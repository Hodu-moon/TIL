import java.util.concurrent.ThreadLocalRandom;

public class AddConsumerThread implements Runnable{

    Store store;
    AddConsumerThread(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        while(true) {

            int i = ThreadLocalRandom.current().nextInt(10);
            try {
                Thread.sleep(i * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            store.enter();
        }
    }
}
