package com.duoduo.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duoduo.security.context.SessionUser;
import com.duoduo.security.context.UserContext;
import com.duoduo.system.Constants;
import com.duoduo.system.service.ResourceService;
import com.duoduo.system.vo.ResourceVO;

@Controller
public class AppController {

	private Logger log = LoggerFactory.getLogger(getClass());

	private String homePage = "/home";

	@javax.annotation.Resource
	private ResourceService resourceService;

	@RequestMapping("/home")
	public String toHome(HttpServletRequest request, ModelMap model) {
		SessionUser currentUser = UserContext.getCurrentUser();
		List<ResourceVO> allMenus = resourceService.listByUserId("" + currentUser.getUser().getId());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("rootMenuId", Constants.ROOT_MENU_ID);
		model.addAttribute("allMenus", JSONArray.fromObject(allMenus).toString());
		return homePage;
	}

	@RequestMapping(value = "/common")
	public String myJsp() {
		log.info("common.......");
		return "common";
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		log.info("admin.......");
		return "admin";
	}

	@RequestMapping(value = "/getLoggerJson")
	@ResponseBody
	public Logger getUserJson() {
		return log;
	}

	@RequestMapping(value = "/getNullJson")
	@ResponseBody
	public Object getNullJson() {
		return null;
	}
}
