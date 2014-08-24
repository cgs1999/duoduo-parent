package com.duoduo.system.model;

import com.duoduo.core.model.IdEntity;

/**
 * 参数配置实体类
 * @author chengesheng
 * @date 2014-3-10 上午11:20:53
 * @note 保存系统运行分散的配置信息，可通过type来控制参数的分类
 */
public final class Parameter extends IdEntity {

	private static final long serialVersionUID = -8118955356716154090L;

	/** 参数主键 */
	private String key;
	/** 参数对应值 */
	private String value;
	/** 参数类型 */
	private String type;
	/** 备注 */
	private String memo;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
