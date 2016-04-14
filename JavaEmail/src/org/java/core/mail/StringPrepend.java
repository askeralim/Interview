package org.java.core.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class StringPrepend {

	/**
	 * @param args
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static void main(String[] args) throws URISyntaxException,
			IOException {

		// String format below will add leading zeros (the %0 syntax)
		// to the number above.
		// The length of the formatted string will be 7 characters.
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.localhost", "caredoha.com");

		Session s = Session.getInstance(props, null);
		s.setDebug(true);

		MimeMessage message = new MimeMessage(s);

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				SendEmail.class.getResource("contact.csv").toURI())));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		boolean done = false;
		String content = readFile();
		String code = "";
		try {
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");
				if (data[1] != null && data[2] != null && data[3] != null)
					code = "CAREERINS"
							+ String.format("%04d", Integer.valueOf(data[1]));
						//	+ "   \t\t" + data[2] + " \t\t " + data[3];
				System.out.println(code);
				if (!done) {
					done = true;
					content = content.replaceAll("#NAME#", data[2]);
					content = content.replaceAll("#REG_NUM#", code);
					sendMail(props, "jabirjalal@live.com", content);
					//System.out.println(content);
				}
			}

			// return stringBuilder.toString();
		} finally {
			reader.close();
		}
		// String formatted = String.format("%03d", Integer.valueOf("3"));

		// System.out.println("Number with leading zeros: " + formatted);

	}

	private static void sendMail(Properties props, String email, String content) {
		Session s = Session.getInstance(props, null);
		s.setDebug(true);

		MimeMessage message = new MimeMessage(s);
		// Multipart multipart = new MimeMultipart();
		try {
			String template = readFile();
			System.out.println("----------- "+email+" -----------");
			// if(true)
			// return;
			InternetAddress from = new InternetAddress("info@caredoha.com",
					"Care Doha");
			InternetAddress to = new InternetAddress(email);

			message.setSentDate(new Date());
			message.setFrom(from);
			message.addRecipient(Message.RecipientType.TO, to);

			message.setSubject("Career Insight Registration");

			message.setContent(content, "text/html");

			Transport tr = s.getTransport("smtp");
			tr.connect("localhost", "info@caredoha.com", "Mardan@650");
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
			System.out.println("--------------- Mail Sent --------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String readFile() throws IOException, URISyntaxException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				SendEmail.class.getResource("template.html").toURI())));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}

}
