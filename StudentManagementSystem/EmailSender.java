//package p1;


import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;

class EmailSender
{
    public static void sendEmail(String emailRec)
    {
        Email email=new Email("ORGANIZATION EMAIL-ID","ORGANIZATION PASSWORD");
        
        try {
			email.setFrom("ORGANIZATION EMAIL-ID", "Student Management System");
			
			email.setSubject("Detail Report about the performance of every Individual");
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h3>The Information contained in the email message and\\or attachments may contain confidential information</h3>","text/html");
			
			Multipart multipart = new MimeMultipart();
			
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			
			String filename="YOUR_FILE_PATH_"+"\\Student.pdf";
			
			String file="Student.pdf";
			DataSource source=new FileDataSource(filename);
			
			messageBodyPart.setDataHandler(new DataHandler(source));
			
	        messageBodyPart.setFileName(file);
	        
	        multipart.addBodyPart(messageBodyPart);
	        
	        email.setContent(multipart, null);
			
			email.addRecipient(emailRec);
			email.send();
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(new JDialog(),e);
		} catch (MessagingException e) {
			
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
        
        
    }
}
