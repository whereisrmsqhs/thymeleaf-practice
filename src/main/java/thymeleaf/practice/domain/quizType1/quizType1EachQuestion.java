package thymeleaf.practice.domain.quizType1;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class quizType1EachQuestion {
    private Map<String, Object> teams = new LinkedHashMap<>();
    private Integer teamNum;
    private String answer;

    public void save(String teamName) {
        teams.put(teamName, teamName);
    }
}
