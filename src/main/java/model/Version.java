package main.java.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Version {

    public List<String> get() {
        List <String> returnList = new ArrayList<>();
        String filename = "src/resources/about.csv";
        BufferedReader br = null;
        String line = "";
        String token = ",";
        
        try {
            
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                
                String[] data = line.split(token);
                
                //System.out.println(data[0]);
                
                returnList.add(data[0]);
            }
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return returnList;
    }

}
