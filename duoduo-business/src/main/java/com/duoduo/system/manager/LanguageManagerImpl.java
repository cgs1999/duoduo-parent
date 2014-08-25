package com.duoduo.system.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duoduo.core.vo.Page;
import com.duoduo.system.dao.LanguageDao;
import com.duoduo.system.model.Language;

/**
 * 语言管理业务实现类
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午12:23:59
 * @version 1.0.0
 */
@Service("languageManager")
public class LanguageManagerImpl implements LanguageManager {

	@Resource
	private LanguageDao languageDao;

	@Override
	public Language getById(String id) {
		return languageDao.getById(id);
	}

	@Override
	public Language getByName(String name) {
		return languageDao.getByName(name);
	}

	@Override
	public Language getByI18nTag(String i18nTag) {
		return languageDao.getByI18nTag(i18nTag);
	}

	@Override
	public List<Language> listAll() {
		return languageDao.listAll();
	}

	@Override
	public List<Language> listAllEnable() {
		return languageDao.listAllEnable();
	}

	@Override
	public Language create(Language language) {
		return languageDao.create(language);
	}

	@Override
	public void update(Language language) {
		languageDao.update(language);
	}

	@Override
	public boolean delete(String id) {
		return languageDao.delete(id);
	}

	@Override
	public Page<Language> pagingList(String key, Page<Language> page) {
		return languageDao.pagingList(key, page);
	}

	@Override
	public Page<Language> pagingList(String name, String i18nTag, Page<Language> page) {
		return languageDao.pagingList(name, i18nTag, page);
	}
}
