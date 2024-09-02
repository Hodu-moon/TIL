import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    
    public static List<List<String>> readCSV(String fileName){
        List<List<String>> csvList = new ArrayList<List<String>>();

        File csv = new File(fileName);
        BufferedReader reader = null;

        String line = "";

        try{
            reader = new BufferedReader(new FileReader(csv));

            while((line =reader.readLine()) != null ){
                List<String> aline = new ArrayList<String>();
                String[] lineArr = line.split(",");
                aline = Arrays.asList(lineArr);
                csvList.add(aline);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
            reader.close();
            }catch(Exception e){}
        }

        return csvList;


    }

    public static void writeCSV(List<String[]> dataList){
        File csv = new File("info.csv");

        BufferedWriter bw = null;

        try{
            bw = new BufferedWriter(new FileWriter(csv));

            for(int i = 0; i < dataList.size(); i++){
                String[] data = dataList.get(i);
                String aData = "";
                aData = data[0] + "," + data[1] + "," + data[2];

                bw.write(aData);

                bw.newLine();


            }
        }catch(Exception e){}
    }
}
