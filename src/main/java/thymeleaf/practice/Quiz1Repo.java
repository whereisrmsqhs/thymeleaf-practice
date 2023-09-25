package thymeleaf.practice;

import java.util.HashMap;
import java.util.Map;

public class Quiz1Repo {
    private static final Map<String, Quiz1> store = new HashMap<>();

    public Quiz1 save(Quiz1 quiz1){
        store.put(quiz1.getAnswer(), quiz1);
        return quiz1;
    }
}
