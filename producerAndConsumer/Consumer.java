import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    Store store;
    String name;

    public Consumer(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        store.sell();
    }
}