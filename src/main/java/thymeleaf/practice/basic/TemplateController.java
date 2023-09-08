package thymeleaf.practice.basic;

import org.springframework.web.bind.annotation.GetMapping;

public class TemplateController {

    @GetMapping("/fragment")
    public String template(){
        return "template/fragment/fragmentMain";
    }
}
