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
import com.duoduo.system.model.Language;

/**
 * 语言管理Dao测试
 * @author chengesheng@gmail.com
 * @date 2014-7-21 下午4:34:46
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLanguageDao extends BaseTest {

	private static Long entityId1;
	private static Long entityId2;

	@Resource
	private LanguageDao languageDao;

	@Test
	public void test10Create() {
		// 创建语言1
		Language language = languageDao.create(TestHelper.createLanguage1());

		Assert.assertNotNull(language);
		entityId1 = language.getId();
		System.out.println("entityId1=" + entityId1);

		// 创建语言2
		language = languageDao.create(TestHelper.createLanguage2());

		Assert.assertNotNull(language);
		entityId2 = language.getId();
		System.out.println("entityId2=" + entityId2);

	}

	@Test
	public void test20GetById() {
		Language language = languageDao.getById("" + entityId1);
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getName(), "闽南语");
	}

	@Test
	public void test22GetByName() {
		Language language = languageDao.getByName("闽南语");
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getId(), entityId1);
	}

	@Test
	public void test23GetByI18nTag() {
		Language language = languageDao.getByI18nTag("mingnan");
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getId(), entityId1);
	}

	@Test
	public void test24ListAll() {
		List<Language> languageList = languageDao.listAll();
		Assert.assertNotNull(languageList);
		Assert.assertEquals(languageList.size(), 2);

		TestHelper.printEntityList(languageList);
	}

	@Test
	public void test26ListAllEnable() {
		List<Language> languageList = languageDao.listAllEnable();
		Assert.assertNotNull(languageList);
		Assert.assertEquals(languageList.size(), 2);

		TestHelper.printEntityList(languageList);
	}

	@Test
	public void test27PageList() {
		Page<Language> page = new Page<Language>();
		page = languageDao.pagingList("闽南语", null, page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<Language>();
		page = languageDao.pagingList(null, "mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);

		page = new Page<Language>();
		page = languageDao.pagingList("闽南语", "mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test28PageList() {
		Page<Language> page = new Page<Language>();
		page = languageDao.pagingList("mingnan", page);
		Assert.assertNotNull(page);
		Assert.assertEquals(page.getRows().size(), 1);
	}

	@Test
	public void test30Update() {
		Language language = languageDao.getById("" + entityId1);
		Assert.assertNotNull(language);

		language.setEnable(1);
		language.setMemo("修改后的备注信息");

		languageDao.update(language);

		language = languageDao.getById("" + entityId1);
		Assert.assertNotNull(language);
		Assert.assertEquals(language.getEnable().intValue(), 1);
	}

	@Test
	public void test40Delete() {
		Assert.assertTrue(languageDao.delete("" + entityId1));
		Language language = languageDao.getById("" + entityId1);
		Assert.assertNull(language);

		Assert.assertTrue(languageDao.delete("" + entityId2));
		language = languageDao.getById("" + entityId2);
		Assert.assertNull(language);
	}
}
