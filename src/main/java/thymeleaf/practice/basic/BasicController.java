package thymeleaf.practice.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.practice.Quiz1;
import thymeleaf.practice.ThumbnailInfo;
import thymeleaf.practice.domain.QuizIntro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {


    @GetMapping("/main-page")
    public String mainPage(Model model) {
        ThumbnailInfo Aimyon = new ThumbnailInfo("Aimyon's music", "Aimyon", "3.4M views &#183; 6 months ago");

        QuizIntro quizIntro = new QuizIntro();
        quizIntro.setQuizName("로고보고 선수 맞추기");
        quizIntro.setQuizDescription("선수가 거쳐간 팀로고들을 보고 어떤 선수인지 맞춰주세요!");
        model.addAttribute("quizIntro", quizIntro);

        return "footballQuiz/main-pageV1";
    }

    @GetMapping("/quiz1-page-1")
    public String quiz1(Model model) {
        Quiz1 quiz1 = Quiz1.getInstance();

        quiz1.save("함부르크SV");
        quiz1.save("바이어 04 레버쿠젠");
        quiz1.save("토트넘 훗스퍼");
        quiz1.setAnswer("손흥민");
        quiz1.setTeamNum(3);

        model.addAttribute("quiz1", quiz1);

        return "footballQuiz/quiz1-page-1";
    }

    // 오류, 안됨.
    @ResponseBody
    @PostMapping("/quiz1-club-logo")
    public String quiz1(@PathVariable String answer, Model model){
        Quiz1 quiz1 = Quiz1.getInstance();

        quiz1.save("함부르크SV");
        quiz1.save("바이어 04 레버쿠젠");
        quiz1.save("토트넘 훗스퍼");
        quiz1.setAnswer("손흥민");
        quiz1.setTeamNum(3);

        if(answer.equals("손흥민")){
            return "정답입니다!!";
        } else {
            return "틀렸습니다...";
        }
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
