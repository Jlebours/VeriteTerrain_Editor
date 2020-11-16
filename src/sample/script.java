package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class script {
    public static void main(String[] args) {
        try {
            String s;
            Process p = Runtime.getRuntime().exec("python src/sample/test.py");
            BufferedReader in = new BufferedReader(new InputStreamReader((p.getInputStream())));
            while ((s = in.readLine()) != null){
                System.out.println(s);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
