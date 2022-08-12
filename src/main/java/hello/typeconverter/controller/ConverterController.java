package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) {
        // 컨버터를 뷰에 적용
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.2", 8081));
        return "converter-view";
    }

    @GetMapping("/converter/edit") // IpPort 를 뷰 템플릿 폼에 출력
    public String converterForm(Model model) {
        // 컨버터를 폼에 적용
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);

        model.addAttribute(form);
        return "converter-form";
    }

    @PostMapping("/converter/edit") // 뷰 템플릿 폼의 IpPort 정보를 출력
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "converter-view";
    }

    @Data
    static class Form {
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }

}
