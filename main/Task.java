package main;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.*;

public class Task<V>  implements Callable<V>/*, Comparable<Task>*/{
    private TaskType taskType;
    private Callable<V> c;

    protected Task(Callable<V> c, TaskType taskType){
        this.taskType = taskType;
        this.c = c;
    }

    public static Task<?> createTask(Callable<?> c, TaskType taskType){
        Task<?> t = new Task<>(c, taskType);
        return t;
    }

    public static Task<?> createTask(Callable<?> c){
        //???
        TaskType taskType = TaskType.OTHER;
        Task<?> t = new Task<>(c, taskType);
        return t;
    }

    @Override
    public V call(){
        try {
            return this.c.call();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   /* @Override
    public int compareTo(Task t) {
        if (this.taskType.getPriorityValue() > t.taskType.getPriorityValue())
        {
            return -1;
        }
        if (this.taskType.getPriorityValue() < t.taskType.getPriorityValue())
        {
            return 1;
        }
        return 0;
    }*/

    @Override
    public String toString()
    {
        String s = "Type: " + this.taskType.getType() + ", Priority: " + this.taskType.getPriorityValue();
        return s;
    }

    public int getPriority()
    {
        return this.taskType.getPriorityValue();
    }

    public Callable<V> getCallable()
    {
        return this.c;
    }


}
