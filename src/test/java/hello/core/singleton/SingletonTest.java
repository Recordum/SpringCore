package hello.core.singleton;

import hello.core.AppConfigure;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfigure appConfigure   = new AppConfigure();
        //1.조회 :호출할때마다 객체 생성
        MemberService memberService1 = appConfigure.memberService();
        //2.조회 :호출할때마다 객체 생성
        MemberService memberService2 = appConfigure.memberService();

        //참조값이 다른것을 확인 
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2 확인.
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("singleton 패턴 적용 객체 사용")
    void singletonServiceTest () {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
        //same : instance 비교
        //equal : 값 비교
    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SpringContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigure.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 같은것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 = memberService2 확인.
        assertThat(memberService1).isSameAs(memberService2);
    }
}
