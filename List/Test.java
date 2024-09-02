import java.util.LinkedList;

public class Test {
    
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.add("first");
        list.add("Second");
        list.add("Third");
        list.add(1, "NEW");

        list.set(2, "set");


        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }        

        System.out.println(list.contains("set"));

        
    }
}
