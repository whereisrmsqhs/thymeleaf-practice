package thymeleaf.practice.domain.quizType1;

import java.util.HashMap;
import java.util.Map;

public class quizType1Repo {
    private Map<String, quizType1EachQuestion> store = new HashMap<>();

    private static quizType1Repo quizType1Repo = new quizType1Repo();

    private quizType1Repo() {
    }

    public static quizType1Repo getInstance(){
        return quizType1Repo;
    }

    public quizType1EachQuestion save(quizType1EachQuestion quizType1EachQuestion){
        store.put(quizType1EachQuestion.getAnswer(), quizType1EachQuestion);
        return quizType1EachQuestion;
    }
}
