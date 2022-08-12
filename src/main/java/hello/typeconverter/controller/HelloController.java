package hello.typeconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        // 문자 타입 조회
        // HTTP 요청 파라미터는 모두 문자로 처리된다.
        String data = request.getParameter("data");
        // 숫자 타입으로 변환
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        // 스프링의 @RequestParam 을 사용하면 Integer 타입으로 편하게 받을 수 있다.
        // 스프링이 중간에서 타입을 변환시켜 주었기 때문이다.
        // @ModelAttribute , @PathVariable 도 동일하게 적용된다.
        System.out.println("data = " + data);
        return "ok";
    }
}
/* 스프링 타입 컨버터가 적용되는 예*/
// 스프링 MVC 요청 파라미터 @RequestParam, @ModelAttribute, @PathVariable
// @Value 등으로 YML 정보 읽기
// XML 에 넣은 스프링 빈 정보를 변환
// 뷰 렌더링