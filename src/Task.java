import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    private final Integer id;
    private String title;
    private String description;
    private final LocalDateTime taskDate;
    private Type taskType;

    public Task(Integer id, String title, String description, LocalDateTime taskDate,Type taskType) throws IncorrectArgumentException {
        if ((id < 0) || (title.isEmpty()))
            throw new IncorrectArgumentException();
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
        this.taskType = taskType;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //    public TaskType getType() {return taskType;}
    public void setTitle(String s) {
        title = s;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String s) {
        description = s;
    }

    public LocalDateTime getDateTime() {
        return taskDate;
    }

    public boolean appearsIn(LocalDate date) {
        return taskDate.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "Тип задачи: " +taskType +" Задача, " +
                "id=" + id +
                ", Название: " + title + '\'' +
                ", Описание задачи: " + description + '\'' +
                ", Дата создания: " + taskDate.toString() +
                '}';
    }

    public LocalDateTime getDate() {
        return taskDate;
    }
}
