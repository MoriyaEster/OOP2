package main;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class CustomExecutor extends ThreadPoolExecutor{

    //tor
    private ThreadPoolExecutor new_pool;
    private PriorityBlockingQueue P_queue;

    private int Max_pri = -1;

    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors()/2 , Runtime.getRuntime().availableProcessors() - 1,
                300 , TimeUnit.MILLISECONDS , new PriorityBlockingQueue<Runnable>(Runtime.getRuntime().availableProcessors()/2
                ,Comparator.comparing(t2 ->((TaskAdapt)t2))));
      //  int min = Runtime.getRuntime().availableProcessors() / 2;
      //  int max = Runtime.getRuntime().availableProcessors() - 1;
      //  P_queue = new PriorityBlockingQueue<>(min, Comparator.comparing(t -> ((TaskAdapt) t)));
      //  new_pool = new ThreadPoolExecutor(min, max, 300, TimeUnit.MILLISECONDS, P_queue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable)
    {
        return new TaskAdapt<T>(callable);
    }

    public <T> Future<T> submit(Task<T> t) {

        System.out.println("This is task type value: " + t.getPriority());
        try {
             return super.submit((Callable<T>) t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public <T> Future<?> submit(Callable<?> c, TaskType taskType)
    {
        Task<?> t = Task.createTask(c , taskType);
        return submit(t);
    }

    //@Override
   /* public <T> Future<T> submit(Callable<T> c)
    {
        Task<?> t = Task.createTask(c , TaskType.OTHER);
        return this.submit(t);
    }*/

    public int getCurrentMax()
    {
        return this.Max_pri;
    }

    public void gracefullyTerminate()
    {
        super.shutdown();
    }

    //@Override
//    protected void afterExecute(Runnable r, Throwable t) {
//        if (!new_pool.getQueue().isEmpty())
//        {
//            if(r instanceof Task<?>)
//            {
//                if(((Task<?>) r).getPriority() < this.Max_pri)
//                {
//                    this.Max_pri = ((Task<?>) r).getPriority();
//                }
//            }
//        }
//    }

}
