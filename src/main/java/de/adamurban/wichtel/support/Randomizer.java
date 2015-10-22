package de.adamurban.wichtel.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
	
	final Map<String, String> names;
	
	public Randomizer(final Map<String, String> names) {
		this.names = names;
	}
	
	// Format:
	// name=email address
	// Example:
	// Adam=adam@gmail.com
	public Randomizer(final String propertyFile) throws IOException {
		InputStream input = new FileInputStream(propertyFile);
		final Properties prop = new Properties();
		prop.load(input);
		names = new HashMap<String, String>();
		for (final Object key : prop.keySet()) {
			final String name = (String) key;
			names.put(name, prop.getProperty(name));
		}
	}
	
	public String getMailAddress(final String name) {
		return names.get(name);
	}
	
	public Map<String, String> getWichtel() {
		Map<String, String> wichtel = new HashMap<String, String>();
		
		List<String> presenter = new ArrayList<String>();
		List<String> presentee = new ArrayList<String>();
		
		for (final String name : names.keySet()) {
			presenter.add(name);
			presentee.add(name);
		}
		
		int max = presenter.size();
		
		for (int i = 0; i < max; i++) {
			int nextPresenter = ThreadLocalRandom.current().nextInt(0, presenter.size());
			int nextPresentee = ThreadLocalRandom.current().nextInt(0, presentee.size());
			while (presenter.get(nextPresenter).equals(presentee.get(nextPresentee))) {
				nextPresenter = ThreadLocalRandom.current().nextInt(0, presenter.size());
				nextPresentee = ThreadLocalRandom.current().nextInt(0, presentee.size());	
			}
			wichtel.put(presenter.get(nextPresenter), presentee.get(nextPresentee));
			presenter.remove(nextPresenter);
			presentee.remove(nextPresentee);
		}
		
		return wichtel;
	}
	
	public static String getWichtel(final Map<String, String> wichtel) {
		final StringBuffer str = new StringBuffer();
		for (final String w : wichtel.keySet()) {
			str.append(w + ": " + wichtel.get(w) + "\n");
		}
		return str.toString();
	}

}
