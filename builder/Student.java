public class Student {
    private String name;
    private String number;
    private String friend;

    private Student(Builder builder){
        this.name = builder.name;
        this.number = builder.number;
        this.friend = builder.friend;
    }

    public static class Builder{
        private String name;
        private String number;
        private String friend;


        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder number(String number){
            this.number = number;
            return this;
        }

        public Builder friend(String friend){
            this.friend = friend;
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }

}