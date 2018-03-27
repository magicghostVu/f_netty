package client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Fresher on 23/03/2018.
 */
public class MyThreadPoolService {

    private ExecutorService executorService;

    private ScheduledExecutorService scheduledExecutorService;

    private static MyThreadPoolService ourInstance = new MyThreadPoolService();

    public static MyThreadPoolService getInstance() {
        return ourInstance;
    }

    private MyThreadPoolService() {
        executorService = Executors.newSingleThreadExecutor();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }


}
