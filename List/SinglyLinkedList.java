public class SinglyLinkedList<E> implements List<E> {
    
    Node<E> head;
    Node<E> tail;

    private int size;

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<E>(e);
        if(head == null){
            head = newNode;
            tail = head;
        }else{
            tail.next = newNode;
            tail = newNode; 
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(size < index){
            throw new IllegalArgumentException();
        }else if(size == index ){
            add(element);
        }else if(index == 0){
            Node<E> newNode = new Node<E>(element);
            newNode.next = head;
            head = newNode;
            size++;
        }else{
            Node<E> findedNode = findNode(index - 1);
            Node<E> newNode = new Node<E>(element);
            newNode.next = findedNode.next;
            findedNode.next = newNode;
            size++;
        }

    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++){
            if(findNode(i).element.equals(o)){
                return true;
            }
        }

        return false;
    }

    @Override
    public E get(int index) {
        Node<E> node = findNode(index);

        return node.element;
    }

    
    private Node<E> findNode(int index){
        Node<E> findedNode = head;
        for(int i = 0; i < index; i++){
            findedNode = findedNode.next;
        }

        return findedNode;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 3개 이하일때 안함
    @Override
    public E remove(int index) {
        E e = null;
        if( size <= index){
            throw new IllegalArgumentException();
        }else if(index == 0){
            e = head.element;
            head = head.next;
            size--;
        }else if(index == size - 1){
            Node<E> findedNode = findNode(index - 1);
            e = findedNode.next.element;
            tail = findedNode;
            findedNode.next = null;
        }else{
            Node<E> findedNode = findNode(index - 1);
            e = findedNode.next.element;
            findedNode.next = findedNode.next.next;
            size--;
        }

        return e;

        
    }

    @Override
    public E set(int index, E element) {
        E e = findNode(index).element;
        findNode(index).element = element;

        return e;
    }

    @Override
    public int size() {
        return this.size;
    }
}

class Node<E>{
    E element;
    Node<E> next;

    Node(E e){
        element = e;
    }
}
