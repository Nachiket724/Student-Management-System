//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Blob;
import java.io.File;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.query.*;
import org.hibernate.cfg.*;

@SuppressWarnings({"unchecked", "deprecation"})
class ViewPersonal extends JFrame
{
	Container c;
	JLabel lblName,lblNameAns, lblRno, lblMarks1,lblMarks1Ans, lblMarks2,lblMarks2Ans, lblMarks3,lblMarks3Ans,lblImage,lblEmpty,lblEmpty1,lblEmpty2,lblEmpty3,lblEmpty4,lblEmpty5,lblEmpty6;
	JButton btnBack,btnSearch;
	JTextField txtRno;
	
	ViewPersonal()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		
		lblRno=new JLabel("Enter Roll No.");
		lblRno.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		lblEmpty=new JLabel("");
		lblEmpty.setPreferredSize(new Dimension(400,5));
		
		txtRno=new JTextField(8);
		txtRno.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		lblName=new JLabel("Student Name is : ");
		lblName.setFont(new Font("TIMES_ROMAN", Font.BOLD, 18));
				
		lblNameAns=new JLabel("");
		lblNameAns.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks1=new JLabel("Student Scored in Physics is : ");
		lblMarks1.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks1Ans=new JLabel("");
		lblMarks1Ans.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks2=new JLabel("Student Scored in Chemistry is : ");
		lblMarks2.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks2Ans=new JLabel("");
		lblMarks2Ans.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks3=new JLabel("Student Scored in Maths is : ");
		lblMarks3.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblMarks3Ans=new JLabel("");
		lblMarks3Ans.setFont(new Font("TIMES_ROMAN", Font.BOLD , 18));
		
		lblImage=new JLabel("");
		lblImage.setPreferredSize(new Dimension(150,150));
		
		btnBack=new JButton("Back");
		btnBack.setFont(new Font("TIMES_ROMAN", Font.BOLD +Font.ITALIC, 18));
		
		btnSearch=new JButton("Search");
		btnSearch.setFont(new Font("TIMES_ROMAN", Font.BOLD+Font.ITALIC , 18));
		
		lblEmpty1=new JLabel("");
		lblEmpty1.setPreferredSize(new Dimension(400,5));
		
		lblEmpty2=new JLabel("");
		lblEmpty2.setPreferredSize(new Dimension(400,10));
		
		lblEmpty3=new JLabel("");
		lblEmpty3.setPreferredSize(new Dimension(400,3));
		
		lblEmpty4=new JLabel("");
		lblEmpty4.setPreferredSize(new Dimension(400,3));
		
		lblEmpty5=new JLabel("");
		lblEmpty5.setPreferredSize(new Dimension(400,3));
		
		lblEmpty6=new JLabel("");
		lblEmpty6.setPreferredSize(new Dimension(400,7));
		
		c.add(lblRno);
		c.add(txtRno);
		c.add(btnSearch);
		c.add(lblEmpty);
		c.add(lblImage);
		c.add(lblEmpty1);
		c.add(lblName);
		c.add(lblNameAns);
		c.add(lblEmpty3);
		c.add(lblMarks1);
		c.add(lblMarks1Ans);
		c.add(lblEmpty4);
		c.add(lblMarks2);
		c.add(lblMarks2Ans);
		c.add(lblEmpty5);
		c.add(lblMarks3);
		c.add(lblMarks3Ans);
		c.add(lblEmpty6);
		c.add(btnBack);
		
		
		ActionListener e1=(ae) -> { StudentManagementSystem a=new StudentManagementSystem(); dispose();};
		btnBack.addActionListener(e1);
		
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					String rno=txtRno.getText().replaceAll("\\s","");;
					if(rno.isBlank())
					{
						JOptionPane optionPane = new JOptionPane("Enter the Blank Spaces",JOptionPane.WARNING_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Enter the Roll Number");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					else
					{
						try
						{
							int rno1=Integer.parseInt(rno);
							HbHandler hb=new HbHandler();
							java.util.ArrayList<String> stu=hb.getPerStudent(rno1);
							lblNameAns.setText(stu.get(0));
							lblMarks1.setText(stu.get(0)+" Scored Marks in Physics : ");
							lblMarks1Ans.setText(stu.get(1)+" Marks");
							lblMarks2.setText(stu.get(0)+" Scored Marks in Chemistry : ");
							lblMarks2Ans.setText(stu.get(2)+" Marks");
							lblMarks3.setText(stu.get(0)+" Scored Marks in Maths : ");
							lblMarks3Ans.setText(stu.get(3)+" Marks");
							
							Configuration cfg=new Configuration();
							cfg.configure("hibernate.cfg.xml");
							SessionFactory sfact=cfg.buildSessionFactory();
							Session session=null;
							Blob viewImage=null;
							try
							{
								session=sfact.openSession();
								org.hibernate.query.Query q1=session.createQuery("from Student where rno=:rn");
								q1.setParameter("rn",rno1);
								java.util.List<Student> stu1=q1.list();
								
								for(Student student:stu1)
								{
									
									viewImage=student.getImage();
								}
								byte[] imageByte = viewImage.getBytes(1, (int) viewImage.length());
								InputStream is=new ByteArrayInputStream(imageByte);
								BufferedImage imag=ImageIO.read(is);
								Image dimg = imag.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),Image.SCALE_SMOOTH);
								ImageIcon icon =new ImageIcon(dimg);
								lblImage.setIcon(icon) ;  
								
							}
							catch(Exception e)
							{
								
							}
							finally
							{
								session.close();
								sfact.close();
							}
						}
						catch(NumberFormatException e)
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Enter Only Integer");
						}
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(new JDialog(),"Record Not Found");
				}
			}
		});
		
		setTitle("View Student ");
		setSize(480,530);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
	