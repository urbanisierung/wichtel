package de.adamurban.wichtel.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.*;

public class MailContent {
	
	private String text;
	
	public MailContent(final String fileName) throws IOException {
		FileInputStream inputStream = new FileInputStream(fileName);
		try {
		    text = IOUtils.toString(inputStream);
		} finally {
		    inputStream.close();
		}
	}
	
	public String replace(final Map<String, String> replacements) {
		String finalText = text;
		for (final String key : replacements.keySet()) {
			finalText = finalText.replace(key, replacements.get(key));
		}
		return finalText;
	}

}
