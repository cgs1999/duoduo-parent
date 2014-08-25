package com.duoduo.system.model;

import com.duoduo.core.model.IdEntity;

/**
 * 国际化语言包
 * @author chengesheng
 * @date 2014-3-17 上午10:35:25
 */
public class Language extends IdEntity {

	private static final long serialVersionUID = 5263456366349424389L;

	/** 国际化标识，如：中国zh_CN */
	private String i18nTag;
	/** 排序索引 */
	private Integer orderIndex;
	/** 启用禁用标识 */
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

}
