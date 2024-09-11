import java.util.*;

public class HashCodeMain {
    public static void main(String[] args) {

        User user1 = new User("y", 25);
        User user2 = new User("y", 25);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        Set<User> set = new HashSet<>();
        System.out.println(set.add(user1));
        System.out.println(set.add(user2));

    }
}

class User{
    String name;
    int age;

    User(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        User u = (User) obj;

        if(this.name.equals(u.name) ){
            return true;
        }
        if(this.age == u.age){
            return true;
        }

        return false;

    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result =  31 * result + age;

        return result;
    }
}
