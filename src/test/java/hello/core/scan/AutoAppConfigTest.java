package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class AutoAppConfigTest {
    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderServiceImpl.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
        memberService.join(new Member(1L, "min gyu" , Grade.VIP));
        Order order = bean.createOrder(1L, "셔츠", 20000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000); // @Primary 보다 @Qualifier 가 우선권
    }
}
