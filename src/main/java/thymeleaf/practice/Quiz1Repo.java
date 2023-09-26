package thymeleaf.practice;

import java.util.HashMap;
import java.util.Map;

public class Quiz1Repo {
    private Map<String, Quiz1> store = new HashMap<>();

    private static Quiz1Repo quiz1Repo = new Quiz1Repo();

    private Quiz1Repo() {
    }

    public static Quiz1Repo getInstance(){
        return quiz1Repo;
    }

    public Quiz1 save(Quiz1 quiz1){
        store.put(quiz1.getAnswer(), quiz1);
        return quiz1;
    }
}
