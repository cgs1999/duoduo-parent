package com.duoduo.system.vo;

import com.duoduo.core.util.DateUtils;
import com.duoduo.core.vo.BaseVO;
import com.duoduo.system.model.Parameter;

/**
 * 参数配置
 * @author chengesheng
 * @date 2014-3-24 下午5:06:24
 */
public class ParameterVO extends BaseVO {

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

	/**
	 * 将Entity封装为VO
	 * @return
	 */
	public static ParameterVO fromEntity(Parameter entity) {
		ParameterVO vo = new ParameterVO();
		vo.setId(entity.getId());
		vo.setName(entity.getName());
		vo.setKey(entity.getKey());
		vo.setType(entity.getType());
		vo.setValue(entity.getValue());
		vo.setMemo(entity.getMemo());
		vo.setCreateTime(DateUtils.toDatetimeString(entity.getCreateTime()));
		vo.setUpdateTime(DateUtils.toDatetimeString(entity.getUpdateTime()));
		return vo;
	}

	/**
	 * 将VO封装为Entity
	 * @return
	 */
	public static Parameter toEntity(ParameterVO vo) {
		Parameter entity = new Parameter();
		entity.setId(vo.getId());
		entity.setName(vo.getName());
		entity.setKey(vo.getKey());
		entity.setType(vo.getType());
		entity.setValue(vo.getValue());
		entity.setMemo(vo.getMemo());
		return entity;
	}
}
