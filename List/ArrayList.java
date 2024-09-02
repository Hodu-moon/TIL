@SuppressWarnings("all")
public class ArrayList<E> implements List<E> {
    E[] elements;
    int size;

    ArrayList(){
        elements = (E[])new Object[10];
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if(size < elements.length){
            elements[size++] = e;
            return true;
        }

        return false;
    }

    @Override
    public void add(int index, E element){
        if( size < index){
            throw new IndexOutOfBoundsException();
        }else if( size == index){
            add(element);
        }else{
            for(int i = size ; i > index; i--){
                elements[i] = elements[i - 1]; 
            }
            elements[index] = element; 
            size++;
        }
    }


    public void clear(){
        this.size = 0;
    }
        
    public boolean contains(Object o){
        E e = (E) o;

        for(int i = 0; i < size; i++){
            if(get(i).equals(e))
                return true;
        }

        return false;
        
    }

    public int size(){
        return size;
    }

    public E get(int index){
        return elements[index];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E set(int index, E element){
        E e = elements[index];
        elements[index] = element;
        return e;
    }

    public E remove(int index){
        E e = elements[index];
        for(int i = index; i < size - 1; i++){
            elements[i] = elements[i + 1];

        }
        size--;

        return e;
    }


    
    
}
