package ${packageName}.service;

import java.util.List;

import com.duoduo.core.vo.Page;
import ${packageName}.vo.${beanName}VO;

/**
 * TODO 管理业务处理接口
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
public interface ${beanName}Service {

	/**
	 * 根据Id获取
	 * @param id
	 * @return
	 */
	public ${beanName}VO getById(String id);

	/**
	 * 根据name获取
	 * @param name
	 * @return
	 */
	public ${beanName}VO getByName(String name);

	/**
	 * 获取所有
	 * @return
	 */
	public List<${beanName}VO> listAll();

	/**
	 * 创建
	 * @param ${beanName?uncap_first}VO
	 * @return
	 */
	public ${beanName}VO create(final ${beanName}VO ${beanName?uncap_first}VO);

	/**
	 * 修改
	 * @param ${beanName?uncap_first}VO
	 */
	public void update(${beanName}VO ${beanName?uncap_first}VO);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 分页查询列表（关键字模糊查询，模糊查询内容：名称、TODO）
	 * @param searchKey
	 * @param page
	 * @return
	 */
	public Page<${beanName}VO> pagingList(String searchKey, Page<${beanName}VO> page);
}
