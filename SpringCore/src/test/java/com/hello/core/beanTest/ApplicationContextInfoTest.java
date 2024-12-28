package com.hello.core.beanTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AutoAppConfig;

public class ApplicationContextInfoTest {

	private AnnotationConfigApplicationContext ac = 
			new AnnotationConfigApplicationContext(AutoAppConfig.class);

	@Test
	@DisplayName("모든 빈 출력")
	public void findAllBean() {
		Stream.of(ac.getBeanDefinitionNames())
			.map(beanName -> String.format("Bean Name : %s [Bean : %s]", beanName, ac.getBean(beanName)))
			.forEach(System.out::println);
	}
	
	@Test
	@DisplayName("애플리케이션 빈 출력")
	public void findApplicationBean() {
		Stream.of(ac.getBeanDefinitionNames())
		.filter(beanName -> ac.getBeanDefinition(beanName).getRole() == BeanDefinition.ROLE_APPLICATION)
		.map(beanName -> String.format("Bean Name : %s [Bean : %s]", beanName, ac.getBean(beanName)))
		.forEach(System.out::println);
	}
	
	@Test
	@DisplayName("인프라스트럭쳐 빈 출력")
	public void findInfraStructureBean() {
		Stream.of(ac.getBeanDefinitionNames())
		.filter(beanName -> ac.getBeanDefinition(beanName).getRole() == BeanDefinition.ROLE_INFRASTRUCTURE)
		.map(beanName -> String.format("Bean Name : %s [Bean : %s]", beanName, ac.getBean(beanName)))
		.forEach(System.out::println);
	}
	
}
