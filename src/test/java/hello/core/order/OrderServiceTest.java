package hello.core.order;

import hello.core.AppConfigure;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class OrderServiceTest {

    MemberService memberService ;
    OrderService orderService ;
    @BeforeEach
    public void beforeEach(){
        AppConfigure appConfigure = new AppConfigure();
        memberService = appConfigure.memberService();
        orderService = appConfigure.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA" , 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
    //필드 주입시 Test 할려면 NullPointerException 이 터짐
  /*  @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService1 = new OrderServiceImpl();
        orderService1.createOrder(1L , "iteamA" ,10000);
    }*/

    //수정자 자동주입 TEST




}
