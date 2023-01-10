package main;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Futurenew<V> extends FutureTask<V> {

    private Task t;

    public Futurenew(Callable c)
    {
        super(c);
    }
}
