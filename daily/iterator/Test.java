public class Test {
    
    public static void main(String[] args) {
        Person person1 = new Person(0, "y");
        Person person2 = new Person(1, "j");
        Person person3 = new Person(2, "j");

        Group group = new Group(3);

        group.add(person1);
        group.add(person2);
        group.add(person3);

        GroupEnumerator enumerator = new GroupEnumerator(group.persons);

        while(enumerator.hasNext()){
            System.out.println(enumerator.next());
        }

        for(Person person : group){
            System.out.println(person);
        }

        MVG mvg = new MVG(person1, person2, person3);

        Enumerator enumerator2 = mvg.enumerator();


        while(enumerator2.hasNext()){
            System.out.println(enumerator2.next());
        }
    }
}
