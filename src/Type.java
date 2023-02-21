public enum Type {
    WORK("Рабочая"),
    PERSONAL("Личная");

    public final String taskType;

    Type(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return taskType + ", ";
    }
}