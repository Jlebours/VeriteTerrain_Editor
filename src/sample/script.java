package sample;

import java.io.IOException;

public class script {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("python ../WikipediaExtractor_Python/main.py");
    }
}
