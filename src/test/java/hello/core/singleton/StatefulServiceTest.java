package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10,000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B 사용자 20,000원 주문
        statefulService2.order("userA", 20000);

        //ThreadA : A 사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
        //싱글톤 공유필드를 조심해야함. 스프링 빈은 항상 무상태(stateless)로 설계해야 한다.
    }

    static class TestConfig {

        @Bean
        public  StatefulService statefulService() {
            return new StatefulService();
        }
    }

}