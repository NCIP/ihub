/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.caxchange.messaging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

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
            for (strLine = br.readLine(); strLine != null; strLine = br.readLine()) {
                if (!strLine.contains("list>")) {
                    fileContents.append(strLine);
                    fileContents.append('\n');
                }
            }

            // Close the input stream
            in.close();
        } catch (IOException e) {
            fileContents.append("");
        }
        return fileContents.toString();
    }

    private void copyMergedFile(String mergedFileName, String mergedFileContents) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(mergedFileName));
            final String mergedContents = "<list>" + "\n" + mergedFileContents + "</list>";
            out.write(mergedContents);
            out.close();
        } catch (IOException e) {
            System.err.println("Exception while copying the merged file: " + e.getMessage()); // NOPMD
        }

    }

    private String[] convertDelimStringToArray(String str, String strDelim) {
        try {
            String[] arstrRet = null;
            arstrRet = str.split(strDelim);
            return arstrRet;
        } catch (PatternSyntaxException ex) {
            return null;
        }
    }

}
