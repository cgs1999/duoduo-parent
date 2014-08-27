package com.duoduo.system.dao;

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
import com.duoduo.system.model.Parameter;

@Repository("parameterDao")
public class ParameterDao extends BaseDao {

	private static final RowMapper<Parameter> entityRowMapper = new RowMapper<Parameter>() {

		@Override
		public Parameter mapRow(ResultSet rs, int rowNum) throws SQLException {
			final Parameter entity = new Parameter();
			entity.setId(rs.getLong("id"));
			entity.setKey(rs.getString("key"));
			entity.setName(rs.getString("name"));
			entity.setValue(rs.getString("value"));
			entity.setType(rs.getString("type"));
			entity.setMemo(rs.getString("memo"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setUpdateTime(rs.getTimestamp("update_time"));
			return entity;
		}
	};

	private static final String getByIdSql = "select * from sys_parameter where id=?";

	/**
	 * 根据Id获取
	 */
	public Parameter getById(String id) {
		try {
			Parameter parameter = super.getJdbcTemplate().queryForObject(getByIdSql, entityRowMapper, id);
			return parameter;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String getByNameSql = "select * from sys_parameter where `name`=?";

	/**
	 * 根据name获取
	 */
	public Parameter getByName(String name) {
		try {
			Parameter parameter = super.getJdbcTemplate().queryForObject(getByNameSql, entityRowMapper, name);
			return parameter;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String getByKeySql = "select * from sys_parameter where `key`=?";

	/**
	 * 根据key获取
	 */
	public Parameter getByKey(String key) {
		try {
			Parameter parameter = super.getJdbcTemplate().queryForObject(getByKeySql, entityRowMapper, key);
			return parameter;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String listByTypeSql = "select * from sys_parameter where `type`=?";

	/**
	 * 根据type获取列表
	 */
	public List<Parameter> listByType(String type) {
		try {
			List<Parameter> parameters = super.getJdbcTemplate().query(listByTypeSql, entityRowMapper, type);
			return parameters;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String insertSql = "insert into sys_parameter(`key`,`name`,`value`,`type`,`memo`,`create_time`,`update_time`) values"
			+ "(?,?,?,?,?,now(),now())";

	/**
	 * 创建参数
	 */
	public synchronized Parameter create(final Parameter parameter) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		super.getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {
					"id"
				});
				int count = 1;
				ps.setString(count++, parameter.getKey());
				ps.setString(count++, parameter.getName());
				ps.setString(count++, parameter.getValue());
				ps.setString(count++, parameter.getType());
				ps.setString(count++, parameter.getMemo());
				return ps;
			}
		}, keyHolder);

		parameter.setId(keyHolder.getKey().longValue());
		return parameter;
	}

	private static final String updateSql = "update sys_parameter set `key`=?,`name`=?,`value`=?,"
			+ "`type`=?,`memo`=?,`update_time`=now() where `id`=?";

	/**
	 * 修改参数
	 */
	public void update(Parameter parameter) {
		Object[] args = new Object[] {
				parameter.getKey(), parameter.getName(), parameter.getValue(), parameter.getType(),
				parameter.getMemo(), parameter.getId()
		};
		super.getJdbcTemplate().update(updateSql, args);
	}

	private static final String deleteSql = "delete from sys_parameter where id=?";

	/**
	 * 删除参数
	 */
	public boolean delete(String id) {
		super.getJdbcTemplate().update(deleteSql, id);
		return true;
	}

	/**
	 * 分页查询参数列表（模糊查询，条件为：关键字、名称、值）
	 */
	public Page<Parameter> pagingList(String key, String name, String value, Page<Parameter> page) {
		String countSql = "select count(id) from sys_parameter where 1=1";
		String queryByPageSql = "select * from sys_parameter where 1=1";

		if (StringUtils.hasText(key)) {
			countSql += " and `key` like :likeKey";
			queryByPageSql += " and `key` like :likeKey";
		}

		if (StringUtils.hasText(name)) {
			countSql += " and `name` like :likeName";
			queryByPageSql += " and `name` like :likeName";
		}

		if (StringUtils.hasText(value)) {
			countSql += " and `value` like :likeValue";
			queryByPageSql += " and `value` like :likeValue";
		}

		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeKey", super.filterKeyPara(key));
		params.put("likeName", super.filterKeyPara(name));
		params.put("likeValue", super.filterKeyPara(value));
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}

	/**
	 * 分页查询参数列表（关键字模糊查询，模糊查询内容：关键字、名称、值）
	 */
	public Page<Parameter> pagingList(String searchKey, Page<Parameter> page) {
		String countSql = "select count(id) from sys_parameter where 1=1";
		String queryByPageSql = "select * from sys_parameter where 1=1";

		if (StringUtils.hasText(searchKey)) {
			countSql += " and ((`key` like :likeKey) or (`name` like :likeName) or (`value` like :likeValue))";
			queryByPageSql += " and ((`key` like :likeKey) or (`name` like :likeName) or (`value` like :likeValue))";
		}

		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeKey", super.filterKeyPara(searchKey));
		params.put("likeName", super.filterKeyPara(searchKey));
		params.put("likeValue", super.filterKeyPara(searchKey));
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}
}
