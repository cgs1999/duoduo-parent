package com.duoduo.system.service;

import java.util.List;

import com.duoduo.core.vo.Page;
import com.duoduo.system.vo.ParameterVO;

/**
 * 参数管理业务处理接口
 * @author chengesheng@gmail.com
 * @date 2014-6-2 下午10:26:09
 * @version 1.0.0
 */
public interface ParameterService {

	/**
	 * 根据Id获取参数
	 * @param id
	 * @return
	 */
	public ParameterVO getById(String id);

	/**
	 * 根据Key获取参数
	 * @param key
	 * @return
	 */
	public ParameterVO getByKey(String key);

	/**
	 * 根据Type获取参数
	 * @param type
	 * @return
	 */
	public List<ParameterVO> listByType(String type);

	/**
	 * 创建参数
	 * @param parameterVO
	 * @return
	 */
	public ParameterVO create(final ParameterVO parameterVO);

	/**
	 * 修改参数
	 * @param parameterVO
	 */
	public void update(ParameterVO parameterVO);

	/**
	 * 删除参数
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 分页查询参数列表（关键字模糊查询，模糊查询内容：关键字、名称、值）
	 * @param searchKey
	 * @param page
	 * @return
	 */
	public Page<ParameterVO> pagingList(String searchKey, Page<ParameterVO> page);

	/**
	 * 分页查询参数列表（模糊查询，条件为：关键字、名称、值）
	 * @param key 关键字
	 * @param name 名称
	 * @param value 值
	 * @param page
	 * @return
	 */
	public Page<ParameterVO> pagingList(String key, String name, String value, Page<ParameterVO> page);
}
