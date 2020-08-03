//package p1;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({"unchecked", "deprecation"})
class JCharts extends JFrame
{
	JCharts()
	{
		DefaultCategoryDataset d1=new DefaultCategoryDataset();
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact=cfg.buildSessionFactory();
		
		Session session=null;
		try
		{
			session=sfact.openSession();
			java.util.List<Student> stu=new ArrayList<>();
			stu=session.createQuery("from Student").list();
			for(Student s:stu)
			{
				d1.addValue(s.getMarks1(),s.getName(),"Physics");
				d1.addValue(s.getMarks2(),s.getName(),"Chemistry");
				d1.addValue(s.getMarks3(),s.getName(),"Maths");
			}
		}
		catch(Exception e)
		{
			System.out.println("Issue "+e);
		}
		finally
		{
			JFreeChart chart =ChartFactory.createBarChart("Marks","Subjects","Marks",
			d1,PlotOrientation.VERTICAL,true,false,false);
			ChartPanel cp= new ChartPanel(chart);
			setContentPane(cp);
			
			
			setTitle("Marks Sheet");
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
			sfact.close();
			session.close();
		}
	}
}
	