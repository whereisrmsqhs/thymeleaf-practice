package thymeleaf.practice.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thymeleaf.practice.domain.quizType1.quizType1EachQuestion;
import thymeleaf.practice.domain.quizType1.quizType1Repo;
import thymeleaf.practice.domain.quizType1.quizType1Description;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {

    quizType1Repo quizType1Repo = thymeleaf.practice.domain.quizType1.quizType1Repo.getInstance();
    @ModelAttribute("description")
    public quizType1Description description(){
        String quizName = "로고 보고 선수 맞추기";
        String quizDescription = "선수가 거쳐간 팀로고들을 보고 어떤 선수인지 맞춰주세요!\n 문제가 시작되면 화면 중간에 특정 선수가 거쳐간 " +
                "클럽의 로고들이 경력 순서에 맞게 나타납니다. 해당 커리어를 바탕으로 어떤 선수인지 맞춰주세요!";
        String quizRule = "주어진 시간안에 맞히면 1점, 시간이 초과되거나 입력한 답이 틀렸을 시 0점입니다.";
        String briefIntro = "선수가 거쳐간 팀로고들을 보고 어떤 선수인지 맞춰주세요!";
        quizType1Description des = new quizType1Description(quizName, quizDescription, quizRule, briefIntro);
        return des;
    }

    @GetMapping("/main-page")
    public String mainPage(Model model) {

        return "footballQuiz/main-pageV1";
    }

    @GetMapping("/quiz-page1")
    public String quiz1(Model model) {
        quizType1EachQuestion quizType1EachQuestion = new quizType1EachQuestion();

        quizType1EachQuestion.save("함부르크SV");
        quizType1EachQuestion.save("바이어 04 레버쿠젠");
        quizType1EachQuestion.save("토트넘 훗스퍼");
        quizType1EachQuestion.setAnswer("손흥민");
        quizType1EachQuestion.setTeamNum(3);

        model.addAttribute("quiz1", quizType1EachQuestion);

        return "footballQuiz/quiz-page1";
    }

    @PostMapping("/quiz-page1")
    public String quiz1(Model model, @ModelAttribute("quiz1") quizType1EachQuestion quizType1EachQuestion, @RequestParam("userAnswer") String userAnswer){
        if(userAnswer.equals("손흥민")){
            model.addAttribute("answer", true);
        }
        else {
            model.addAttribute("answer", false);
        }
        log.info("userAnswer = {}", userAnswer);
        return "footballQuiz/quiz-page1";
    }

    @GetMapping("/make-quiz1")
    public String makeQuiz1(Model model){
        return "footballQuiz/make-quiz1";
    }

    @PostMapping("/make-quiz1")
    public String makeQuiz1(@RequestParam Map<String, Object> paramMap, RedirectAttributes redirectAttributes){

        quizType1EachQuestion quizType1EachQuestion = new quizType1EachQuestion();
        int teamCount = 0;
        for (String s : paramMap.keySet()) {
            if(s.equals("answer")){
                quizType1EachQuestion.setAnswer((String) paramMap.get(s));
                continue;
            }
            quizType1EachQuestion.save((paramMap.get(s)).toString());
            teamCount++;
        }
        quizType1EachQuestion.setTeamNum(teamCount);
        quizType1Repo.save(quizType1EachQuestion);

        log.info("quiz1={}", quizType1EachQuestion);

        redirectAttributes.addAttribute("answer", quizType1EachQuestion.getAnswer());
        redirectAttributes.addAttribute("status", true);
        return "redirect:quiz1/{answer}";
    }

    @GetMapping("/quiz1/{answer}")
    public String quiz1List(@PathVariable String answer, Model model){
        model.addAttribute("answer", answer);
        return "footballQuiz/quiz1";
    }


    // --------------------------------------------------------------------------------------------------
    // 밑에는 수업 내용, 위에는 내 마음대로 하는 내용
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);

        return "basic/javascript";
    }


    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
}
