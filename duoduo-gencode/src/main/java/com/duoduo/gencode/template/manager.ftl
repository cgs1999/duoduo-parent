package ${packageName}.manager;

import java.util.List;

import com.duoduo.core.vo.Page;
import ${packageName}.model.${beanName};

/**
 * TODO 业务处理接口
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
public interface ${beanName}Manager {

	/**
	 * 根据Id获取
	 * @param id
	 * @return
	 */
	public ${beanName} getById(String id);

	/**
	 * 根据Name获取
	 * @param name
	 * @return
	 */
	public ${beanName} getByName(String name);

	/**
	 * 获取所有
	 * @return
	 */
	public List<${beanName}> listAll();

	/**
	 * 创建
	 * @param ${beanName?uncap_first}
	 * @return
	 */
	public ${beanName} create(final ${beanName} ${beanName?uncap_first});

	/**
	 * 修改
	 * @param ${beanName?uncap_first}
	 */
	public void update(${beanName} ${beanName?uncap_first});

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 分页查询列表（关键字模糊查询，模糊查询内容：标识、名称）
	 * @param searchKey
	 * @param page
	 * @return
	 */
	public Page<${beanName}> pagingList(String searchKey, Page<${beanName}> page);
}
