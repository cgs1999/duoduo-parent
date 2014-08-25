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
import com.duoduo.system.service.LanguageService;
import com.duoduo.system.vo.LanguageVO;

/**
 * 语言管理Controller
 * @author chengesheng@gmail.com
 * @date 2014-6-3 上午2:30:09
 * @version 1.0.0
 */
@Controller
@RequestMapping("/system/language")
public class LanguageController {

	@Resource
	private LanguageService languageService;

	private String listPage = "language/language-list";
	private String formPage = "language/language-form";

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
	public void getPageList(HttpServletResponse response, Page<LanguageVO> page,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "i18nTag", required = false) String i18nTag) {
		page = languageService.pagingList(name, i18nTag, page);
		ResponseUtils.renderJson(response, page);
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.POST)
	public void listAll(HttpServletResponse response) {
		ResponseUtils.renderJson(response, languageService.listAll());
	}

	@RequestMapping(value = "/listAllEnable", method = RequestMethod.POST)
	public void listAllEnable(HttpServletResponse response) {
		ResponseUtils.renderJson(response, languageService.listAllEnable());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletResponse response, LanguageVO languageVO) {
		ResponseUtils.renderJson(response, save(languageVO));
	}

	private void load(ModelMap model, String id) {
		LanguageVO languageVO = languageService.getById(id);

		model.addAttribute("data", languageVO);
	}

	private Message<String> save(LanguageVO languageVO) {
		Message<String> message = new Message<String>(true, "保存成功");

		int nResult = -1;
		if (languageVO != null) {
			if (languageVO.getId() != null && languageVO.getId().longValue() != 0) {
				languageService.update(languageVO);
				nResult = 1;
			} else {
				languageVO = languageService.create(languageVO);
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
