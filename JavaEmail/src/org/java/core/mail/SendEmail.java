package org.java.core.mail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail
{
   public static void main(String [] args)
   {    
	   
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
	   //Multipart multipart = new MimeMultipart();
	   try{  
		   String template = readFile();
		   System.out.println("----------------------");
		 //  if(true)
			//   return;
		   InternetAddress from = new InternetAddress("info@caredoha.com","Care Doha");
		   InternetAddress to = new InternetAddress("askeralim@gmail.com");
		     
		   message.setSentDate(new Date());
		   message.setFrom(from);
		   message.addRecipient(Message.RecipientType.TO, to);
		     
		   message.setSubject("Career Insight Registration");
		   
		   message.setContent(template, "text/html");
		   
		   Transport tr = s.getTransport("smtp");
		   tr.connect("localhost", "info@caredoha.com", "Mardan@650");
		   message.saveChanges();
		   tr.sendMessage(message, message.getAllRecipients());
		   tr.close();
		   System.out.println("--------------- Mail Sent --------------");
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   System.out.println("Sent message successfully....");
   }
   private static String readFile( ) throws IOException, URISyntaxException {
	    BufferedReader reader = new BufferedReader( new FileReader (new File(SendEmail.class.getResource("template.html").toURI())));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while( ( line = reader.readLine() ) != null ) {
	            stringBuilder.append( line );
	            stringBuilder.append( ls );
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
}
