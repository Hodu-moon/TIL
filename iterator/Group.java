import java.util.Iterator;

public class Group implements Iterable<Person>{
    int index = 0;
    Person[] persons;
    
    public Group(int size){
        this.persons = new Person[size];
    }

    public void add(Person person){
        this.persons[this.index++] = person;
    }

    public Person get(int index){
        return this.persons[index];
    }

    public int size(){
        return index + 1;
    }

    public Iterator<Person> iterator(){

        return new GroupEnumerator(this.persons);

    }



}
