package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model) {

        Form form = new Form();
        form.setNumber(100000);
        form.setLocalDateTime(LocalDateTime.now());

        model.addAttribute("form", form);
        return "formatter-form";
    }

    @PostMapping("/formatter/edit")
    public String formatterEdit(@ModelAttribute Form form) {
        return "formatter-view";
    }

    @Data
    static class Form {

        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }

}
/* 스프링이 제공하는 기본 포맷터 */
// 스프링은 자바에서 제공하는 타입들에대해 수 많은 포맷터를 기본으로 제공한다.
// 그런데 포맷터는 기본 형식이 지정되어 있기 때문에, 객체의 각 필드마다 다른 형식으로 포맷을 지정하기 어렵다.
// 스프링은 이런 문제를 해결하기 위해 어노테이션 기반으로 원하는 형식을 지정해서 사용할 수 있는 매우 유용한 포맷터 두 가지를 기본으로 제공한다.

// @NumberFormat : 숫자 관련 형식 지정 포맷터 사용, NumberFormatAnnotationFormatterFactory
// @DataTimeFormat : 날짜 관련 형식 지정 포맷터 사용, Jsr310DateTimeFormatAnnotationFormatterFactory

// 정리
// 컨버터를 사용하든, 포맷터를 사용하든 등록 방법은 다르지만,
// 사용시에는 컨버전 서비스를 통해서 일관성 있게 사용할 수 있다.

// 주의
// 메시지 컨버터 (HttpMessageConverter) 에는 컨버전 서비스가 적용되지 않는다.
// HttpMessageConverter 의 역할은 HTTP 메시지 바디의 내용을 객체로 변환하거나,
// 객체를 HTTP 메시지 바디에 입력하는 것이다.

// 예를들어 JSON 을 객체로 변환할 때 메시지 컨버터는 내부에서 Jackson 라이브러리로 변환한다.
// 즉, 변환시의 결과는 라이브러리에 달려있다.
// 따라서 JSON 결과로 만들어지는 숫자나 날짜 포맷을 변경하고 싶으면 해당 라이브러리가 제공하는 설정을 통해야 한다.
// 즉 메시지 컨버터는 컨버전 서비스와 전혀 관계가 없다.

// 컨버전 서비스는 @RequestParam, @ModelAttribute, @PathVariable, 뷰 템플릿 등에서 사용할 수 있다.
