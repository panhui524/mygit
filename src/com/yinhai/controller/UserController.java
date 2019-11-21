package com.yinhai.controller;

import java.security.Key;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.BASE64Decoder;

import com.yinhai.bean.User;
import com.yinhai.service.UserService;
import com.yinhai.util.RSACoder;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 登录前加载公钥到session中
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "beforeLogin.action", method = RequestMethod.GET)
	public String beforeLogin(HttpServletRequest request, Model model) {
		try {
		    //初始化密钥
			Map<String, Key> keyMap = RSACoder.initKey();
			//获取
			String publicKey = RSACoder.getPublicKey(keyMap);
			String privateKey = RSACoder.getPrivateKey(keyMap);
			
			//存入session
			HttpSession session = request.getSession();
			session.setAttribute("publicKey", publicKey);
			session.setAttribute("privateKey", privateKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 跳转登录界面
		return "index";
	}
	
	/**
	 * 注册前加载公钥到session中
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "beforeRegister.action", method = RequestMethod.GET)
	public String beforeRegister(HttpServletRequest request, Model model) {
        try {
            //初始化密钥
            Map<String, Key> keyMap = RSACoder.initKey();
            //获取
            String publicKey = RSACoder.getPublicKey(keyMap);
            String privateKey = RSACoder.getPrivateKey(keyMap);
            
            //存入session
            HttpSession session = request.getSession();
            session.setAttribute("publicKey", publicKey);
            session.setAttribute("privateKey", privateKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 跳转登录界面
        return "register";
    }

	/**
	 * 注册
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register.action",method = RequestMethod.GET)
	public String reg(User user, HttpServletRequest request) throws Exception {
	    
	    // 核对验证码
        String verifyCode = request.getParameter("verifyCode");
        String sessionVerifyCode = (String) request.getSession().getAttribute("verifyCodeValue");
        if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
              request.setAttribute("message", "验证码错误");
              return "forward:beforeRegister.action";
        }

	    // 获取 session中的 privateKey
		String privateKey = (String) request.getSession().getAttribute("privateKey");
		BASE64Decoder decoder = new BASE64Decoder();
    	byte[] encodedData = decoder.decodeBuffer(user.getRuPassword());
    	byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,privateKey);
    	String newPwd = new String(decodedData); // 解密后的密码 
    	user.setRuPassword(newPwd);
    	System.out.println(user.toString());
    	boolean boo = userService.register(user);
    	if (boo) {
    		request.setAttribute("user", user);
    		return "forward:beforeLogin.action";
    	} else {
    		request.setAttribute("info", "注册失败");
    		return "forward:beforeRegister.action";
    	}

	}

	/**
	 * 登录
	 * 
	 * @param panhui
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping("login.action")
	public String login(User user, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	    // 核对验证码
	    String verifyCode = request.getParameter("verifyCode");
        String sessionVerifyCode = (String) request.getSession().getAttribute("verifyCodeValue");
        if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
              request.setAttribute("message", "验证码错误");
              return "forward:beforeLogin.action";
        }

		// 获取 session中的 privateKey
		String privateKey = (String) request.getSession().getAttribute("privateKey");
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] encodedData = decoder.decodeBuffer(user.getRuPassword());
		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,privateKey);
		String newPwd = new String(decodedData); 
		user.setRuPassword(newPwd);
		
		ArrayList<User> list = userService.login(user);
		if (null != list && list.size() != 0) {
			request.setAttribute("user", list.get(0));
			return "show";
		} else {
			request.setAttribute("info", "用户名或密码错误");
			return "forward:beforeLogin.action";
		}
	}
	


}
