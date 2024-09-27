import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    String name;
    AtomicInteger atomicInteger = new AtomicInteger(0);

    Item(){
        this.name = "item" + atomicInteger.getAndIncrement();
    }
}
