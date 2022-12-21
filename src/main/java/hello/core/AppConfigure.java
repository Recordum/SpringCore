package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfigure {
    /* 실제 구현객체를 생성하고 생성자 주입
    *  의존관계에 대한 고민을 AppConfigure 가 담당.
    *  DIP 완성 -> 구현체는 추상에만 의존하면 된다.
    *  역확과 구현이 드러나도록 리팩토링*/
    public MemberService memberService(){
        return new MemberServiceImpl(memeberRepository());
    }

    private MemberRepository memeberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memeberRepository(), discontPolicy());
    }

    private DiscountPolicy discontPolicy() {
        return new FixDiscountPolicy();
    }


}
