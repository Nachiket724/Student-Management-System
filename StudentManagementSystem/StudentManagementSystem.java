//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StudentManagementSystem extends JFrame implements Runnable
{
	
	
		Container c;
		JButton btnAdd,btnView,btnUpdate,btnDelete,btnCharts,btnViewPersonal,btnAckn,btnBack;
		private String regexEmail="^[A-Za-z0-9+_.-]+@(.+)$";
		
		public void run()
		{
			c=getContentPane();
			c.setLayout(new GridLayout(4,2));
			btnAdd=new JButton("Add Student");
			btnAdd.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnView=new JButton("View Student");
			btnView.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnUpdate=new JButton("Update Student");
			btnUpdate.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnDelete=new JButton("Delete Student");
			btnDelete.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnCharts=new JButton("Charts");
			btnCharts.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnViewPersonal=new JButton("View Personal");
			btnViewPersonal.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnAckn=new JButton("Send Acknowledgement");
			btnAckn.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			btnBack=new JButton("Log Out");
			btnBack.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
			
			c.add(btnAdd);
			c.add(btnView);
			c.add(btnUpdate);
			c.add(btnDelete);
			c.add(btnCharts);
			c.add(btnViewPersonal);
			c.add(btnAckn);
			c.add(btnBack);
			
			ActionListener viewStu=(ae) ->	{ ViewStudent vS=new ViewStudent();dispose();};
			btnView.addActionListener(viewStu);
			
			ActionListener addStu=(ae) ->	{ AddStudent aS=new AddStudent();dispose();};
			btnAdd.addActionListener(addStu);
			
			ActionListener updateStu=(ae) ->	{UpdateStudent uS=new UpdateStudent();dispose();};
			btnUpdate.addActionListener(updateStu);
			
			ActionListener deleteStu=(ae) ->	{DeleteStudent dS=new DeleteStudent();dispose();};
			btnDelete.addActionListener(deleteStu);
			
			ActionListener chartsStu=(ae) ->	
			{
				JCharts jc=new JCharts();
			};
			btnCharts.addActionListener(chartsStu);
			
			ActionListener ViewPerStu=(ae) ->	{ViewPersonal vS=new ViewPersonal();dispose();};
			btnViewPersonal.addActionListener(ViewPerStu);
			
			ActionListener back=(ae) ->	
			{
				
				int input = JOptionPane.showConfirmDialog(null,"Do you want to Logout?", "Select an Option...",JOptionPane.YES_NO_CANCEL_OPTION);
				if(input==0)
				{
					MainPage mp=new MainPage();
					dispose();
				}
				
				
			};
			btnBack.addActionListener(back);
			
			
			btnAckn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						String email = JOptionPane.showInputDialog(null,"Enter your Email-ID","Email",JOptionPane.INFORMATION_MESSAGE);
							
						if(!(email.isBlank()))
						{
							Pattern pattern=Pattern.compile(regexEmail);
							Matcher matcher=pattern.matcher(email);
							if(!(matcher.matches()))
							{
								JOptionPane.showMessageDialog(new JDialog(),"Enter Valid Email-Id");
							}
							else
							{
								SendAcknow sb=new SendAcknow(email);
								try
								{
									Thread t1=new Thread(sb);
									t1.start();
								}
								catch(Exception e)
								{
									JOptionPane.showMessageDialog(new JDialog(),e);
								}
								
								
							}
						}
						else 
						{

						}
					}
					catch(NullPointerException ne){}
				}
			});
			
			setTitle("Student Management System");
			setLayout(new GridLayout(4,2));
			setSize(500,500);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
		}
		
		StudentManagementSystem()
		{
			run();
		}
	
}