package com.duoduo;

import java.util.List;
import java.util.UUID;

import com.duoduo.core.model.IdEntity;
import com.duoduo.core.vo.BaseVO;
import com.duoduo.system.model.Resource;
import com.duoduo.system.model.Role;
import com.duoduo.system.model.User;
import com.duoduo.system.vo.ResourceVO;
import com.duoduo.system.vo.RoleVO;
import com.duoduo.system.vo.UserVO;

/**
 * 测试帮助类
 * @author chengesheng@gmail.com
 * @date 2014-7-29 下午3:24:31
 * @version 1.0.0
 */
public class TestHelper {

	// ***************************************
	// Entity
	// ***************************************
	// 创建用户1
	public static User createUser1() {
		User user = new User();
		user.setAccount("cgs");
		user.setName("陈格生");
		user.setEmail("chengesheng@gmail.com");
		user.setPhone("13312345678");
		user.setPassword("888888");
		user.setSalt(UUID.randomUUID().toString());
		user.setStatus(1);
		return user;
	}

	// 创建用户2
	public static User createUser2() {
		User user = new User();
		user.setAccount("jason");
		user.setName("陈小生");
		user.setEmail("jason@126.com");
		user.setPhone("15912345678");
		user.setPassword("888888");
		user.setSalt(UUID.randomUUID().toString());
		user.setStatus(0);
		return user;
	}

	// 创建角色1
	public static Role createRole1() {
		Role role = new Role();
		role.setName("admin");
		role.setType("1");
		role.setEnable(Boolean.TRUE);
		role.setMemo("备注信息");
		return role;
	}

	// 创建角色2
	public static Role createRole2() {
		Role role = new Role();
		role.setName("user");
		role.setType("1");
		role.setEnable(Boolean.FALSE);
		role.setMemo("普通用户");
		return role;
	}

	// 创建资源(根菜单)
	public static Resource createMenuRoot() {
		Resource resource = new Resource();
		resource.setName("系统配置");
		resource.setUrl("");
		resource.setType("1");
		resource.setParentId(null);
		resource.setOrderIndex(0);
		resource.setEnable(Boolean.TRUE);
		resource.setMemo("系统配置");
		return resource;
	}

	// 创建资源(菜单)1
	public static Resource createMenu1(Long parentId) {
		Resource resource = new Resource();
		resource.setName("用户管理");
		resource.setUrl("module/createRecord");
		resource.setType("1");
		resource.setParentId(parentId);
		resource.setOrderIndex(10);
		resource.setEnable(Boolean.TRUE);
		resource.setMemo("用户管理");
		return resource;
	}

	// 创建资源(菜单)2
	public static Resource createMenu2(Long parentId) {
		Resource resource = new Resource();
		resource.setName("角色管理");
		resource.setUrl("module/deleteRecord");
		resource.setType("1");
		resource.setParentId(parentId);
		resource.setOrderIndex(20);
		resource.setEnable(Boolean.FALSE);
		resource.setMemo("角色管理");
		return resource;
	}

	// 创建资源1
	public static Resource createResource1(Long parentId) {
		Resource resource = new Resource();
		resource.setName("创建记录");
		resource.setUrl("module/createRecord");
		resource.setType("2");
		resource.setParentId(parentId);
		resource.setOrderIndex(1010);
		resource.setEnable(Boolean.TRUE);
		resource.setMemo("创建记录");
		return resource;
	}

	// 创建资源2
	public static Resource createResource2(Long parentId) {
		Resource resource = new Resource();
		resource.setName("删除记录");
		resource.setUrl("module/deleteRecord");
		resource.setType("2");
		resource.setParentId(parentId);
		resource.setOrderIndex(2010);
		resource.setEnable(Boolean.FALSE);
		resource.setMemo("删除记录");
		return resource;
	}

	// ***************************************
	// VO
	// ***************************************
	// 创建用户VO1
	public static UserVO createUserVO1(String roleIds) {
		UserVO user = new UserVO();
		user.setAccount("cgs");
		user.setName("陈格生");
		user.setEmail("chengesheng@gmail.com");
		user.setPhone("13312345678");
		user.setPassword("888888");
		user.setSalt(UUID.randomUUID().toString());
		user.setStatus(1);

		user.setRoleIds(roleIds);
		return user;
	}

	// 创建用户VO2
	public static UserVO createUserVO2(String roleIds) {
		UserVO user = new UserVO();
		user.setAccount("jason");
		user.setName("陈小生");
		user.setEmail("jason@126.com");
		user.setPhone("15912345678");
		user.setPassword("888888");
		user.setSalt(UUID.randomUUID().toString());
		user.setStatus(0);

		user.setRoleIds(roleIds);
		return user;
	}

	// 创建角色VO1
	public static RoleVO createRoleVO1(String resourceIds) {
		RoleVO role = new RoleVO();
		role.setName("admin");
		role.setType("1");
		role.setEnable(Boolean.TRUE);
		role.setMemo("备注信息");

		role.setResourceIds(resourceIds);
		return role;
	}

	// 创建角色VO2
	public static RoleVO createRoleVO2(String resourceIds) {
		RoleVO role = new RoleVO();
		role.setName("user");
		role.setType("1");
		role.setEnable(Boolean.FALSE);
		role.setMemo("普通用户");

		role.setResourceIds(resourceIds);
		return role;
	}

	// 创建资源(根菜单)VO
	public static ResourceVO createMenuRootVO() {
		ResourceVO resource = new ResourceVO();
		resource.setName("系统配置");
		resource.setUrl("");
		resource.setType("1");
		resource.setParentId(null);
		resource.setOrderIndex(0);
		resource.setEnable(Boolean.TRUE);
		return resource;
	}

	// 创建资源(菜单)VO1
	public static ResourceVO createMenuVO1(Long parentId) {
		ResourceVO resource = new ResourceVO();
		resource.setName("用户管理");
		resource.setUrl("module/createRecord");
		resource.setType("1");
		resource.setParentId(parentId);
		resource.setOrderIndex(10);
		resource.setEnable(Boolean.TRUE);
		return resource;
	}

	// 创建资源(菜单)VO2
	public static ResourceVO createMenuVO2(Long parentId) {
		ResourceVO resource = new ResourceVO();
		resource.setName("角色管理");
		resource.setUrl("module/deleteRecord");
		resource.setType("1");
		resource.setParentId(parentId);
		resource.setOrderIndex(20);
		resource.setEnable(Boolean.FALSE);
		return resource;
	}

	// 创建资源VO1
	public static ResourceVO createResourceVO1(Long parentId) {
		ResourceVO resource = new ResourceVO();
		resource.setName("创建记录");
		resource.setUrl("module/createRecord");
		resource.setType("2");
		resource.setParentId(parentId);
		resource.setOrderIndex(1010);
		resource.setEnable(Boolean.TRUE);
		return resource;
	}

	// 创建资源VO2
	public static ResourceVO createResourceVO2(Long parentId) {
		ResourceVO resource = new ResourceVO();
		resource.setName("删除记录");
		resource.setUrl("module/deleteRecord");
		resource.setType("2");
		resource.setParentId(parentId);
		resource.setOrderIndex(2010);
		resource.setEnable(Boolean.FALSE);
		return resource;
	}

	/**
	 * 获取所有实体Id，结果以逗号分隔，例如：1,2,4
	 * @param entityList
	 * @return
	 */
	public static <T extends IdEntity> String getEntityListIds(List<T> entityList) {
		String ret = "";
		for (T t : entityList) {
			ret += "," + t.getId();
		}
		return ret.substring(1);
	}

	/**
	 * 获取所有实体名称，结果以逗号分隔，例如：create,update,delete
	 * @param entityList
	 * @return
	 */
	public static <T extends IdEntity> String getEntityListNames(List<T> entityList) {
		String ret = "";
		for (T t : entityList) {
			ret += "," + t.getName();
		}
		return ret.substring(1);
	}

	/**
	 * 输出实体列表信息
	 * @param entityList
	 * @return
	 */
	public static <T extends IdEntity> void printEntityList(List<T> entityList) {
		for (T t : entityList) {
			System.out.println("Entity[" + t.getName() + "]=" + t.toString());
		}
	}

	/**
	 * 获取所有值对象Id，结果以逗号分隔，例如：1,2,4
	 * @param voList
	 * @return
	 */
	public static <T extends BaseVO> String getVOListIds(List<T> voList) {
		String ret = "";
		for (T t : voList) {
			ret += "," + t.getId();
		}
		return ret.substring(1);
	}

	/**
	 * 获取所有值对象名称，结果以逗号分隔，例如：create,update,delete
	 * @param voList
	 * @return
	 */
	public static <T extends BaseVO> String getVOListNames(List<T> voList) {
		String ret = "";
		for (T t : voList) {
			ret += "," + t.getName();
		}
		return ret.substring(1);
	}

	/**
	 * 输出值对象列表信息
	 * @param voList
	 */
	public static <T extends BaseVO> void printVOList(List<T> voList) {
		for (T t : voList) {
			System.out.println("VO[" + t.getName() + "]=" + t.toString());
		}
	}
}
