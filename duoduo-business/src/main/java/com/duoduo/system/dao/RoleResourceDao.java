package com.duoduo.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.duoduo.core.dao.BaseDao;
import com.duoduo.system.model.RoleResource;

/**
 * 角色资源关系管理Dao
 * @author chengesheng@gmail.com
 * @date 2014-6-2 下午9:55:35
 * @version 1.0.0
 */
@Repository("roleResourceDao")
public class RoleResourceDao extends BaseDao {

	private static final RowMapper<RoleResource> entityRowMapper = new RowMapper<RoleResource>() {

		@Override
		public RoleResource mapRow(ResultSet rs, int rowNum) throws SQLException {
			final RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(rs.getLong("role_id"));
			roleResource.setResourceId(rs.getLong("resource_id"));
			return roleResource;
		}
	};

	private static final String listByRoleIdSql = "select * from sys_role_resource where role_id=?";

	/**
	 * 根据角色Id获取角色权限关系
	 */
	public List<RoleResource> listByRoleId(String roleId) {
		try {
			List<RoleResource> result = super.getJdbcTemplate().query(listByRoleIdSql, entityRowMapper, roleId);
			return result;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String listByResourceIdSql = "select * from sys_role_resource where resource_id=?";

	/**
	 * 根据权限Id获取角色权限关系
	 */
	public List<RoleResource> listByResourceId(String resourceId) {
		try {
			List<RoleResource> result = super.getJdbcTemplate().query(listByResourceIdSql, entityRowMapper, resourceId);
			return result;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String insertSql = "insert into sys_role_resource(role_id,resource_id) values (?,?)";

	/**
	 * 创建角色权限关系
	 */
	public synchronized boolean create(final RoleResource roleResource) {
		Object[] args = new Object[] {
				roleResource.getRoleId(), roleResource.getResourceId()
		};
		super.getJdbcTemplate().update(insertSql, args);
		return true;
	}

	private static final String deleteByRoleIdSql = "delete from sys_role_resource where role_id=?";

	/**
	 * 根据角色Id删除角色权限关系
	 */
	public boolean deleteByRoleId(String roleId) {
		super.getJdbcTemplate().update(deleteByRoleIdSql, roleId);
		return true;
	}

	private static final String deleteByResourceIdSql = "delete from sys_role_resource where resource_id=?";

	/**
	 * 根据权限Id删除角色权限关系
	 */
	public boolean deleteByResourceId(String resourceId) {
		super.getJdbcTemplate().update(deleteByResourceIdSql, resourceId);
		return true;
	}
}
