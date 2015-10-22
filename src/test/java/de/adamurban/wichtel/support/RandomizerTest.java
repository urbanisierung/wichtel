package de.adamurban.wichtel.support;

import org.junit.Assert;

import java.util.Map;

import org.junit.Test;

public class RandomizerTest {
	
	@Test
	public void testWithPropertyFile() throws Exception {
		Randomizer randomizer = new Randomizer(Wichteln.PARTICIPANTS);
		Map<String, String> wichtel = randomizer.getWichtel();
		for (final String w : wichtel.keySet()) {
			Assert.assertFalse(w.equals(wichtel.get(w)));
		}
	}
}
