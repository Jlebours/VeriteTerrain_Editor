package sample;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class CloneExtractors {
    /**
     * Clone the two extractors
     * Run the script which generates the executable jar file of the java extractor
     * @param args : String[]
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String repoUrlPython = "https://github.com/Jlebours/WikipediaExtractor_Python";
        String repoUrlJava = "https://github.com/Jlebours/PDL_1920_groupe-7";
        cloneExtractor(repoUrlPython);
        cloneExtractor(repoUrlJava);
        Runtime.getRuntime().exec("cmd /c start \"\" generateJAR.bat");
    }

    /**
     * Check the parameter is the java extractor ou the python extractor github url
     * Check if the directory to clone them is well initialized
     * Clone it in the directory
     * @param repoUrl : String
     */
    public static void cloneExtractor(String repoUrl) {
        String cloneDirectoryPath;
        if (repoUrl.equals("https://github.com/Jlebours/PDL_1920_groupe-7")) {
            cloneDirectoryPath = "../extractors/wikipediaExtractor_Java";
        } else {
            cloneDirectoryPath = "../extractors/wikipediaExtractor_Python";
        }
        File extractorsDir = new File("../extractors");
        if(!extractorsDir.exists()){
            extractorsDir.mkdir();
        }
        File cloneJava = new File(cloneDirectoryPath);
        boolean cloneJavaPresent = false;
        if (!cloneJava.exists()) {
            cloneJavaPresent = cloneJava.mkdir();
        } else {
            cloneJavaPresent = true;
        }
        if (cloneJavaPresent && Objects.requireNonNull(cloneJava.list()).length == 0) {
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
}
