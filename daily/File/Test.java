import java.io.*;
import java.util.List;

public class Test {
     public static void main(String[] args) throws Exception{


        // utf-8 파일 읽기 
        File file = new File("Supersonic.txt");
        InputStreamReader reader = null;


        try(FileInputStream stream = new FileInputStream(file)){
            reader = new InputStreamReader(stream, "utf-8");

            
            char[] contents = new char[(int)file.length()];

            System.out.println(reader.getEncoding());

            for(int i = 0; i < contents.length; i++){
                contents[i] = (char)reader.read();

            }

            for(char c : contents){
                System.out.print(c);
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        // csv 파일 읽기

        List<List<String>> csvList =  CSVReader.readCSV("info.csv");

        for(List<String> list : csvList){
            for(String string : list){
                System.out.print(string + ",");
            }
            System.out.println();
        }
        


    }
}
