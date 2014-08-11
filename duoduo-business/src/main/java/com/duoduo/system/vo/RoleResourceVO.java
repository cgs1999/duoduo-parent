package com.duoduo.system.vo;

import com.duoduo.system.model.RoleResource;

/**
 * 角色资源关系VO
 * @author chengesheng@gmail.com
 * @date 2014-5-30 上午12:53:26
 * @version 1.0.0
 */
public class RoleResourceVO {

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

	/**
	 * 转换Entity为VO
	 * @param entity
	 * @return
	 */
	public static RoleResourceVO fromEntity(RoleResource entity) {
		RoleResourceVO vo = new RoleResourceVO();
		vo.setRoleId(entity.getRoleId());
		vo.setResourceId(entity.getResourceId());
		return vo;
	}

	/**
	 * 转换VO为Entity
	 * @param vo
	 * @return
	 */
	public static RoleResource toEntity(RoleResourceVO vo) {
		RoleResource entity = new RoleResource();
		entity.setRoleId(vo.getRoleId());
		entity.setResourceId(vo.getResourceId());
		return entity;
	}

}
