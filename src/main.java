import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class main {
    public static final String DELIMITER = "--------------------------------------------------------------------------------";

    public static void printToFile(ArrayList<String> testNames, String targetDirectory) {
        try {
            FileWriter myWriter = new FileWriter(targetDirectory);
            for (String name : testNames) {
                myWriter.write(name + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void dateParse(String [] input, String [] args) {
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("date")) {
                String date = input[i + 3];
                dates.add(date);
            }
        }
        printToFile(dates, args[1] + "\\" + "date.txt");
    }

    /**
     * The function provides the framework for analysis of several versions in the basepath
     * @param basePath - path to project folder with version subfolders
     * @param firstVersion - first version you want to analyse
     * @param lastVersion - last version you want to analyse
     */
    public static void multiFileSearch(String basePath, int firstVersion, int lastVersion) {
        for (int i = 1; i <= lastVersion; i++) {
            String targetPath = basePath
        }
    }

    public static void causeParse(String [] input, String [] args) {
        ArrayList<String> testNames = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("cause")) {

                boolean endOfTests = false;
                for (int j = i; j < input.length && !endOfTests; j++) {
                    if (input[j].equals(DELIMITER)) {
                        endOfTests = true;
                        continue;
                    }

                    if (input[j].equals("-") && !input[j + 1].equals("-")) {
                        String filename = input[j + 1];
                        System.out.println(input[j + 1]);
                        int firstDoubleColon = filename.indexOf(':');

                        boolean testFound = false;

                        for (int y = firstDoubleColon; y >= 0 && !testFound; y--) {
                            if (filename.charAt(y) == '.') {
                                testFound = true;
                                int colonIndex = y;
                                testNames.add(filename.substring(colonIndex + 1, filename.length()));
                            }
                        }
                    }
                }
            }
        }
        if (testNames.size() > 0) {
            // outputPath has to be given as a file path, like (windows) "D:\\Dropbox\\shared2\\infoParser\\testFiles"
            printToFile(testNames, args[1] + "\\" + "testNames2.txt");
        }
    }

    public static void main(String[] args){
        String info = "";
        try {
            // text has to be given as a file path, like (windows) "D:\\Dropbox\\shared2\\infoParser\\testFiles\\testInfo.txt"
            info = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException e) {
            e.printStackTrace();
        }


        String [] input = info.split("\\s+");
        dateParse(input, args);




    }
}
