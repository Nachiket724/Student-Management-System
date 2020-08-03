//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({"unchecked", "deprecation"})
class MainPage extends JFrame
{
	Container c;
	JLabel lblUser,lblPass,lblEmpty,lblEmpty1,lblEmpty2,lblEmpty3,lblEmpty4;
	JTextField txtUser;
	JPasswordField txtPass;
	JButton btnLogin;
	
	
	MainPage()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lblEmpty=new JLabel("");
		lblEmpty.setPreferredSize(new Dimension(500,100));

		lblUser=new JLabel("Enter Username");
		lblUser.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 22));
		
		lblEmpty1=new JLabel("");
		lblEmpty1.setPreferredSize(new Dimension(500,3));
		
		txtUser=new JTextField(20);
		txtUser.setFont(new Font("TIMES_ROMAN", Font.BOLD , 20));
		
		lblEmpty2=new JLabel("");
		lblEmpty2.setPreferredSize(new Dimension(500,10));
		
		lblPass=new JLabel("Enter Password");
		lblPass.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 22));
		
		lblEmpty3=new JLabel("");
		lblEmpty3.setPreferredSize(new Dimension(500,3));
		
		txtPass=new JPasswordField(20);
		txtPass.setFont(new Font("TIMES_ROMAN", Font.BOLD , 20));
		
		lblEmpty4=new JLabel("");
		lblEmpty4.setPreferredSize(new Dimension(500,10));
		
		btnLogin=new JButton("Login");
		btnLogin.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 22));
		
		c.add(lblEmpty);
		c.add(lblUser);
		c.add(lblEmpty1);
		c.add(txtUser);
		c.add(lblEmpty2);
		c.add(lblPass);
		c.add(lblEmpty3);
		c.add(txtPass);
		c.add(lblEmpty4);
		c.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if((txtUser.getText().equals("rait")) && (txtPass.getText().equals("root")))
				{
					StudentManagementSystem sms=new StudentManagementSystem();
					dispose();
				}
				else if(txtUser.getText().isBlank())
				{
					JOptionPane optionPane = new JOptionPane("Enter the Username",JOptionPane.WARNING_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Enter All Fields");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					txtUser.setText("");
					txtPass.setText("");
					txtUser.requestFocusInWindow();
				}
				else if(txtPass.getText().isBlank())
				{
					JOptionPane optionPane = new JOptionPane("Enter the Password",JOptionPane.WARNING_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Enter All Fields");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					txtUser.setText("");
					txtPass.setText("");
					txtUser.requestFocusInWindow();
				}
				else if(!(txtUser.getText().equals("rait")) || !(txtPass.getText().equals("root")))
				{
					JOptionPane optionPane = new JOptionPane("Enter the correct Roll Number and Password",JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Enter Right Details");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					txtUser.setText("");
					txtPass.setText("");
					txtUser.requestFocusInWindow();
				}
			}
		});
		
		setTitle("Login Page ");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}