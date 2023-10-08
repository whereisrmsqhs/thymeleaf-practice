package thymeleaf.practice.domain.quizType1;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class quizType1Description {
    private String quizName;
    private String quizDescription;
    private String quizRule;
    private String briefIntro;

    public quizType1Description(String quizName, String quizDescription, String quizRule, String briefIntro) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizRule = quizRule;
        this.briefIntro = briefIntro;
    }
}
