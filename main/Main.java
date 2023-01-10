package main;

public class Main {

    public static void main(String[] args) {
        Task task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        System.out.println(task);
        try {
            System.out.println(task.call());
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Runtime.getRuntime().availableProcessors());

        CustomExecutor p = new CustomExecutor();
    }



}
