import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(Integer id, String title, String description, LocalDateTime taskDate, Type taskType) throws IncorrectArgumentException {
        super(id, title, description, taskDate,taskType);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "; тип повтора: ежедневная";
    }
}