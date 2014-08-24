package com.duoduo.system.controller;

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
import com.duoduo.system.service.ParameterService;
import com.duoduo.system.vo.ParameterVO;

/**
 * 参数管理Controller
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午2:30:09
 * @version 1.0.0
 */
@Controller
@RequestMapping("/system/parameter")
public class ParameterController {

	@Resource
	private ParameterService parameterService;

	private String listPage = "parameter/parameter-list";
	private String formPage = "parameter/parameter-form";

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
	public void getPageList(HttpServletResponse response, Page<ParameterVO> page,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "value", required = false) String value) {
		page = parameterService.pagingList(key, name, value, page);
		ResponseUtils.renderJson(response, page);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletResponse response, ParameterVO parameterVO) {
		ResponseUtils.renderJson(response, save(parameterVO));
	}

	private void load(ModelMap model, String id) {
		ParameterVO parameterVO = parameterService.getById(id);

		model.addAttribute("data", parameterVO);
	}

	private Message<String> save(ParameterVO parameterVO) {
		Message<String> message = new Message<String>(true, "保存成功");

		int nResult = -1;
		if (parameterVO != null) {
			if (parameterVO.getId() != null && parameterVO.getId().longValue() != 0) {
				parameterService.update(parameterVO);
				nResult = 1;
			} else {
				parameterVO = parameterService.create(parameterVO);
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
