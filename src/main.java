import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class main {
    public static final String DELIMITER = "--------------------------------------------------------------------------------";


    /**
     *
     * @param printData -  String Array, containing data that should be stored in txt
     * @param targetDirectory - Path to target directory
     */
    public static void printToFile(ArrayList<String> printData, String targetDirectory) {
        try {
            FileWriter myWriter = new FileWriter(targetDirectory);
            for (String data : printData) {
                myWriter.write(data + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param input - String that contains entire info file
     * @param outputPath - path to save output
     */
    public static void dateParse(String [] input, String outputPath) {
        ArrayList<String> dates = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("date")) {
                String date = input[i + 3];
                System.out.println(date);
                dates.add(date);
            }
        }
        printToFile(dates, outputPath + "\\" + "date.txt");
    }



    /**
     *
     * @param input - String that contains entire info file
     * @param outputPath - path to save output
     */
    public static void causeParse(String [] input, String outputPath) {
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
            printToFile(testNames, outputPath + "\\" + "testNames2.txt");
        }
    }

    /**
     * @param infoFilePath - Contains path to infoFile
     * @return - returns the info file as a String array
     */
    public static String [] infoToString(String infoFilePath) {
        String info = "";
        try {
            // text has to be given as a file path, like (windows) "D:\\Dropbox\\shared2\\infoParser\\testFiles\\testInfo.txt"
            info = new String(Files.readAllBytes(Paths.get(infoFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] input = info.split("\\s+");
        return input;
    }

    /**
     * The function provides the framework for analysis of several versions in the basepath
     * @param basePath - path to project folder with version subfolders
     * @param firstVersion - first version you want to analyse
     * @param lastVersion - last version you want to analyse
     */
    public static void multiFileSearch(String basePath, int firstVersion, int lastVersion) {
        for (int i = firstVersion; i <= lastVersion; i++) {
            String targetPath = basePath + "\\" + "version_" + i;
            String infoPath = targetPath + "\\testInfo.txt";
            String [] input = infoToString(infoPath);
            dateParse(input, targetPath);
        }
    }

    /**
     * args has to contain the arguments below, only 3 arguments are necessary if you
     * want to parse an info file. They are pathToInfo, outputPath, multifile
     * while you need 5 arguments if you want to search through multiple files
     * When you use 5 arguments, outputPath will be ignored
     * If single file shall be analysed args[0] has to be path to info like ..\Chart\version_2\testInfo.txt
     * If multiple files will be analysed args [0]has to be the path to the base folder like ..\Chart
     * @param args - contains: String pathToInfo, String outputpath, String multifile = TRUE/FALSE, int first, int last
     */
    public static void main(String[] args){

        if (args[2].equals("TRUE")) {
            int firstVersion = Integer.parseInt(args[3]);
            int lastVersion = Integer.parseInt(args[4]);
            multiFileSearch(args[0], firstVersion,lastVersion);
        } else {
            String [] input = infoToString(args[0]);
            causeParse(input, args[1]);
        }
//
//




    }
}
