import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {
    Integer maxID = -1;
    // поля
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final List<Task> removedTasks = new ArrayList<>();

    // Методы
    public Integer addTask(String title, String descr, int repeatType, Type taskType) throws IncorrectArgumentException {

        Task t = null;
        try {

            switch (repeatType) {
                case 1:
                    t = new Task(maxID + 1, title, descr, LocalDateTime.now(), taskType);
                    break;
                case 2:
                    t = new DailyTask(maxID + 1, title, descr, LocalDateTime.now(), taskType);
                    break;
                case 3:
                    t = new WeeklyTask(maxID + 1, title, descr, LocalDateTime.now(), taskType);
                    break;
                case 4:
                    t = new MonthlyTask(maxID + 1, title, descr, LocalDateTime.now(), taskType);
                    break;
                case 5:
                    t = new YearlyTask(maxID + 1, title, descr, LocalDateTime.now(), taskType);
                    break;
                default:
                    throw new IncorrectArgumentException();
            }
            tasks.put(t.getId(), t);
            maxID++;
            return maxID;
        } catch (IncorrectArgumentException e) {
            System.out.println("Ошибка создания задачи.");
            throw e;
        }
    }

    public boolean removeTask(Integer id) throws TaskServiceException {
        if (!tasks.containsKey(id))
            throw new TaskServiceException("Задача с таким ключом не найдена.");
        tasks.remove(id);
        return true;
    }

    public List<Task> tasksByDate(LocalDate date) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.appearsIn(date)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public Collection<Task> tasksAll() {
        return tasks.values();
    }
}