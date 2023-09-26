package thymeleaf.practice;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Quiz1 {
    private Map<String, Object> teams = new LinkedHashMap<>();
    private Integer teamNum;
    private String answer;

    public void save(String teamName) {
        teams.put(teamName, teamName);
    }
}
