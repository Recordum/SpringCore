package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //필드 주입 Test 시 외부에서 더미 객체를 만들기가 어려움(안티패턴)
/*    @Autowired */ private final MemberRepository memberRepository;
/*    @Autowired */ private final DiscountPolicy discountPolicy; //DIP를 지키기 위해 인터페이스에만 의존.


    @Autowired //생성자 주입 (생성자가 1개만 존재시, @Autowired 생략가능.)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트용

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
