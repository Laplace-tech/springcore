package com.hello.core.logdemo;

import org.springframework.stereotype.Service;

import com.hello.core.common.MyLogger;

import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LogDemoService {

//	private final Provider<MyLogger> loggerProvider;
	
	private final MyLogger myLogger;
	
	public void logic(String id) {
		myLogger.log("service ID : " + id);
	}
	
}
