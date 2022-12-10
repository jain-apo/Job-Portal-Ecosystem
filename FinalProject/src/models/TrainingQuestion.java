package models;

import java.util.Objects;

public class TrainingQuestion {
    private int id;
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private Integer trainingModuleId;
    private TrainingModule trainingModuleByTrainingModuleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public Integer getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(Integer trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingQuestion that = (TrainingQuestion) o;
        return id == that.id && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(option1, that.option1) && Objects.equals(option2, that.option2) && Objects.equals(option3, that.option3) && Objects.equals(trainingModuleId, that.trainingModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, option1, option2, option3, trainingModuleId);
    }

    public TrainingModule getTrainingModuleByTrainingModuleId() {
        return trainingModuleByTrainingModuleId;
    }

    public void setTrainingModuleByTrainingModuleId(TrainingModule trainingModuleByTrainingModuleId) {
        this.trainingModuleByTrainingModuleId = trainingModuleByTrainingModuleId;
    }
}
