import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(Integer id, String title, String description, LocalDateTime taskDate, Type taskType) throws IncorrectArgumentException {
        super(id, title, description, taskDate,taskType);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return date.getDayOfMonth() == getDateTime().getDayOfMonth();
    }
}