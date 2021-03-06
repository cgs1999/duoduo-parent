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
import com.duoduo.system.model.Resource;

/**
 * 资源管理Dao
 * @author chengesheng@gmail.com
 * @date 2014-5-30 下午1:24:06
 * @version 1.0.0
 */
@Repository("resourceDao")
public class ResourceDao extends BaseDao {

	private static final RowMapper<Resource> entityRowMapper = new RowMapper<Resource>() {

		@Override
		public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
			final Resource resource = new Resource();
			resource.setId(rs.getLong("id"));
			resource.setName(rs.getString("name"));
			resource.setType(rs.getString("type"));
			resource.setUrl(rs.getString("url"));
			resource.setParentId(rs.getLong("parent_id"));

			try {
				resource.setParentName(rs.getString("parentName"));
			} catch (SQLException e) {
			}

			resource.setParentIds(rs.getString("parent_ids"));
			resource.setOrderIndex(rs.getInt("order_index"));
			resource.setEnable(rs.getBoolean("enable"));
			resource.setMemo(rs.getString("memo"));
			resource.setCreateTime(rs.getTimestamp("create_time"));
			resource.setUpdateTime(rs.getTimestamp("update_time"));
			return resource;
		}
	};

	private static final String getByIdSql = "select r.*,r2.name as parentName from sys_resource r"
			+ " left join sys_resource r2 on r.parent_id=r2.id" + " where r.id=?" + " order by r.order_index";

	/**
	 * 根据Id获取资源
	 */
	public Resource getById(String id) {
		try {
			Resource resource = super.getJdbcTemplate().queryForObject(getByIdSql, entityRowMapper, id);
			return resource;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String getByNameSql = "select * from sys_resource where name=?" + " order by order_index";

	/**
	 * 根据名称获取资源
	 */
	public Resource getByName(String name) {
		try {
			Resource resource = super.getJdbcTemplate().queryForObject(getByNameSql, entityRowMapper, name);
			return resource;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String insertSql = "insert into sys_resource(name,type,url,parent_id,parent_ids,order_index,enable,memo,create_time,update_time) values"
			+ "(?,?,?,?,?,?,?,?,now(),now())";

	/**
	 * 创建资源
	 */
	public synchronized Resource create(final Resource resource) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		super.getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {
					"id"
				});
				int count = 1;
				ps.setString(count++, resource.getName());
				ps.setString(count++, resource.getType());
				ps.setString(count++, resource.getUrl());
				ps.setObject(count++, resource.getParentId());
				ps.setString(count++, resource.getParentIds());
				ps.setInt(count++, (resource.getOrderIndex() == null ? 0 : resource.getOrderIndex()));
				ps.setInt(count++, resource.getEnable() ? 1 : 0);
				ps.setString(count++, resource.getMemo());
				return ps;
			}
		}, keyHolder);

		resource.setId(keyHolder.getKey().longValue());
		return resource;
	}

	private static final String updateSql = "update sys_resource set name=?,type=?,url=?,parent_id=?,parent_ids=?,order_index=?,enable=?,memo=?,update_time=now() where id=?";

	/**
	 * 修改资源
	 */
	public void update(Resource resource) {
		Object[] args = new Object[] {
				resource.getName(), resource.getType(), resource.getUrl(), resource.getParentId(),
				resource.getParentIds(), resource.getOrderIndex(), resource.getEnable(), resource.getMemo(),
				resource.getId()
		};
		super.getJdbcTemplate().update(updateSql, args);
	}

	private static final String deleteSql = "delete from sys_resource where id=?";

	/**
	 * 删除资源
	 */
	public boolean delete(String id) {
		super.getJdbcTemplate().update(deleteSql, id);
		return true;
	}

	/**
	 * 分页查询资源列表（模糊查询，条件为：资源名称）
	 */
	public Page<Resource> pagingList(String name, Page<Resource> page) {
		String countSql = "select count(id) from sys_resource where 1=1";
		String queryByPageSql = "select r.*,r2.name as parentName from sys_resource r"
				+ " left join sys_resource r2 on r2.id=r.parent_id" + " where 1=1";

		if (StringUtils.hasText(name)) {
			countSql += " and name like :likeName";
			queryByPageSql += " and r.name like :likeName";
		}

		queryByPageSql += " order by r.order_index";
		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeName", super.filterKeyPara(name));
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}

	private static final String listByUserIdSql = "select distinct r.* from sys_resource r"
			+ " left join sys_role_resource rr on rr.resource_id=r.id"
			+ " left join sys_user_role ur on ur.role_id=rr.role_id" + " where ur.user_id = ?"
			+ " order by r.order_index";

	/**
	 * 根据用户Id获取资源
	 */
	public List<Resource> listByUserId(String userId) {
		try {
			List<Resource> resources = super.getJdbcTemplate().query(listByUserIdSql, entityRowMapper, userId);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listByRoleIdSql = "select distinct r.* from sys_resource r"
			+ " left join sys_role_resource rr on rr.resource_id=r.id" + " where rr.role_id = ?"
			+ " order by r.order_index";

	/**
	 * 根据角色Id获取资源
	 */
	public List<Resource> listByRoleId(String roleId) {
		try {
			List<Resource> resources = super.getJdbcTemplate().query(listByRoleIdSql, entityRowMapper, roleId);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listAllSql = "select * from sys_resource" + " order by order_index";

	/**
	 * 获取所有资源
	 */
	public List<Resource> listAll() {
		try {
			List<Resource> resources = super.getJdbcTemplate().query(listAllSql, entityRowMapper);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listByTypeSql = "select * from sys_resource where type=?" + " order by order_index";

	/**
	 * 根据类型获取资源
	 */
	public List<Resource> listByType(String type) {
		try {
			List<Resource> resources = super.getJdbcTemplate().query(listByTypeSql, entityRowMapper, type);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listSubResourceSql = "select * from sys_resource where 1=1";

	/**
	 * 根据父Id获取下属资源
	 */
	public List<Resource> listSubResource(String parentId) {
		try {
			String sql = listSubResourceSql;
			if (parentId == null) {
				sql += " and parent_id is ?";
			} else {
				sql += " and parent_id = ?";
			}
			sql += " order by order_index";
			List<Resource> resources = super.getJdbcTemplate().query(sql, entityRowMapper, parentId);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listSubResourceByTypeSql = "select * from sys_resource where 1=1";

	/**
	 * 根据父Id获取下属资源
	 */
	public List<Resource> listSubResourceByType(String parentId, String type) {
		try {
			String sql = listSubResourceByTypeSql;
			if (parentId == null) {
				sql += " and parent_id is ?";
			} else {
				sql += " and parent_id = ?";
			}
			sql += " and type=?";
			sql += " order by order_index";
			List<Resource> resources = super.getJdbcTemplate().query(sql, entityRowMapper, parentId, type);
			return resources;
		} catch (DataAccessException e) {
		}
		return null;
	}
}
