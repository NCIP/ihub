package gov.nih.nci.caxchange.messaging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.tools.ant.Task;

/**
 * This is a Utility class which is being used as a custom task helper for Ant task. It will download the Cacis template
 * and merge with the iHub template.
 * 
 * @author Rohit Gupta
 * 
 */
public class MergeFilesTask extends Task {

    private String fileNames = "";

    /**
     * 
     * @param fileNames - delimiter separated file names
     */
    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public void execute() {

        final String[] fileNamesArray = convertDelimStringToArray(fileNames, ";");

        final String fileName1 = fileNamesArray[0];
        final String fileName2 = fileNamesArray[1];
        final String mergedFileName = fileNamesArray[2];

        // read the contents of the files
        final String mergedFileContents = readFileContents(fileName1) + readFileContents(fileName2);

        // write the merged file
        copyMergedFile(mergedFileName, mergedFileContents);
    }

    private String readFileContents(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        try {
            // Open the file that is the first
            final FileInputStream fstream = new FileInputStream(fileName);

            // Get the object of DataInputStream
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // Read File Line By Line
            while ((strLine = br.readLine()) != null) { // NOPMD
                if (!strLine.contains("list>")) {
                    fileContents.append(strLine);
                    fileContents.append("\n"); // NOPMD
                }
            }
            // Close the input stream
            in.close();
            // CHECKSTYLE:OFF
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e.getMessage());// NOPMD
        }
        return fileContents.toString();
    }

    private void copyMergedFile(String mergedFileName, String mergedFileContents) { // NOPMD
        try {
            final BufferedWriter out = new BufferedWriter(new FileWriter(mergedFileName));
            mergedFileContents = "<list>" + "\n" + mergedFileContents + "</list>";
            out.write(mergedFileContents);
            out.close();
            System.out.println("Merging Done");// NOPMD
        } catch (IOException e) { // NOPMD
            System.err.println("Exception while copying the merged file: " + e.getMessage()); // NOPMD
        }
    }

    private String[] convertDelimStringToArray(String str, String strDelim) {
        try {
            String[] arstrRet = null;
            arstrRet = str.split(strDelim);
            return arstrRet;
        } catch (Exception ex) { // NOPMD
            System.err.println("Exception during convertDelimStringToArray: " + ex.getMessage()); // NOPMD
            return null;
        }
    }

}
