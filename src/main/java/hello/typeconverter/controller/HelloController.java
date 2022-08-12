package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
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

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
/* 스프링 타입 컨버터가 적용되는 예*/
// 스프링 MVC 요청 파라미터 @RequestParam, @ModelAttribute, @PathVariable
// @Value 등으로 YML 정보 읽기
// XML 에 넣은 스프링 빈 정보를 변환
// 뷰 렌더링

// Boolean 타입을 숫자로 변경하는등 여러 타입 변환을 지원한다.
/* 타입 컨버터 Converter */
// org.springFramework.core.convert.converter.Converter
// 새 타입을 만들어 변환하고 싶을때는 컨버터 인터페이스를 사용한다.
// 컨버터 인터페이스를 구현해서 등록하면 된다.
// A -> B 타입 변환, B -> A 타입 변환 각각 만들어 등록하면 된다.

// 참고
// 과거에는 PropertyEditor 라는 것으로 타입변환했다.
// PropertyEditor 는 동시성 문제가 있어서 변환시마다 객체를 새로 생성해야 하는 단점이 있다.
// 지금은 Converter 로 해당 문제가 해결됐고, 기능확장이 필요하면 Converter 를 사용하면 된다.
