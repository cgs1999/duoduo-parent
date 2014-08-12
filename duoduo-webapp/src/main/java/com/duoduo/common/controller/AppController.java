package com.duoduo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	private static Logger log = LoggerFactory.getLogger(AppController.class);

	@RequestMapping(value = "/home")
	public String index() {
		// log.info("index.......");
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// WebAuthenticationDetails webAuth = (WebAuthenticationDetails) auth.getDetails();
		// log.info("当前登录用户ip:" + webAuth.getRemoteAddress());
		// log.info("当前登录用户的sessionId:" + webAuth.getSessionId());
		// User user = (User) auth.getPrincipal();
		//
		// log.info("当前登录用户权限：" + user.getAuthorities());
		return "home";
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
