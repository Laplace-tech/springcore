package com.hello.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.core.common.MyLogger;
import com.hello.core.logdemo.LogDemoService;

import jakarta.inject.Provider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LogDemoController {

	private final LogDemoService logDemoService;
	
	private final MyLogger myLogger;
	
//	private final Provider<MyLogger> loggerProvider;
//  private final ObjectProvider<MyLogger> loggerProvider;
	
	@ResponseBody
	@GetMapping("log-demo")
	public String logDemo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		
//		MyLogger myLogger = loggerProvider.get();
//		MyLogger myLogger = loggerProvider.getObject();
		
		System.out.println("myLogger : " + myLogger.getClass());
		myLogger.setRequestURL(requestURL);
		
		myLogger.log("controller test");
		logDemoService.logic("testID");
		
		return "OK";
	}
	
}
