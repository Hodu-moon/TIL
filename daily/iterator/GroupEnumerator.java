import java.util.Iterator;

public class GroupEnumerator implements Iterator<Person>{
    
    Person[] persons;
    int index = 0;
    int size;

    GroupEnumerator(Person[] persons){
        this.persons = persons;
        size = persons.length;
    }

    public boolean hasNext(){
        return index != size; 
    }

    public Person next(){
        return persons[index++];
    }
}
