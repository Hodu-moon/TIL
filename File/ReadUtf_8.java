import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadUtf_8 {
    
    public static void main(String[] args) {

        File file = new File("Supersonic.txt");
        InputStreamReader reader = null;


        try(FileInputStream stream = new FileInputStream(file)){
            reader = new InputStreamReader(stream, "utf-8");

            
            char[] contents = new char[(int)file.length()];

            System.out.println(reader.getEncoding());

            for(int i = 0; i < contents.length; i++){
                contents[i] = (char)reader.read();
            }


        }catch(Exception e){}

        

    }
}
