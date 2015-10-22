package de.adamurban.wichtel.support;

import java.util.HashMap;
import java.util.Map;

public class Wichteln {
	
	public final static String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
	public final static String PARTICIPANTS = DIRECTORY + "participants.properties";
	public final static String CREDENTIALS = DIRECTORY + "wichtel.properties";
	public final static String MAIL = DIRECTORY + "mail.txt";

	public static void main(String[] args) throws Exception {
		// Randomize
		Randomizer randomizer = new Randomizer(PARTICIPANTS);
		Map<String, String> wichtel = randomizer.getWichtel();
		// Mail
		MailContent mailContent = new MailContent(MAIL);
		Mailer mailer = new Mailer(CREDENTIALS);
		for (final String w : wichtel.keySet()) {
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("${presenter}", w);
			replacements.put("${presentee}", wichtel.get(w));
			mailer.send(randomizer.getMailAddress(w), "Grüße vom Wichtel-Mailer!!!", mailContent.replace(replacements));
		}
		// mailer.send("mail@mail.com", "Wichtel Log", Randomizer.getWichtel(wichtel));

	}

}
