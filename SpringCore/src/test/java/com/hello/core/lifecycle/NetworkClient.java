package com.hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient // implements InitializingBean, DisposableBean 
{

	private String url;
	
	public NetworkClient() {
		System.out.println("생성자 호출 : " + url);
	}
	
	public void setUrl(String url) {
		System.out.println("set url : " + url);
		this.url = url;
	}
	
	@PostConstruct // 빈 생성(Construct) 이후(Post)
	public void init() {
		System.err.println("NetworkClient.init()");
		connect();
		call("초기화 연결 메세지");
	}
	
	@PreDestroy // 빈 소멸(Destroy) 전(Pre)
	public void close() {
		System.err.println("NetworkClient.close()");
		disconnect();
	}
	
	private void connect() {
		System.out.println("connect : " + url);
	}
	
	private void call(String message) {
		System.out.println("call : " + url + " message : " + message);
	}
	
	private void disconnect() {
		System.out.println("close : " + url);
	}
	
//	@Override // Interface : DisposableBean
//	public void destroy() throws Exception {
//		System.err.println("distroy Call-Back before terminate");
//		disconnect();
//	}
//
//	@Override // Interface : InitializingBean
//	public void afterPropertiesSet() throws Exception {
//		System.err.println("initialize Call-Back");
//		connect();
//		call("초기화 연결 메세지");
//	}
	
}
