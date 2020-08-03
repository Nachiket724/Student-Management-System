//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.List;
import com.itextpdf.text.Font;
import java.io.*;
import com.itextpdf.text.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.query.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import java.sql.*;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.hibernate.cfg.Configuration;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.lang.Comparable.*;


@SuppressWarnings({"unchecked", "deprecation"})
class SendAcknow implements Runnable
{
	private String email;
	
	public SendAcknow(String email)
	{
		this.email=email;
	}
	
	public void run()
	{
		try
		{
			DefaultCategoryDataset d1=new DefaultCategoryDataset();
			
			Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18,Font.BOLDITALIC);
			Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,Font.BOLD);
			String file_name="YOUR_FILE_PATH"+"\\Student.pdf";
			Document document=new Document();
							
			PdfWriter.getInstance(document,new FileOutputStream(file_name));
			document.open();
							
			Paragraph paragraph=new Paragraph("This PDF will show you the details of each and every Individual. !!",f1);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
							
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
							
			PdfPTable table=new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(20f);
			table.setSpacingAfter(20f);
							
			float[] colWidth={10f,10f,10f,10f,10f,10f,14f};
			table.setWidths(colWidth);
							
			PdfPCell c1=new PdfPCell(new Phrase("Sr No.",bold));
							
			c1.setFixedHeight(32f);
			table.addCell(c1);
							
			PdfPCell c2=new PdfPCell(new Phrase("Roll No.",bold));
							
			c2.setFixedHeight(32f);
			table.addCell(c2);
							
			PdfPCell c3=new PdfPCell(new Phrase("Name",bold));
							
			c3.setFixedHeight(32f);
			table.addCell(c3);
							
			PdfPCell c4=new PdfPCell(new Phrase("Physics",bold));
							
			c4.setFixedHeight(32f);
			table.addCell(c4);
							
			PdfPCell c5=new PdfPCell(new Phrase("Chemistry",bold));
							
			c5.setFixedHeight(32f);
			table.addCell(c5);
							
			PdfPCell c6=new PdfPCell(new Phrase("Maths",bold));
							
			c6.setFixedHeight(32f);
			table.addCell(c6);
							
			PdfPCell c7=new PdfPCell(new Phrase("Profile Picture",bold));
							
			c7.setFixedHeight(32f);
			table.addCell(c7);
							
			table.setHeaderRows(1);
			
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sfact=cfg.buildSessionFactory();
			Session session=null;
			try
			{
				session=sfact.openSession();
				java.util.List<Student> stu=new ArrayList<>();
				stu=session.createQuery("from Student").list();
				int i=0;
				Blob viewImage=null;
				for(Student s:stu)
				{
									
					i++;
					viewImage=s.getImage();
					byte[] imageByte = viewImage.getBytes(1, (int) viewImage.length());
					FileOutputStream fout=new FileOutputStream("YOUR_FILE_PATH"+s.getRno()+".jpg"); 
					fout.write(imageByte);
					fout.close(); 
									
					Image img = Image.getInstance("YOUR_FILE_PATH"+s.getRno()+".jpg");
					img.scaleAbsolute(100f, 100f);
					table.addCell(String.valueOf(i));
					table.addCell(String.valueOf(s.getRno()));
					table.addCell(s.getName());
					table.addCell(String.valueOf(s.getMarks1()));
					table.addCell(String.valueOf(s.getMarks2()));
					table.addCell(String.valueOf(s.getMarks3()));
					table.addCell(new PdfPCell(img));
					d1.addValue(s.getMarks1(),s.getName(),"Physics");
					d1.addValue(s.getMarks2(),s.getName(),"Chemistry");
					d1.addValue(s.getMarks3(),s.getName(),"Maths");
				}
								
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JDialog(),"Record Issue");
								
			}
			finally
			{
				session.close();
				sfact.close();
				JFreeChart chart =ChartFactory.createBarChart("Marks","Subjects","Marks",
				d1,PlotOrientation.VERTICAL,true,false,false);
								
				try
				{
					File marks=new File("YOUR_FILE_PATH"+"\\bar.jpeg");
					ChartUtilities.saveChartAsJPEG(marks,chart,500,400);
					
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
								
				document.add(table);
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				Paragraph paragraph1=new Paragraph("This is the performance of every Individual.",f2);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph1);
				document.add(new Paragraph(" "));
								
								
				document.add(com.itextpdf.text.Image.getInstance("YOUR_FILE_PATH"+"\\bar.jpeg"));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				
								
				document.close();
								
				EmailSender es=new EmailSender();
				es.sendEmail(email);
								
							
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
		
	}
}