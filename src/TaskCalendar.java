import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskCalendar {

    public TaskCalendar() {
        taskService = new TaskService();
        inputScan = new Scanner(System.in);
    }

    TaskService taskService;
    Scanner inputScan;

    public static void main(String[] args) {
        TaskCalendar tc=new TaskCalendar();
        tc.run();
    }

    public void run(){
        // write your code here
        String inputCommand = "";
        while (!inputCommand.equals("QUIT")){
            System.out.println("Введите команду (HELP - список команд):");
            inputCommand = inputScan.next().toUpperCase();
            if (inputCommand.equals("HELP")){
                showHelp();
            }
            if (inputCommand.equals("CREATE")){
                createTask();
            }
            if (inputCommand.equals("DELETE")){
                deleteTask();
            }
            if (inputCommand.equals("SHOW")){
                showTaskList();
            }
            if (inputCommand.equals("SHOWALL")){
                showFullTaskList();
            }
            System.out.println("==============================================");
        }
    }

    private static void showHelp(){
        System.out.println("CREATE - создать задачу,");
        System.out.println("DELETE - Удалить задачу,");
        System.out.println("SHOW - Вывести список задач по дате,");
        System.out.println("SHOWALL - Вывести список всех задач,");
        System.out.println("QUIT - закрыть календарь");
    }

    private  void createTask() {
        try {
            System.out.println("Введите тип задачи.");
            Type taskType = Type.valueOf(inputScan.next().toUpperCase());
            System.out.println("Введите название задачи.");
            String title = inputScan.next();
            System.out.println("Введите описание задачи.");
            String descr = inputScan.next();
            System.out.println("Введите тип повтора задачи (1 2 3 4 5).");
            int repType = inputScan.nextInt();
            int id = taskService.addTask(title, descr, repType,taskType);
            System.out.println("Задача создана с номером " + id + ".");

        }
        catch (IncorrectArgumentException e){
            System.out.println("Ошибка ввода.");
        }
    }

    private  void deleteTask() {
        Integer id=-1;
        System.out.println("Введите id задачи:");
        try {
            id = inputScan.nextInt();
        }
        catch (Exception e){
            System.out.println("Ошибка ввода.");
            return;
        }
        try {
            taskService.removeTask(id);
            System.out.println("Задача удалена.");
        }
        catch (TaskServiceException e1){
            System.out.println(e1.getMessage());
        }
    }

    private void showTaskList(){
        LocalDate dt=null;
        System.out.println("Введите дату задач (формат YYYY-MM-DD):");
        try {
            dt = LocalDate.parse(inputScan.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        catch (Exception e){
            System.out.println("Ошибка ввода.");
            return;
        }

        List<Task> found = taskService.tasksByDate(dt);
        for (Task task:found){
            System.out.println(task.toString());
            System.out.println("--------------------");
        }
        if (found.size()==0){
            System.out.println("Задач на эту дату нет.");
        }
    }
    private void showFullTaskList(){
        Collection<Task> found = taskService.tasksAll();
        for (Task task:found){
            System.out.println(task.toString());
            System.out.println("--------------------");
        }
        if (found.size()==0){
            System.out.println("Задач нет.");
        }
    }
}