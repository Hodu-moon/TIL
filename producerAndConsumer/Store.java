import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {

    private static int MAX_ITEM = 10;
    private static int MAX_CONSUMER = 5;
    private List<Item> itemList = new LinkedList<>();

    private ArrayBlockingQueue<Consumer> consumerQueue = new ArrayBlockingQueue<>(10);
    private Thread consumerThread;

    int consumerIndex = 0;

    public Store() {

    }

    public void enter() {


        System.out.println("enter customer ");
        try{
            consumerQueue.put(new Consumer("consumer", this));
        }catch (InterruptedException ignored){}
      }

    public void exit() {
        consumerQueue.poll();
    }

    public void startBuy(){
        consumerThread = new Thread(consumerQueue.poll());
        consumerThread.start();

        if(consumerThread.isInterrupted()){
            exit();
        }


    }



    // Store입장에서 producer로부터 물건 사기
    public synchronized void buy(Item item) {
        while(itemList.size() >= MAX_ITEM){
            try{
                System.out.println("item full wait ");
                wait();
            }catch (InterruptedException ignored){}
        }

        itemList.add(item);
        System.out.println("producer : store buy item : " + itemList.size() );

        notifyAll();
    }

    // Consumer한테 물건 팔기
    public synchronized void sell() {
        while(itemList.isEmpty()){
            try{
                wait();
            }catch (InterruptedException ignored){}
        }

        itemList.removeFirst();
    }
}