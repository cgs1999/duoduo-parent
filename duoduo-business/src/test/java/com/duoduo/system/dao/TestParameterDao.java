package com.duoduo.system.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.duoduo.TestHelper;
import com.duoduo.core.test.BaseTest;
import com.duoduo.core.vo.Page;
import com.duoduo.system.model.Parameter;

/**
 * 参数管理Dao测试
 * @author chengesheng@gmail.com
 * @date 2014-8-26 下午11:35:42
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestParameterDao extends BaseTest {

	private static Long entityId1;
	private static Long entityId2;

	@Resource
	private ParameterDao parameterDao;

	@Test
	public void test10Create() {
		// 创建参数1
		Parameter parameter = parameterDao.create(TestHelper.createParameter1());
		Assert.assertNotNull(parameter);
		entityId1 = parameter.getId();
		System.out.println("entityId1=" + entityId1);

		// 创建参数2
		parameter = parameterDao.create(TestHelper.createParameter2());
		Assert.assertNotNull(parameter);
		entityId2 = parameter.getId();
		System.out.println("entityId2=" + entityId2);

	}

	@Test
	public void test20GetById() {
		Parameter parameter = parameterDao.getById("" + entityId1);
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getKey(), "ENTERPRISE_NAME");
	}

	@Test
	public void test21GetByName() {
		Parameter parameter = parameterDao.getByName("企业名称");
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getId(), entityId1);
	}

	@Test
	public void test22GetByKey() {
		Parameter parameter = parameterDao.getByKey("ENTERPRISE_NAME");
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getId(), entityId1);
	}

	@Test
	public void test24ListByType() {
		List<Parameter> parameterList = parameterDao.listByType("系统参数");
		Assert.assertNotNull(parameterList);
		Assert.assertEquals(parameterList.size(), 2);

		TestHelper.printEntityList(parameterList);
	}

	@Test
	public void test28PageList() {
		// 账号作为关键字进行搜索
		Page<Parameter> page = new Page<Parameter>();
		page = parameterDao.pagingList("ENTERPRISE_NAME", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test30Update() {
		Parameter parameter = parameterDao.getById("" + entityId1);
		Assert.assertNotNull(parameter);

		parameter.setName("企业图标");
		parameter.setKey("ENTERPRISE_LOGO");
		parameter.setMemo("修改后的备注信息");

		parameterDao.update(parameter);

		parameter = parameterDao.getById("" + entityId1);
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getKey(), "ENTERPRISE_LOGO");
	}

	@Test
	public void test40Delete() {
		Assert.assertTrue(parameterDao.delete("" + entityId1));
		Parameter parameter = parameterDao.getById("" + entityId1);
		Assert.assertNull(parameter);

		Assert.assertTrue(parameterDao.delete("" + entityId2));
		parameter = parameterDao.getById("" + entityId2);
		Assert.assertNull(parameter);
	}
}
