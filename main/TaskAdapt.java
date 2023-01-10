package main;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TaskAdapt<T> extends FutureTask implements Comparable<TaskAdapt<T>>{

    private Callable<T> task;
    public TaskAdapt(Callable<T> c)
    {
        super(c);
        this.task = c;
    }

    public Callable<T> getCallable()
    {
        return this.task;
    }

    public int getPriority()
    {
        return (((Task<T>)task)).getPriority();
    }

    @Override
    public int compareTo(TaskAdapt<T> o) {
        //return ((Task)this.task).compareTo((Task)o.task);
        if (((Task<T>)(this.task)).getPriority() >
                (((Task.createTask(o.getCallable())).getPriority())))
        {
            return 1;
        }
        if (((Task<T>)(this.task)).getPriority() <
                (((Task.createTask(o.getCallable())).getPriority())))
        {
            return -1;
        }

        return 0;
    }

}


