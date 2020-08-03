//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewStudent extends JFrame
{
	Container c;
	JLabel lblEmpty;
	TextArea taData;
	JButton btnBack;
	
	ViewStudent()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		
		lblEmpty=new JLabel("");
		lblEmpty.setPreferredSize(new Dimension(600,14));
		
	
		taData=new TextArea(30,60);
		taData.setEditable(false);
		btnBack=new JButton("Back");
		btnBack.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		c.add(taData);
		c.add(lblEmpty);
		c.add(btnBack);
		
		ActionListener e1=(ae) -> { StudentManagementSystem a=new StudentManagementSystem(); dispose();};
		btnBack.addActionListener(e1);
		
		HbHandler hb=new HbHandler();
		String data=hb.getStudent();
		taData.setText(data);
		taData.setFont(new Font("TIMES_ROMAN", Font.BOLD , 14));
		
		setTitle("View Student");
		setSize(700,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}