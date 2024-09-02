public class Person {
    private int no;
    private String name;

    public Person(int no, String name){
        this.no = no;
        this.name = name;
    }

    public int getNo(){
        return this.no;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.no + " " + this.name;
    }
}
