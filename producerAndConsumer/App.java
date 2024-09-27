import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;


public class App {
    public static void main(String[] args) throws InterruptedException {

        Store store = new Store();

        Thread producerThread = new Thread(new Producer(store));
        producerThread.start();

        Thread addCustomer = new Thread(new AddConsumerThread(store));
        addCustomer.start();

        Thread startButy = new Thread(new StoreStartThread(store));
        startButy.start();


        Thread.sleep(20000);
        System.exit(2);


    }


}


