import java.util.Arrays;

public class ThreadPoolMain {
    Thread[] threads;
    Runnable runnable;

    ThreadPoolMain(int poolSize, Runnable runnable){
        threads = new Thread[poolSize];
        Arrays.fill(threads, new Thread(runnable));
    }

    ThreadPoolMain(Runnable runnable){
        this(5, runnable);
    }


    public void stopThread(){
        synchronized (threads){
            for(Thread thread : threads){
                thread.interrupt();
            }
        }
    }

    public void initThread(){
        synchronized (threads) {
            for (Thread thread : threads) {
                thread.start();
            }
        }
    }




}
