package com.duoduo.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.duoduo.TestHelper;
import com.duoduo.core.test.BaseTest;
import com.duoduo.core.vo.Page;
import com.duoduo.system.vo.ParameterVO;

/**
 * 参数管理Service
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:53:24
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestParameterService extends BaseTest {

	private static Long entityId1;
	private static Long entityId2;

	@Resource
	private ParameterService parameterService;

	@Test
	public void test10Create() {
		// 创建参数1
		ParameterVO parameter = parameterService.create(TestHelper.createParameterVO1());

		Assert.assertNotNull(parameter);
		entityId1 = parameter.getId();
		System.out.println("entityId1=" + entityId1);

		// 创建参数2
		parameter = parameterService.create(TestHelper.createParameterVO2());

		Assert.assertNotNull(parameter);
		entityId2 = parameter.getId();
		System.out.println("entityId2=" + entityId2);

	}

	@Test
	public void test20GetById() {
		ParameterVO parameter = parameterService.getById("" + entityId1);
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getName(), "企业名称");
	}

	@Test
	public void test22GetByName() {
		ParameterVO parameter = parameterService.getByName("企业名称");
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getId(), entityId1);
	}

	@Test
	public void test23GetByKey() {
		ParameterVO parameter = parameterService.getByKey("ENTERPRISE_NAME");
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getId(), entityId1);
	}

	@Test
	public void test24ListByType() {
		List<ParameterVO> parameterList = parameterService.listByType("系统参数");
		Assert.assertNotNull(parameterList);
		Assert.assertEquals(parameterList.size(), 2);

		TestHelper.printVOList(parameterList);
	}

	@Test
	public void test27PageList() {
		Page<ParameterVO> page = new Page<ParameterVO>();
		page = parameterService.pagingList("ENTERPRISE_NAME", null, null, page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<ParameterVO>();
		page = parameterService.pagingList(null, "企业名称", null, page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<ParameterVO>();
		page = parameterService.pagingList(null, null, "上海多多工作室", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<ParameterVO>();
		page = parameterService.pagingList("ENTERPRISE_NAME", "企业名称", "上海多多工作室", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test28PageList() {
		Page<ParameterVO> page = new Page<ParameterVO>();
		page = parameterService.pagingList("ENTERPRISE_NAME", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<ParameterVO>();
		page = parameterService.pagingList("企业名称", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<ParameterVO>();
		page = parameterService.pagingList("上海多多工作室", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test30Update() {
		ParameterVO parameter = parameterService.getById("" + entityId1);
		Assert.assertNotNull(parameter);

		parameter.setType("2");
		parameter.setMemo("修改后的备注信息");

		parameterService.update(parameter);

		parameter = parameterService.getById("" + entityId1);
		Assert.assertNotNull(parameter);
		Assert.assertEquals(parameter.getType(), "2");
	}

	@Test
	public void test40Delete() {
		Assert.assertTrue(parameterService.delete("" + entityId1));
		ParameterVO parameter = parameterService.getById("" + entityId1);
		Assert.assertNull(parameter);

		Assert.assertTrue(parameterService.delete("" + entityId2));
		parameter = parameterService.getById("" + entityId2);
		Assert.assertNull(parameter);
	}
}
