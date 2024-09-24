import java.util.concurrent.ArrayBlockingQueue;
import java.util.Queue;

public class RequestChannel {
    Queue<Excutable> queue = new ArrayBlockingQueue<>(10);

    public void addJob(Excutable excutable){
        queue.add(excutable);
    }

    public Excutable getJob(){
        return queue.poll();
    }



}
