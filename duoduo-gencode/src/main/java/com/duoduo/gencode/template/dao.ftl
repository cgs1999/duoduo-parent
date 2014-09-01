package ${packageName}.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.duoduo.core.dao.BaseDao;
import com.duoduo.core.vo.Page;
import ${packageName}.model.${beanName};

/**
 * TODO 数据库处理类
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
@Repository("${beanName?uncap_first}Dao")
public class ${beanName}Dao extends BaseDao {

	private static final RowMapper<${beanName}> entityRowMapper = new RowMapper<${beanName}>() {

		@Override
		public ${beanName} mapRow(ResultSet rs, int rowNum) throws SQLException {
			final ${beanName} entity = new ${beanName}();
	<#list columns as item>
		<#assign attrType="${item.attributeType}" >
		<#if attrType=="Integer">
			entity.set${item.attributeName?cap_first}(rs.getInt("${item.columnName}"));
		<#elseif attrType=="Date">
			entity.set${item.attributeName?cap_first}(rs.getTimestamp("${item.columnName}"));
		<#else>
			entity.set${item.attributeName?cap_first}(rs.get${item.attributeType}("${item.columnName}"));
		</#if>
	</#list>
			return entity;
		}
	};

	private static final String getByIdSql = "select * from ${tableName} where id=?";

	/**
	 * 根据Id获取
	 */
	public ${beanName} getById(String id) {
		try {
			${beanName} ${beanName?uncap_first} = super.getJdbcTemplate().queryForObject(getByIdSql, entityRowMapper, id);
			return ${beanName?uncap_first};
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String getByNameSql = "select * from ${tableName} where `name`=?";

	/**
	 * 根据name获取
	 */
	public ${beanName} getByName(String name) {
		try {
			${beanName} ${beanName?uncap_first} = super.getJdbcTemplate().queryForObject(getByNameSql, entityRowMapper, name);
			return ${beanName?uncap_first};
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String listAllSql = "select * from ${tableName} order by `id`";

	/**
	 * 获取所有
	 */
	public List<${beanName}> listAll() {
		try {
			List<${beanName}> ${beanName?uncap_first}s = super.getJdbcTemplate().query(listAllSql, entityRowMapper);
			return ${beanName?uncap_first}s;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String insertSql = "insert into ${tableName}(" + 
		<#list columns as item>
			<#assign attrName="${item.attributeName}" >
			<#if attrName!="id" && attrName!="createTime" && attrName!="updateTime">
			"`${item.columnName}`," + 
			</#if>
		</#list>
			"`create_time`,`update_time`) values (" + 
		<#list columns as item>
			<#assign attrName="${item.attributeName}" >
			<#if attrName!="id" && attrName!="createTime" && attrName!="updateTime">
			"?," + 
			</#if>
		</#list>
			"now(),now())";

	/**
	 * 创建
	 */
	public synchronized ${beanName} create(final ${beanName} ${beanName?uncap_first}) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		super.getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {
					"id"
				});
				int count = 1;
	<#list columns as item>
		<#assign attrName="${item.attributeName}" >
		<#assign attrType="${item.attributeType}" >
		<#if attrName!="id" && attrName!="createTime" && attrName!="updateTime">
			<#if attrType=="Integer">
				ps.setInt(count++, ${beanName?uncap_first}.get${item.attributeName?cap_first}());
			<#else>
				ps.set${item.attributeType}(count++, ${beanName?uncap_first}.get${item.attributeName?cap_first}());
			</#if>
		</#if>
	</#list>
				return ps;
			}
		}, keyHolder);

		${beanName?uncap_first}.setId(keyHolder.getKey().longValue());
		return ${beanName?uncap_first};
	}

	private static final String updateSql = "update ${tableName} set " + 
		<#list columns as item>
			<#assign attrName="${item.attributeName}" >
			<#if attrName!="id" && attrName!="createTime" && attrName!="updateTime">
			"`${item.columnName}`=?," + 
			</#if>
		</#list>
			"`update_time`=now() where `id`=?"; // TODO

	/**
	 * 修改
	 */
	public void update(${beanName} ${beanName?uncap_first}) {
		// TODO
		Object[] args = new Object[] {
			<#list columns as item>
				<#assign attrName="${item.attributeName}" >
				<#if attrName!="id" && attrName!="createTime" && attrName!="updateTime">
				${beanName?uncap_first}.get${item.attributeName?cap_first}(),
				</#if>
			</#list>
				${beanName?uncap_first}.getId()
		};
		super.getJdbcTemplate().update(updateSql, args);
	}

	private static final String deleteSql = "delete from ${tableName} where id=?";

	/**
	 * 删除
	 */
	public boolean delete(String id) {
		super.getJdbcTemplate().update(deleteSql, id);
		return true;
	}

	/**
	 * 分页查询列表（关键字模糊查询，模糊查询内容：名称） // TODO
	 */
	public Page<${beanName}> pagingList(String searchKey, Page<${beanName}> page) {
		String countSql = "select count(id) from ${tableName} where 1=1";
		String queryByPageSql = "select * from ${tableName} where 1=1";

		if (StringUtils.hasText(searchKey)) {
			countSql += " and (`name` like :likeName)"; // TODO
			queryByPageSql += " and (`name` like :likeName)"; // TODO
		}

		queryByPageSql += " order by `order_index`";
		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeName", super.filterKeyPara(searchKey));  // TODO
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}
}

