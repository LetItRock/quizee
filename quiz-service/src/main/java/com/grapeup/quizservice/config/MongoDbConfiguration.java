package com.grapeup.quizservice.config;

import com.grapeup.quizservice.domain.Question;
import com.grapeup.quizservice.domain.Quiz;
import com.grapeup.quizservice.domain.pojo.Answer;
import com.grapeup.quizservice.domain.pojo.Option;
import com.grapeup.quizservice.repository.QuestionRepository;
import com.grapeup.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class MongoDbConfiguration {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Bean
    public CommandLineRunner fillDatabase() {
        return strings -> {
            List<String> labelsInDB = createLabelsInDB();
            List<Question> questionsInDB = createQuestionsInDB(labelsInDB);
            createQuizzesInDB(questionsInDB, labelsInDB);
        };
    }

    private void createQuizzesInDB(List<Question> questionsInDB, List<String> labelsInDB) {
        long count = quizRepository.count();
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Donec pharetra rhoncus mauris non tristique. " +
                "Etiam a eros convallis, pulvinar nisi in, accumsan tellus. " +
                "Nulla rhoncus mi quam, id molestie quam consectetur sed. " +
                "Fusce vel tortor efficitur, malesuada velit vel, eleifend tellus. " +
                "Suspendisse urna est, faucibus eget luctus at, varius eget elit. " +
                "Sed consectetur magna nulla, ac sagittis leo blandit vel. " +
                "Nam fringilla dui ut arcu consectetur luctus. " +
                "Duis id mollis nulla. " +
                "Suspendisse auctor leo vel orci facilisis, ac molestie justo facilisis. ";
        if (count <= 0) {
            for (int i = 0; i < 10; i++) {
                String uuid = UUID.randomUUID().toString();
                Quiz quiz = Quiz.builder()
                        .name(uuid)
                        .shortDescription("Short description " + uuid)
                        .description("Description " + lorem + uuid)
                        .icon("")
                        .duration(1800000) // 30 min
                        .points(ThreadLocalRandom.current().nextInt(1, 10 + 1))
                        .active(true)
                        .showResult(true)
                        .questions(questionsInDB)
                        .labels(labelsInDB)
                        .build();
                quizRepository.save(quiz);
            }
        }
    }

    private List<Question> createQuestionsInDB(List<String> labelsInDB) {
        List<Question> result = new ArrayList<>();
        long questionsCount = questionRepository.count();
        if (questionsCount <= 0) {
            Question markdownQuestion =
                    Question
                    .builder()
                    .body("```\\n<ul>\\n  {items.map((item, key) => <li key={key}>{item}</li>}\\n</ul>\\n```\\n\\nIt looks like:")
                    .isMarkdownEnabled(true)
                    .type(Question.Type.SINGLE)
                    .options(Arrays.asList(
                            Option.builder().order(1).text("AngularJS").build(),
                            Option.builder().order(2).text("JSX in React").build(),
                            Option.builder().order(3).text("Simple HTML").build(),
                            Option.builder().order(4).text("Angular 2").build(),
                            Option.builder().order(5).text("Ember").build()
                            )
                    )
                    .points(10)
                    .answers(Collections.singletonList(Answer.builder().order(2).stringAnswer("JSX in React").build()))
                    .explanation("React uses HTML like templates in JS.")
                    .labels(labelsInDB)
                    .build();
            markdownQuestion.setId("1");
            result.add(questionRepository.save(markdownQuestion));

            Question multipleQuestion =
                    Question
                    .builder()
                    .body("Select all correct options that allow to check if a variable vRast is defined.")
                    .isMarkdownEnabled(false)
                    .type(Question.Type.MULTIPLE)
                    .options(Arrays.asList(
                            Option.builder().order(1).text("if (vRast.defined) {}").build(),
                            Option.builder().order(2).text("if (typeof vRast == \"undefined\") {}").build(),
                            Option.builder().order(3).text("if (typeof vRast != null) {}").build(),
                            Option.builder().order(4).text("if (typeof vRast == undefined) {}").build(),
                            Option.builder().order(5).text("if (vRast) {}").build()
                            )
                    )
                    .points(10)
                    .answers(Arrays.asList(
                            Answer.builder().order(3).stringAnswer("if (typeof vRast != null) {}").build(),
                            Answer.builder().order(5).stringAnswer("if (vRast) {}").build()
                    ))
                    .explanation("React uses HTML like templates in JS.")
                    .labels(labelsInDB)
                    .build();
            multipleQuestion.setId("2");
            result.add(questionRepository.save(multipleQuestion));
        }
        return result;
    }

    private List<String> createLabelsInDB() {
        List<String> result = new ArrayList<>();
        result.add("JavaScript");
        result.add("Java");
        return result;
    }

}
