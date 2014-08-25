package com.duoduo.system.vo;

import com.duoduo.core.util.DateUtils;
import com.duoduo.core.vo.BaseVO;
import com.duoduo.system.model.Language;

/**
 * 语言配置
 * @author chengesheng
 * @date 2014-3-24 下午5:06:24
 */
public class LanguageVO extends BaseVO {

	/** 语言主键 */
	private String i18nTag;
	/** 语言对应值 */
	private Integer orderIndex;
	/** 语言类型 */
	private Integer enable;
	/** 备注 */
	private String memo;

	public String getI18nTag() {
		return i18nTag;
	}

	public void setI18nTag(String i18nTag) {
		this.i18nTag = i18nTag;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 将Entity封装为VO
	 * @return
	 */
	public static LanguageVO fromEntity(Language entity) {
		LanguageVO vo = new LanguageVO();
		vo.setId(entity.getId());
		vo.setName(entity.getName());
		vo.setI18nTag(entity.getI18nTag());
		vo.setOrderIndex(entity.getOrderIndex());
		vo.setEnable(entity.getEnable());
		vo.setMemo(entity.getMemo());
		vo.setCreateTime(DateUtils.toDatetimeString(entity.getCreateTime()));
		vo.setUpdateTime(DateUtils.toDatetimeString(entity.getUpdateTime()));
		return vo;
	}

	/**
	 * 将VO封装为Entity
	 * @return
	 */
	public static Language toEntity(LanguageVO vo) {
		Language entity = new Language();
		entity.setId(vo.getId());
		entity.setName(vo.getName());
		entity.setI18nTag(vo.getI18nTag());
		entity.setOrderIndex(vo.getOrderIndex());
		entity.setEnable(vo.getEnable());
		entity.setMemo(vo.getMemo());
		return entity;
	}
}
