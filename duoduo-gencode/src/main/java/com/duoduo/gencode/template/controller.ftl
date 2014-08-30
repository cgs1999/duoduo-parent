package ${packageName}.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.duoduo.core.util.ResponseUtils;
import com.duoduo.core.vo.Message;
import com.duoduo.core.vo.Page;
import ${packageName}.${beanName}Service;
import ${packageName}.vo.${beanName}VO;

/**
 * ${beanName}Controller TODO
 * @author chengesheng@gmail.com
 * @date ${currentDateTime}
 * @version 1.0.0
 */
@Controller
@RequestMapping("/system/${beanName?uncap_first}") // TODO
public class ${beanName}Controller {

	@Resource
	private ${beanName}Service ${beanName?uncap_first}Service;

	private String listPage = "${beanName?uncap_first}/${beanName?uncap_first}-list";
	private String formPage = "${beanName?uncap_first}/${beanName?uncap_first}-form";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		return listPage;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {
		return formPage;
	}

	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public String form(ModelMap model, @PathVariable String id) {
		if (StringUtils.hasText(id)) {
			load(model, id);
		}
		return formPage;
	}

	@RequestMapping(value = "/getPageList", method = RequestMethod.POST)
	public void getPageList(HttpServletResponse response, Page<${beanName}VO> page, String searchKey) {
		page = ${beanName?uncap_first}Service.pagingList(name, searchKey);
		ResponseUtils.renderJson(response, page);
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.POST)
	public void listAll(HttpServletResponse response) {
		ResponseUtils.renderJson(response, ${beanName?uncap_first}Service.listAll());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletResponse response, ${beanName}VO ${beanName?uncap_first}VO) {
		ResponseUtils.renderJson(response, save(${beanName?uncap_first}VO));
	}

	private void load(ModelMap model, String id) {
		${beanName}VO ${beanName?uncap_first}VO = ${beanName?uncap_first}Service.getById(id);

		model.addAttribute("data", ${beanName?uncap_first}VO);
	}

	private Message<String> save(${beanName}VO ${beanName?uncap_first}VO) {
		Message<String> message = new Message<String>(true, "保存成功");

		int nResult = -1;
		if (${beanName?uncap_first}VO != null) {
			if (${beanName?uncap_first}VO.getId() != null && ${beanName?uncap_first}VO.getId().longValue() != 0) {
				${beanName?uncap_first}Service.update(${beanName?uncap_first}VO);
				nResult = 1;
			} else {
				${beanName?uncap_first}VO = ${beanName?uncap_first}Service.create(${beanName?uncap_first}VO);
				nResult = 2;
			}
		}

		if (nResult < 0) {
			message.setSuccess(false);
			message.setDescription("保存失败，请联系管理员!");
		} else {
			message.setData(Integer.toString(nResult));
		}

		return message;
	}
}