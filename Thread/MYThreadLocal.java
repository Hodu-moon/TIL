public class MYThreadLocal {

    public static void main(String[] args) {

        Thread threadA = new Thread(){
            @Override
            public void run(){
                int a = Items.getInt();
                System.out.println("ThreadA: " +a);
                Items.setInt(200);
                a = Items.getInt();
                System.out.println("ThreadA: " +a);
            }
        };


        Thread threadB = new Thread(){
            @Override
            public void run(){
                int a = Items.getInt();
                System.out.println( "ThreadB: " + a);
                Items.setInt(100);
                a = Items.getInt();
                System.out.println("ThreadB: " +a);
            }
        };

        threadA.start();
        threadB.start();
    }
}

class Items {
    static final ThreadLocal<Integer> localInt = ThreadLocal.withInitial(() -> 1);

    static int  getInt(){
        return localInt.get();
    }

    static void setInt(int a){
        localInt.set(a);
    }
}

