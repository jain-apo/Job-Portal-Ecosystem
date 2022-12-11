package models;

import java.util.Objects;

public class ModuleData {
    private int id;
    private String title;
    private String description;
    private int trainingModuleId;

    public ModuleData(int id, String title, String description, int trainingModuleId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.trainingModuleId = trainingModuleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(int trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleData that = (ModuleData) o;
        return id == that.id && trainingModuleId == that.trainingModuleId && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, trainingModuleId);
    }
}
