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
import com.duoduo.system.vo.LanguageVO;

/**
 * 语言管理Service
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:53:24
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLanguageService extends BaseTest {

	private static Long entityId1;
	private static Long entityId2;

	@Resource
	private LanguageService languageService;

	@Test
	public void test10Create() {
		// 创建语言1
		LanguageVO language = languageService.create(TestHelper.createLanguageVO1());
		Assert.assertNotNull(language);
		entityId1 = language.getId();
		System.out.println("entityId1=" + entityId1);

		// 创建语言2
		language = languageService.create(TestHelper.createLanguageVO2());
		Assert.assertNotNull(language);
		entityId2 = language.getId();
		System.out.println("entityId2=" + entityId2);

	}

	@Test
	public void test20GetById() {
		LanguageVO language = languageService.getById("" + entityId1);
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getName(), "闽南语");
	}

	@Test
	public void test22GetByName() {
		LanguageVO language = languageService.getByName("闽南语");
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getId(), entityId1);
	}

	@Test
	public void test23GetByI18nTag() {
		LanguageVO language = languageService.getByI18nTag("mingnan");
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getId(), entityId1);
	}

	@Test
	public void test24ListAll() {
		List<LanguageVO> languageList = languageService.listAll();
		Assert.assertNotNull(languageList);
		Assert.assertEquals(languageList.size(), 2);

		TestHelper.printVOList(languageList);
	}

	@Test
	public void test25ListAllEnable() {
		List<LanguageVO> languageList = languageService.listAllEnable();
		Assert.assertNotNull(languageList);
		Assert.assertEquals(languageList.size(), 2);

		TestHelper.printVOList(languageList);
	}

	@Test
	public void test27PageList() {
		Page<LanguageVO> page = new Page<LanguageVO>();
		page = languageService.pagingList("闽南语", null, page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<LanguageVO>();
		page = languageService.pagingList(null, "mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<LanguageVO>();
		page = languageService.pagingList("闽南语", "mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test28PageList() {
		Page<LanguageVO> page = new Page<LanguageVO>();
		page = languageService.pagingList("mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test30Update() {
		LanguageVO language = languageService.getById("" + entityId1);
		Assert.assertNotNull(language);

		language.setEnable(1);
		language.setMemo("修改后的备注信息");

		languageService.update(language);

		language = languageService.getById("" + entityId1);
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getEnable().intValue(), 1);
	}

	@Test
	public void test40Delete() {
		Assert.assertTrue(languageService.delete("" + entityId1));
		LanguageVO language = languageService.getById("" + entityId1);
		Assert.assertNull(language);

		Assert.assertTrue(languageService.delete("" + entityId2));
		language = languageService.getById("" + entityId2);
		Assert.assertNull(language);
	}
}
