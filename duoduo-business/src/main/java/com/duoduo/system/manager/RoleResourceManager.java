package com.duoduo.system.manager;

/**
 * 角色资源关系业务处理接口
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午1:45:27
 * @version 1.0.0
 */
public interface RoleResourceManager {

	/**
	 * 保存或更新角色资源信息
	 * @param roleId 角色id
	 * @param resourceIds 角色资源ids，以半角逗号分隔
	 * @return
	 */
	public boolean saveOrUpdate(String roleId, String resourceIds);

	/**
	 * 根据角色id删除角色资源信息
	 * @param roleId 角色id
	 * @return
	 */
	public boolean deleteByRoleId(String roleId);

	/**
	 * 根据资源id删除角色资源信息
	 * @param resourceId 资源id
	 * @return
	 */
	public boolean deleteByResourceId(String resourceId);
}
