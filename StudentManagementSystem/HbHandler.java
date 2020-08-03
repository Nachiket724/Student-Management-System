//package p1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.query.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.sql.Blob;
import java.io.FileInputStream;
import java.io.*;
import java.sql.*;
import org.hibernate.cfg.Configuration;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;

@SuppressWarnings({"unchecked", "deprecation"})
class HbHandler
{
	public static String getStudent()
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact= cfg.buildSessionFactory();
		Session session=null;
		String data="";
		try
		{
			session=sfact.openSession();
			java.util.List<Student> stu=new ArrayList<>();
			stu=session.createQuery("from Student").list();
			for(Student s:stu)
			{
				data=data+"Roll Number : "+s.getRno()+"\n"+
						"Student Name : "+s.getName()+"\n"+
						"Physics Marks : "+s.getMarks1()+"\n"+
						"Chemistry Marks : "+s.getMarks2()+"\n"+
						"Maths Marks : "+s.getMarks3()+"\n\n";
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
			return data;
		}
	}
	public static ArrayList<String> getPerStudent(int rn)
	{
		ArrayList<String> viewPersonal=new ArrayList<>();
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact=cfg.buildSessionFactory();
		Session session=null;
		try
		{
			session=sfact.openSession();
			org.hibernate.query.Query q1=session.createQuery("from Student where rno=:rn");
			q1.setParameter("rn",rn);
			java.util.List<Student> stu1=q1.list();
			for(Student student:stu1)
			{
				viewPersonal.add(student.getName());
				viewPersonal.add(String.valueOf(student.getMarks1()));
				viewPersonal.add(String.valueOf(student.getMarks2()));
				viewPersonal.add(String.valueOf(student.getMarks3()));
			}
		}
		catch(Exception e)
		{
			
			JOptionPane optionPane = new JOptionPane("Record Not Found !", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		finally
		{
			session.close();
			sfact.close();
			return viewPersonal;
		}	
				
	}
	public static Boolean addStudent(int rno,String name,int marks1,int marks2,int marks3,byte[] image)
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact= cfg.buildSessionFactory();
		String data=null;
		Session session=null;
		Transaction t=null;
		Boolean flag=false;
		try
		{
			session=sfact.openSession();
			t=session.beginTransaction();
			Blob photo=Hibernate.getLobCreator(session).createBlob(image);
			Student s=new Student(rno,name,marks1,marks2,marks3);
			s.setImage(photo);
			session.save(s);
			t.commit();
			data="Record Inserted SuccessFully";
			JOptionPane optionPane = new JOptionPane(data, JOptionPane.INFORMATION_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Success");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			flag=true;
		}
		catch(Exception e)
		{
			t.rollback();
			data="Roll No Already Exists";
			JOptionPane optionPane = new JOptionPane(data, JOptionPane.WARNING_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			flag=false;
		}
		finally
		{
			sfact.close();
			session.close();
			return flag;
		}
	}
	
	public static void updateStudent(int rno,String name,int marks1,int marks2,int marks3,byte[] image)	
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact= cfg.buildSessionFactory();
		String data=null;
		Session session=null;
		Transaction t=null;
		try
		{
			session=sfact.openSession();
			t=session.beginTransaction();
			Student s=(Student) session.get(Student.class,rno);
			if(s!=null)
			{
				Blob photo=Hibernate.getLobCreator(session).createBlob(image);
				s.setName(name);
				s.setMarks1(marks1);
				s.setMarks2(marks2);
				s.setMarks3(marks3);
				s.setImage(photo);
				session.save(s);
				t.commit();
				data="Record Updated SuccessFully";
				JOptionPane optionPane = new JOptionPane(data, JOptionPane.INFORMATION_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Success");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
			else
			{
				data="Record Not Found !!";
				JOptionPane optionPane = new JOptionPane(data, JOptionPane.ERROR_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Failure");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
		}
		catch(Exception e)
		{
			t.rollback();
			data="Record Already Exists !!";
			JOptionPane optionPane = new JOptionPane(data, JOptionPane.WARNING_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		finally
		{
			sfact.close();
			session.close();
		}
	}
	
	public static void deleteStudent(int rno)	
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact= cfg.buildSessionFactory();
		
		Session session=null;
		Transaction t=null;
		try
		{
			session=sfact.openSession();
			t=session.beginTransaction();
			Student s=(Student) session.get(Student.class,rno);
			if(s!=null)
			{
				session.delete(s);
				t.commit();
				JOptionPane optionPane = new JOptionPane("Record Deleted SuccessFully", JOptionPane.INFORMATION_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Success");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
			else
			{
				JOptionPane optionPane = new JOptionPane("Record not Found", JOptionPane.ERROR_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Failure");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
		}
		catch(Exception e)
		{
			t.rollback();
			JOptionPane.showMessageDialog(new JDialog(),"record deleted issue");
			JOptionPane optionPane = new JOptionPane("Record Deleted Issue", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Failure");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		finally
		{
			sfact.close();
			session.close();
		}
	}
}