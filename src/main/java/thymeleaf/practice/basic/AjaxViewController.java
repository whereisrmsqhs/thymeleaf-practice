package thymeleaf.practice.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import thymeleaf.practice.domain.quizType1.quizType1EachQuestion;

@Slf4j
@Controller
public class AjaxViewController {

    @PostMapping("/load-quiz1")
    public @ResponseBody quizType1EachQuestion question(@ModelAttribute quizType1EachQuestion receivedQuestion){

        receivedQuestion.setAnswer("손흥민");
        return receivedQuestion;
    }
}
