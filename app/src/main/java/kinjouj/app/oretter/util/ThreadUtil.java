package kinjouj.app.oretter.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadUtil {

    private static final ExecutorService executor = Executors.newCachedThreadPool(
        new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "oretter-thread");
            }
        }
    );

    public static void run(Runnable r) {
        executor.submit(r);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
