package com.duoduo.system.service;

import java.util.List;

import com.duoduo.core.vo.Page;
import com.duoduo.system.vo.LanguageVO;

/**
 * 语言管理业务处理接口
 * @author chengesheng@gmail.com
 * @date 2014-6-2 下午10:26:09
 * @version 1.0.0
 */
public interface LanguageService {

	/**
	 * 根据Id获取语言
	 * @param id
	 * @return
	 */
	public LanguageVO getById(String id);

	/**
	 * 根据name获取语言
	 * @param name
	 * @return
	 */
	public LanguageVO getByName(String name);

	/**
	 * 根据i18nTag获取语言
	 * @param i18nTag
	 * @return
	 */
	public LanguageVO getByI18nTag(String i18nTag);

	/**
	 * 获取所有语言
	 * @return
	 */
	public List<LanguageVO> listAll();

	/**
	 * 获取所有启用语言
	 * @return
	 */
	public List<LanguageVO> listAllEnable();

	/**
	 * 创建语言
	 * @param languageVO
	 * @return
	 */
	public LanguageVO create(final LanguageVO languageVO);

	/**
	 * 修改语言
	 * @param languageVO
	 */
	public void update(LanguageVO languageVO);

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
	public Page<LanguageVO> pagingList(String searchKey, Page<LanguageVO> page);

	/**
	 * 分页查询语言列表（模糊查询，条件为：名称、语言标识）
	 * @param name 名称
	 * @param i18nTag 语言标识
	 * @param page
	 * @return
	 */
	public Page<LanguageVO> pagingList(String name, String i18nTag, Page<LanguageVO> page);
}
