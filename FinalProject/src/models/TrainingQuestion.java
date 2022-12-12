package models;

import java.util.Objects;

public class TrainingQuestion {

    private int id;
    private String question;
    private boolean answer;
    private Integer trainingModuleId;

    public TrainingQuestion(int id, String question, boolean answer, Integer trainingModuleId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.trainingModuleId = trainingModuleId;
    }

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

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
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
        return id == that.id && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(trainingModuleId, that.trainingModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, trainingModuleId);
    }
}
