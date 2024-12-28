package com.hello.core.beanDefinition;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hello.core.AutoAppConfig;

public class BeanDefinitionTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class); 
//	GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
	
	@Test
	@DisplayName("빈 설정 메타정보 확인")
	public void findApplicationBean() {
		Stream.of(ac.getBeanDefinitionNames())
			.filter(beanName -> ac.getBeanDefinition(beanName).getRole() == BeanDefinition.ROLE_APPLICATION)
			.map(beanName -> String.format("Bean Name : %s [Bean : %s]", beanName, ac.getBeanDefinition(beanName)))
			.forEach(System.out::println);
	}
	
	
}
