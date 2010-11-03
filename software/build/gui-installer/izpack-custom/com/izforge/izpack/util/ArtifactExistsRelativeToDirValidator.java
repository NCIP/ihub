package com.izforge.izpack.util;

import java.util.Map;
import java.io.File;

import com.izforge.izpack.panels.PasswordGroup;
import com.izforge.izpack.panels.ProcessingClient;

/**
 * A validator to enforce that a specified artifact exists on the filesystem relative to specified dir.
 * <p/>
 * This validator will check that the specified artifact exists on the filesystem relative to specified dir.
 *
 * @author Douglas Harley <harleyda@mail.nih.gov>
 */
public class ArtifactExistsRelativeToDirValidator implements
		com.izforge.izpack.panels.Validator {

	static {
		System.out.println("ArtifactExistsRelativeToDirValidator class is loaded!");
	}

	private static final String ARTIFACT_PATH_RELATIVE_TO_TEXT_PARAM = "artifactPathRelativeToText";

	private static final String IS_DIR_PARAM = "isDir";

	public boolean validate(com.izforge.izpack.panels.ProcessingClient processingClient) {
		System.out.println("starting validate(com.izforge.izpack.panels.ProcessingClient)...");
		boolean artifactExists = false;
		try {
			if (!processingClient.hasParams()) {
				throw new RuntimeException("Required params missing: 'artifactPath', 'isDir'.");
			}
			Map<String, String> paramMap = processingClient.getValidatorParams();
			String specifiedDirPath = getTextFromProcessingClient(processingClient);
			System.out.println("specifiedDirPath =" + specifiedDirPath + "=");
			String artifactPathRelativeToText = paramMap.get(ARTIFACT_PATH_RELATIVE_TO_TEXT_PARAM);
			System.out.println("artifactPathRelativeToText =" + artifactPathRelativeToText + "=");
			boolean isDir = Boolean.valueOf(paramMap.get(IS_DIR_PARAM)).booleanValue();
			System.out.println("isDir =" + isDir + "=");
			File artifactFile = new File(specifiedDirPath + File.separator + artifactPathRelativeToText);
			System.out.println("artifactFile.getAbsolutePath() =" + artifactFile.getAbsolutePath() + "=");
			artifactExists = artifactFile.exists();
			if (artifactExists && isDir) {
				artifactExists = artifactFile.isDirectory();
			}
		} catch (Exception exception) {
			System.err.println("Cannot validate if artifact exists: "
					+ exception.getMessage());
			exception.printStackTrace(System.err);
		}
		System.out.println("exiting validate(com.izforge.izpack.panels.ProcessingClient): artifactExists =" + artifactExists + "=");
		return artifactExists;
	}

	private String getTextFromProcessingClient(ProcessingClient client) {
		String textFromProcessingClient = "";
		if (client instanceof PasswordGroup) {
			int numFields = client.getNumFields();
			if (numFields > 0) {
				textFromProcessingClient = client.getFieldContents(0);
			} else {
				// Should never get here, but might as well try and grab some text
				textFromProcessingClient = client.getText();
			}
		} else {
			// default way to retrieve text for validation
			textFromProcessingClient = client.getText();
		}
		return textFromProcessingClient;
	}

}