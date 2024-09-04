import java.util.*;
import java.io.*;

public class Dictionary{
    Map<String, List<String>> dictionary ;
    
    public Dictionary (){
        dictionary  = new HashMap<>();
        this.getDictionary();
    }

    private void getDictionary(){
        File file = new File("words.txt");
        List<String> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), "utf-8")
        )){
            String line = "";
            while((line = reader.readLine()) != null){

                // 1.  |(여기 자르기 ) english dfafda
                String[] split1 = line.split("[.]");
                
                // english |(여기 자르기) 한글 뜻
                String[] split2 = split1[1].split("(?<=[a-z] )");
                String english = split2[0].trim();

                // 한글 뜻, 한글 뜻 
                String[] split3 = split2[1].split("[,]");

                list  = new ArrayList<>();

                // 한글 리스트 넣기 
                for(int i = 0; i < split3.length; i++){   
                    list.add(split3[i].trim());
                }

                dictionary.put(english, list);

            }


        }catch(Exception e){}

    }

    public List<String> get(String string){
        return dictionary.get(string);
    }



    



}   