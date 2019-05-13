package com.coodman.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
		 * <b>开发者控制平台<b>
		 * @author Admin
		 * @since 2019-05-09
		 */
import com.coodman.app.base.controller.BaseController;
import com.coodman.app.beans.entity.Developer;
import com.coodman.app.service.DevService;
import com.coodman.app.util.MD5Util;
@Controller("devController")
@RequestMapping("/dev")
public class DevController extends BaseController{
	@Resource(name = "devService")
	private DevService devService;
	/**
	 * <b>加载App开发者注册页面<b>
	 * @return String
	 */
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String registerForm() throws Exception {
		return "dev/dev_register";
	}
	/**
	 * 开发者进行注册
	 * @param dev
	 * @return Map
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> devRegister(Developer dev) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//校验用户所填写的信息是否有效
		boolean flag = dev.getDevName()!= null && !"".equals(dev.getDevName().trim())
				&& dev.getPassword() != null && !"".equals(dev.getPassword().trim())
				&& dev.getCellphone() != null && !"".equals(dev.getCellphone().trim())
				&& dev.getEmail() != null && !"".equals(dev.getEmail().trim());
		if (flag) {
			//用户填写的信息有效进行保存
			dev.setPassword(MD5Util.encrypt(dev.getPassword()));
			dev = devService.saveDevInfo(dev);
			if (dev != null) {
				resultMap.put("result", true);
				resultMap.put("devNo", dev.getDevNo());
				return resultMap;
			}
		}
		resultMap.put("result", false);
		return resultMap;
	}
	
	
	/**
	 * <b>APP开发者登录页面<b>
	 * @return dev/dev_login
	 * @since 2019-05-09
	 */
	@RequestMapping(value = "/devLogin",method=RequestMethod.GET)
	public String getLoginForm() throws Exception{
		return "dev/dev_login";
	}
	/**
	 * 开发者进行登录
	 * @param dev
	 * @return 
	 */
	@RequestMapping(value = "/devLogin" ,method = RequestMethod.POST)
	public String loginDev(Developer dev) throws Exception{
		//判断用户是否填入有效的登录信息
		if (dev.getDevNo() != null && !"".equals(dev.getDevNo().trim()) && 
				dev.getPassword() !=  null && !"".equals(dev.getPassword().trim())) {
			//对密码进行加密
			String password = MD5Util.encrypt(dev.getPassword());
			//验证用户填入了有效的登录信息，可以进行下一步登录
			//根据当前开发者编号，使用业务层查询是否有对应的开发者信息
			dev = devService.getByDevNo(dev.getDevNo());
			//如果对象存在，那么比较密码是否相等
			if (dev != null && dev.getPassword().equals(password)) {
				//登录成功，绑定当前成功用户信息
				session.setAttribute("dev", dev);
				//重定向到列表页面
				return "redirect:../app/list";
				
			}
		}
		return "redirect:devLogin";
	}
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String devList() {
		return "dev/dev_list";
	}


}
