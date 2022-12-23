package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    @DisplayName("조회한 빈 모두 호출")
    void findAllBean() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AutoAppConfig.class);
       DiscountService discountService = ac.getBean(DiscountService.class);
        MemberService memberService = ac.getBean(MemberService.class);

       memberService.join(new Member(1l, "mingyu" , Grade.VIP));
        int discountPrice = discountService.discount(memberService.findMember(1L), 20000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(memberService.findMember(1L), 20000, "rateDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPrice).isEqualTo(2000);


   }


    @Component
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }


        public int discount(Member member, int itemPrice, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, itemPrice);
        }
    }
}
