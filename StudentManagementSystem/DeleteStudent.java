//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteStudent extends JFrame
{
	Container c;
	JLabel lblRno,lblEmpty,lblEmpty2;
	JTextField txtRno;
	JButton btnSave,btnBack;
	
	DeleteStudent()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		
		lblEmpty=new JLabel("");
		lblEmpty.setPreferredSize(new Dimension(350,40));
		
		lblEmpty2=new JLabel("");
		lblEmpty2.setPreferredSize(new Dimension(350,5));
		
		lblRno=new JLabel("Enter Roll No. to delete the record");
		lblRno.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtRno=new JTextField(19);
		txtRno.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		btnSave=new JButton("Delete");
		btnSave.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		btnBack=new JButton("Back");
		btnBack.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		c.add(lblEmpty);
		c.add(lblRno);
		c.add(lblEmpty2);
		c.add(txtRno);
		c.add(btnSave);
		c.add(btnBack);
		
		ActionListener e1=(ae) -> { StudentManagementSystem a=new StudentManagementSystem(); dispose();};
		btnBack.addActionListener(e1);
		
		
		ActionListener e2=(ae) ->
		{
			String rno=txtRno.getText().replaceAll("\\s","");
			if(rno.isBlank())
			{
				JOptionPane optionPane = new JOptionPane("Enter All Roll Number to delete !",JOptionPane.WARNING_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Enter the Blank Spaces");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
			else
			{
				try
				{
					int rno1=Integer.parseInt(rno);
					HbHandler hb=new HbHandler();
					hb.deleteStudent(rno1);
					txtRno.setText("");
					txtRno.requestFocusInWindow();
				}
				catch(NumberFormatException e)
				{
					JOptionPane optionPane = new JOptionPane("Enter Integers Only",JOptionPane.WARNING_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Enter Integer Where Ever Required !");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			}
		};
		btnSave.addActionListener(e2);
		
		setTitle("Delete Student");
		setSize(350,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}