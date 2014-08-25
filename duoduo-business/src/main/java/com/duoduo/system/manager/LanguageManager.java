package com.duoduo.system.manager;

import java.util.List;

import com.duoduo.core.vo.Page;
import com.duoduo.system.model.Language;

/**
 * 语言管理业务处理接口
 * @author chengesheng@gmail.com
 * @date 2014-6-2 下午10:26:09
 * @version 1.0.0
 */
public interface LanguageManager {

	/**
	 * 根据Id获取语言
	 * @param id
	 * @return
	 */
	public Language getById(String id);

	/**
	 * 根据Name获取语言
	 * @param name
	 * @return
	 */
	public Language getByName(String name);

	/**
	 * 根据i18nTag获取语言
	 * @param i18nTag
	 * @return
	 */
	public Language getByI18nTag(String i18nTag);

	/**
	 * 获取所有语言
	 * @return
	 */
	public List<Language> listAll();

	/**
	 * 获取所有启用语言
	 * @return
	 */
	public List<Language> listAllEnable();

	/**
	 * 创建语言
	 * @param language
	 * @return
	 */
	public Language create(final Language language);

	/**
	 * 修改语言
	 * @param language
	 */
	public void update(Language language);

	/**
	 * 删除语言
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 分页查询语言列表（关键字模糊查询，模糊查询内容：语言标识、名称）
	 * @param searchKey
	 * @param page
	 * @return
	 */
	public Page<Language> pagingList(String searchKey, Page<Language> page);

	/**
	 * 分页查询语言列表（模糊查询，条件为：语言标识、名称）
	 * @param name 名称
	 * @param i18nTag 关键标识
	 * @param page
	 * @return
	 */
	public Page<Language> pagingList(String name, String i18nTag, Page<Language> page);
}
