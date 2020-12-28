package com.hs.yourfit.common.annotation;

import com.hs.yourfit.common.model.BaseEntity;
import com.hs.yourfit.core.config.JpaAuditConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = ASSIGNABLE_TYPE,
        classes = {JpaAuditConfig.class, BaseEntity.class})) //Jpa에 필요한 클래스들만 로딩이 되어 좀 더 빠르게 테스트를 할 수 있다는 장점을 가짐
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //메모리 DB가 아닌 실 DB에서 테스트 가능
public @interface RepositoryTest {
}
