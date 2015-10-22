package de.adamurban.wichtel.support;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import org.junit.Test;

public class MailContentTest {
	
	@Test
	public void testReplacements() throws Exception {
		MailContent c = new MailContent(Wichteln.MAIL);
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("${presenter}", "Presenter");
		replacements.put("${presentee}", "Presentee");
		final String text = c.replace(replacements);
		Assert.assertFalse(text.contains("${presenter}"));
		Assert.assertFalse(text.contains("${presentee}"));
	}

}
