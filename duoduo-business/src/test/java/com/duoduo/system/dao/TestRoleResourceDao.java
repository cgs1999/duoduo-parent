package com.duoduo.system.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.duoduo.TestHelper;
import com.duoduo.core.test.BaseTest;
import com.duoduo.system.model.Resource;
import com.duoduo.system.model.Role;
import com.duoduo.system.model.RoleResource;

/**
 * 角色资源关系Dao测试
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:36:27
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRoleResourceDao extends BaseTest {

	private static Long roleId1;
	private static Long roleId2;

	private static Long parentId;

	private static Long resourceId1;
	private static Long resourceId2;

	@javax.annotation.Resource
	private RoleResourceDao roleResourceDao;
	@javax.annotation.Resource
	private RoleDao roleDao;
	@javax.annotation.Resource
	private ResourceDao resourceDao;

	@Test
	public void test10Create() {
		// 创建角色1
		Role role = roleDao.create(TestHelper.createRole1());
		Assert.assertNotNull(role);
		roleId1 = role.getId();
		System.out.println("roleId1=" + roleId1);

		// 创建角色2
		role = roleDao.create(TestHelper.createRole2());
		Assert.assertNotNull(role);
		roleId2 = role.getId();
		System.out.println("roleId2=" + roleId2);

		// 创建父资源
		Resource resource = resourceDao.create(TestHelper.createMenuRoot());
		Assert.assertNotNull(resource);
		parentId = resource.getId();
		System.out.println("parentId=" + parentId);

		// 创建资源1
		resource = resourceDao.create(TestHelper.createResource1(parentId));
		Assert.assertNotNull(resource);
		resourceId1 = resource.getId();
		System.out.println("resourceId1=" + resourceId1);

		// 创建资源2
		resource = resourceDao.create(TestHelper.createResource2(parentId));
		Assert.assertNotNull(resource);
		resourceId2 = resource.getId();
		System.out.println("resourceId2=" + resourceId2);

		// 创建角色资源关系1
		RoleResource roleResource = new RoleResource();
		roleResource.setRoleId(roleId1);
		roleResource.setResourceId(resourceId1);
		Assert.assertTrue(roleResourceDao.create(roleResource));

		// 创建角色资源关系2
		roleResource = new RoleResource();
		roleResource.setRoleId(roleId1);
		roleResource.setResourceId(resourceId2);
		Assert.assertTrue(roleResourceDao.create(roleResource));

		// 创建角色资源关系3
		roleResource = new RoleResource();
		roleResource.setRoleId(roleId2);
		roleResource.setResourceId(resourceId2);
		Assert.assertTrue(roleResourceDao.create(roleResource));
	}

	@Test
	public void test20ListByRoleId() {
		// role1
		List<RoleResource> roleResourceList = roleResourceDao.listByRoleId("" + roleId1);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 2);

		// role2
		roleResourceList = roleResourceDao.listByRoleId("" + roleId2);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 1);
	}

	@Test
	public void test22ListByResourceId() {
		// resource1
		List<RoleResource> roleResourceList = roleResourceDao.listByResourceId("" + resourceId1);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 1);

		// resource2
		roleResourceList = roleResourceDao.listByResourceId("" + resourceId2);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 2);
	}

	@Test
	public void test30DeleteByRoleId() {
		Assert.assertTrue(roleResourceDao.deleteByRoleId("" + roleId1));

		// role1
		List<RoleResource> roleResourceList = roleResourceDao.listByRoleId("" + roleId1);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 0);
	}

	@Test
	public void test32DeleteByResourceId() {
		Assert.assertTrue(roleResourceDao.deleteByResourceId("" + resourceId2));

		// role1
		List<RoleResource> roleResourceList = roleResourceDao.listByResourceId("" + resourceId2);
		Assert.assertNotNull(roleResourceList);
		Assert.assertEquals(roleResourceList.size(), 0);

		clearData();
	}

	private void clearData() {
		roleDao.delete("" + roleId1);
		roleDao.delete("" + roleId2);
		resourceDao.delete("" + resourceId1);
		resourceDao.delete("" + resourceId2);
		resourceDao.delete("" + parentId);
	}
}
