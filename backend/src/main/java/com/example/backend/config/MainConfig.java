package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class MainConfig {
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    // ** QueryDSL 사용을 위한 설정
    // => EntityManager
    // => JPAQueryFactory를 Bean 등록하여 프로젝트 전역에서 QueryDSL을 작성할 수 있도록 함.
	@PersistenceContext //entityManager 전용생성 (autowired같은 역할)
	// => EntityManager (i) 객체 주입 애너테이션
	// => SpringBoot JPA 에서는 엔티티 매니저 팩토리 관련 부분을 작성하지 않아도 생성 & 주입 해줌
	private EntityManager entityManager;
	@Bean
	JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}

}

// QueryDSL 관련
// https://velog.io/@kimsundae/Gradle-SpringBoot-3.x-QueryDSL-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0