package sample;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Objects;

public class ExtractorsImpl {
    public static void main(String[] args) throws IOException {
        String repoUrlPython = "https://github.com/Jlebours/WikipediaExtractor_Python";
        String repoUrlJava = "https://github.com/Jlebours/PDL_1920_groupe-7";

        // TODO check if target directory for cloning exists for the two extractors

        // clone python extractor
        cloneExtractor(repoUrlPython);
        // clone java extractor
        cloneExtractor(repoUrlJava);

        // TODO check if output dir exists at the root of the project
        // TODO check if input dir exists at the root of the project

        // python extractor execution
        runPythonExtractor();
        // java extractor execution
        //runJavaExtractor();
    }

    public static void cloneExtractor(String repoUrl){
        String cloneDirectoryPath;
        if (repoUrl.equals("https://github.com/Jlebours/PDL_1920_groupe-7")){
            cloneDirectoryPath = "src/main/java/wikipediaExtractor_Java";
        } else {
            cloneDirectoryPath = "src/main/java/wikipediaExtractor_Python";
        }
        File cloneJava = new File(cloneDirectoryPath);
        // check if repo for clone java extractor exists
        boolean cloneJavaPresent = false;
        if (!cloneJava.exists()){
            cloneJavaPresent = cloneJava.mkdir();
        } else {
            cloneJavaPresent = true;
        }
        if (cloneJavaPresent && Objects.requireNonNull(cloneJava.list()).length == 0){
            try {
                System.out.println("Cloning " + repoUrl + " into " + cloneDirectoryPath);
                Git.cloneRepository()
                        .setURI(repoUrl)
                        .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                        .call();
                System.out.println("Completed Cloning");
            } catch (GitAPIException e) {
                System.out.println("Exception occurred while cloning repo");
                e.printStackTrace();
            }
        }
    }

    public static void runPythonExtractor() throws IOException {
        // Python extractor exec
        Process p = Runtime.getRuntime().exec("python src/main/java/wikipediaExtractor_Python/main.py");
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
        Process p = Runtime.getRuntime().exec("javac sample/JavaScript.java");
        Process p2 = Runtime.getRuntime().exec("java sample/JavaScript");

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

        BufferedReader stdInput2 = new BufferedReader(new
                InputStreamReader(p2.getInputStream()));
        // error
        BufferedReader stdError2 = new BufferedReader(new
                InputStreamReader(p2.getErrorStream()));
        String qui;
        while ((qui = stdInput2.readLine()) != null) {
            System.out.println(qui);
        }
        String err2;
        while ((err2 = stdError2.readLine()) != null) {
            System.out.println(err2);
        }
    }
}
