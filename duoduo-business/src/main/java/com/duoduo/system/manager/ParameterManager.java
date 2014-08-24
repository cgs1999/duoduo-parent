package com.duoduo.system.manager;

import java.util.List;

import com.duoduo.core.vo.Page;
import com.duoduo.system.model.Parameter;

/**
 * 参数管理业务处理接口
 * @author chengesheng@gmail.com
 * @date 2014-6-2 下午10:26:09
 * @version 1.0.0
 */
public interface ParameterManager {

	/**
	 * 根据Id获取参数
	 * @param id
	 * @return
	 */
	public Parameter getById(String id);

	/**
	 * 根据Key获取参数
	 * @param account
	 * @return
	 */
	public Parameter getByKey(String key);

	/**
	 * 根据Type获取参数
	 * @param account
	 * @return
	 */
	public List<Parameter> listByType(String type);

	/**
	 * 创建参数
	 * @param parameter
	 * @return
	 */
	public Parameter create(final Parameter parameter);

	/**
	 * 修改参数
	 * @param parameter
	 */
	public void update(Parameter parameter);

	/**
	 * 删除参数
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 分页查询参数列表（关键字模糊查询，模糊查询内容：关键标识、名称、值）
	 * @param searchKey
	 * @param page
	 * @return
	 */
	public Page<Parameter> pagingList(String searchKey, Page<Parameter> page);

	/**
	 * 分页查询参数列表（模糊查询，条件为：关键标识、名称、值）
	 * @param key 关键标识
	 * @param name 姓名
	 * @param value 值
	 * @param page
	 * @return
	 */
	public Page<Parameter> pagingList(String key, String name, String value, Page<Parameter> page);
}
