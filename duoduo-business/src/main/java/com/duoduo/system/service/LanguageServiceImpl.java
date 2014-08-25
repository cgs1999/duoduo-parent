package com.duoduo.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoduo.core.vo.Page;
import com.duoduo.system.manager.LanguageManager;
import com.duoduo.system.model.Language;
import com.duoduo.system.vo.LanguageVO;

/**
 * 语言管理业务实现类
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午12:23:59
 * @version 1.0.0
 */
@Transactional
@Service("languageService")
public class LanguageServiceImpl implements LanguageService {

	@Resource
	private LanguageManager languageManager;

	@Override
	public LanguageVO getById(String id) {
		Language language = languageManager.getById(id);
		if (language == null) {
			return null;
		}
		return LanguageVO.fromEntity(language);
	}

	@Override
	public LanguageVO getByName(String name) {
		Language language = languageManager.getByName(name);
		if (language == null) {
			return null;
		}
		return LanguageVO.fromEntity(language);
	}

	@Override
	public LanguageVO getByI18nTag(String i18nTag) {
		Language language = languageManager.getByI18nTag(i18nTag);
		if (language == null) {
			return null;
		}
		return LanguageVO.fromEntity(language);
	}

	@Override
	public List<LanguageVO> listAll() {
		return fromEntityList(languageManager.listAll());
	}

	@Override
	public List<LanguageVO> listAllEnable() {
		return fromEntityList(languageManager.listAllEnable());
	}

	@Override
	public LanguageVO create(LanguageVO languageVO) {
		Language language = languageManager.create(LanguageVO.toEntity(languageVO));
		if (language == null) {
			return null;
		}

		return LanguageVO.fromEntity(language);
	}

	@Override
	public void update(LanguageVO languageVO) {
		languageManager.update(LanguageVO.toEntity(languageVO));
	}

	@Override
	public boolean delete(String id) {
		return languageManager.delete(id);
	}

	@Override
	public Page<LanguageVO> pagingList(String key, Page<LanguageVO> page) {
		Page<Language> entityPage = languageManager.pagingList(key, toEntityPage(page));

		return fromEntityPage(entityPage);
	}

	@Override
	public Page<LanguageVO> pagingList(String name, String i18nTag, Page<LanguageVO> page) {
		Page<Language> entityPage = languageManager.pagingList(name, i18nTag, toEntityPage(page));

		return fromEntityPage(entityPage);
	}

	/**
	 * 转换Page &lt;VO&gt; 为 Page &lt;Entity&gt;
	 * @param voPage
	 * @return
	 */
	private Page<Language> toEntityPage(Page<LanguageVO> voPage) {
		Page<Language> entityPage = new Page<Language>();
		entityPage.setStart(voPage.getStart());
		entityPage.setLimit(voPage.getLimit());
		entityPage.setTotal(voPage.getTotal());
		entityPage.setSort(voPage.getSort());
		entityPage.setDir(voPage.getDir());
		return entityPage;
	}

	/**
	 * 转换Page &lt;Entity&gt; 为 Page &lt;VO&gt;
	 * @param entityPage
	 * @return
	 */
	private Page<LanguageVO> fromEntityPage(Page<Language> entityPage) {
		Page<LanguageVO> voPage = new Page<LanguageVO>();
		voPage.setStart(entityPage.getStart());
		voPage.setLimit(entityPage.getLimit());
		voPage.setTotal(entityPage.getTotal());
		voPage.setRows(fromEntityList(entityPage.getRows()));
		voPage.setFooter(fromEntityList(entityPage.getFooter()));
		voPage.setSort(entityPage.getSort());
		voPage.setDir(entityPage.getDir());
		return voPage;
	}

	/**
	 * 转换List &lt;Entity&gt; 为 List &lt;VO&gt;
	 * @param voList
	 * @return
	 */
	private List<LanguageVO> fromEntityList(List<Language> entityList) {
		List<LanguageVO> voList = new ArrayList<LanguageVO>(0);
		if (entityList != null && !entityList.isEmpty()) {
			for (Language entity : entityList) {
				voList.add(LanguageVO.fromEntity(entity));
			}
		}
		return voList;
	}
}
