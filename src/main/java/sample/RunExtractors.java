package sample;

import java.io.*;

public class RunExtractors {
    public static void main(String[] args) throws IOException {
        verifyOutputExists();
        verifyInputExists();
        runPythonExtractor();
        runJavaExtractor();
    }

    public static void runPythonExtractor() throws IOException {
        File python = new File("output/python");
        if (python.exists()) {
            for (File file : python.listFiles()) {
                file.delete();
            }
        }
        Process p = Runtime.getRuntime().exec("python ../extractors/wikipediaExtractor_Python/main.py");
        // output
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
        // error
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));
        String o;
        while ((o = stdInput.readLine()) != null) {
            System.out.println(o);
        }
        String err;
        while ((err = stdError.readLine()) != null) {
            System.out.println(err);
        }
    }

    public static void runJavaExtractor() throws IOException {
        File html = new File("output/html");
        if (html.exists()) {
            for (File file : html.listFiles()) {
                file.delete();
            }
        }
        File wikitext = new File("output/wikitext");
        if (wikitext.exists()) {
            for (File file : wikitext.listFiles()) {
                file.delete();
            }
        }
        Process p = Runtime.getRuntime().exec("java -jar ../extractors/wikipediaExtractor_Java/target/WikipediaMatrix-1.0-SNAPSHOT.jar");
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
        // error
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));
        String o;
        while ((o = stdInput.readLine()) != null) {
            System.out.println(o);
        }
        String err;
        while ((err = stdError.readLine()) != null) {
            System.out.println(err);
        }
    }

    public static void verifyOutputExists() {
        File output = new File("output");
        if (!output.exists()) {
            output.mkdir();
            System.out.println("Output initialized");
        } else {
            System.out.println("Output is already present");
        }
    }

    public static void verifyInputExists() throws IOException {
        File input = new File("inputdata");
        File wikiurls = new File("inputdata/wikiurls.txt");
        if (!input.exists()) {
            input.mkdir();
            wikiurls.createNewFile();
            System.out.println("Empty inputdata initialized");
        } else {
            if (!wikiurls.exists()) {
                wikiurls.createNewFile();
                System.out.println("Empty inputdata initialized");
            } else {
                System.out.println("Inputdata is already present");
            }
        }
    }

    public static void modifyWikiurls(String url) throws IOException {
        try{
            File wikiurls = new File("inputdata/wikiurls.txt");
            wikiurls.createNewFile();
            FileWriter fw = new FileWriter(wikiurls.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(url);
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
