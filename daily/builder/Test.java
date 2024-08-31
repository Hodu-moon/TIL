public class Test {
    public static void main(String[] args) {
        Student student = new Student.Builder().name("hello")
                        .number("010-222")
                        .friend("hey")
                        .build();
    }
}
