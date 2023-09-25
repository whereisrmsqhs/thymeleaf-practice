package thymeleaf.practice;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Quiz1 {
    public static Map<String, String> teams = new HashMap<>();
    private Integer teamNum;
    private String answer;

    private static final Quiz1 instance = new Quiz1();
    public static Quiz1 getInstance() {
        return instance;
    }

    public void save(String teamName) {
        teams.put(teamName, teamName);
    }
}
