package com.duoduo.system.model;

/**
 * 角色资源关系
 * @author chengesheng@gmail.com
 * @date 2014-8-12 上午1:13:19
 * @version 1.0.0
 */
public class RoleResource {

	/** 角色ID */
	private Long roleId;
	/** 资源ID */
	private Long resourceId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
