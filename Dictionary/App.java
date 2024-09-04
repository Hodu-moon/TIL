import java.util.*;

public class App {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        System.out.println("종료 하려면 'eixt' 을 입력하세요 ");
        Scanner scanner = new Scanner(System.in);

        String user;
        while(true){
            try{

                System.out.print("검색할 단어를 입력하세요(영어): ");
                user = scanner.nextLine();


                if(user.equals("exit"))
                    break;
                
                List<String> list =dictionary.get(user);

                if(list.equals(null)){
                    throw new IllegalArgumentException();
                }else{

                    System.out.print("검색 결과 -> [");
                    for(int i = 0; i < list.size(); i++){
                        System.out.print(list.get(i) );
                        if(i < list.size() - 1){
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                }

            }catch(Exception e){ 
                System.out.println("없는 단어입니다.");
            }
        }


    }
}
