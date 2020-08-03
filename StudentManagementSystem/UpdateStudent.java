//package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.*;
import java.sql.Blob;

class UpdateStudent extends JFrame
{
	Container c;
	JLabel lblRno,lblName,lblMarks1,lblMarks2,lblMarks3,lblImage,lblImageHeader;
	JTextField txtRno,txtName,txtMarks1,txtMarks2,txtMarks3;
	JButton btnSave,btnBack,btnChoose;
	private String regexName="^[A-Za-z][a-zA-Z ]+$";
	private String regexImage="([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";
	
	String fileName=null;
	byte[] person_image=null;
	
	UpdateStudent()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		
		lblRno=new JLabel("Enter Roll No. ");
		lblRno.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtRno=new JTextField(20);
		txtRno.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		lblName=new JLabel("Enter Student's Name ");
		lblName.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtName=new JTextField(20);
		txtName.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		lblMarks1=new JLabel("Enter Physics Marks ");
		lblMarks1.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtMarks1=new JTextField(20);
		txtMarks1.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		lblMarks2=new JLabel("Enter Chemistry Marks ");
		lblMarks2.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtMarks2=new JTextField(20);
		txtMarks2.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		lblMarks3=new JLabel("Enter Maths Marks ");
		lblMarks3.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		txtMarks3=new JTextField(20);
		txtMarks3.setFont(new Font("TIMES_ROMAN", Font.PLAIN, 16));
		
		btnSave=new JButton("Save");
		btnSave.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		btnBack=new JButton("Back");
		btnBack.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		lblImageHeader=new JLabel("Upload Student Profile Picture");
		lblImageHeader.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		lblImage=new JLabel("");
		
		btnChoose=new JButton("Upload");
		btnChoose.setFont(new Font("TIMES_ROMAN", Font.BOLD + Font.ITALIC, 18));
		
		lblImage.setPreferredSize(new Dimension(140,140));
		
		c.add(lblRno);
		c.add(txtRno);
		c.add(lblName);
		c.add(txtName);
		c.add(lblMarks1);
		c.add(txtMarks1);
		c.add(lblMarks2);
		c.add(txtMarks2);
		c.add(lblMarks3);
		c.add(txtMarks3);
		c.add(btnSave);
		c.add(btnBack);
		c.add(lblImageHeader);
		c.add(lblImage);
		c.add(btnChoose);
		
		ActionListener e1=(ae) -> { StudentManagementSystem a=new StudentManagementSystem(); dispose();};
		btnBack.addActionListener(e1);
		
		
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String rno=txtRno.getText().replaceAll("\\s","");
				String name=txtName.getText();
				String marks1=txtMarks1.getText().replaceAll("\\s","");
				String marks2=txtMarks2.getText().replaceAll("\\s","");
				String marks3=txtMarks3.getText().replaceAll("\\s","");
			
				Pattern pattern=Pattern.compile(regexName);
				Matcher matcher=pattern.matcher(name);
			
				if(rno.isBlank()|| name.isBlank() || marks1.isBlank()|| marks2.isBlank() || marks3.isBlank()||person_image==null)
				{
					JOptionPane optionPane = new JOptionPane("Enter the Blank Spaces",JOptionPane.WARNING_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Enter All Fields");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else
				{
					
					try
					{
						int rno1=Integer.parseInt(rno);
						int marks11=Integer.parseInt(marks1);
						int marks22=Integer.parseInt(marks2);
						int marks33=Integer.parseInt(marks3);
						
					
						if(!matcher.matches())
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Name must contain more than one Alphabets !");
						}
					
						else if(!(marks11>=0 && marks11<=100))
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Enter Right Marks of Physics");
						}
						else if(!(marks22>=0 && marks22<=100))
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Enter Right Marks of Chemistry");
						
						}
						else if(!(marks33>=0 && marks33<=100))
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Enter Right Marks of Maths");
						
						}
						else if(person_image==null)
						{
							JFrame jf=new JFrame();
							jf.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(jf,"Upload Profile Photo ");
						}
						else
						{
							HbHandler hb=new HbHandler();
							hb.updateStudent(rno1,name,marks11,marks22,marks33,person_image);
							txtRno.setText("");
							txtName.setText("");
							txtMarks1.setText("");
							txtMarks2.setText("");
							txtMarks3.setText("");
							lblImage.setIcon(null);
							person_image=null;
							txtRno.requestFocusInWindow();
						}
					}
					catch(NumberFormatException e)
					{
						JOptionPane optionPane = new JOptionPane("Enter Integers Only",JOptionPane.WARNING_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Enter Integer Where Ever Required !");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}
			}
		});
		btnChoose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser imageChooser=new JFileChooser();
				imageChooser.showOpenDialog(null);
				File fileImage=imageChooser.getSelectedFile();
				fileName=fileImage.getAbsolutePath();
				
				Pattern pattern1=Pattern.compile(regexImage);
				Matcher matcher1=pattern1.matcher(fileName);
				if(!(matcher1.matches()))
				{
					JOptionPane.showMessageDialog(new JDialog(),"Extensions with 'jpg' or 'jpeg' or 'png' is required !!");
					lblImage.setIcon(null);
					fileName=null;
				}
				else
				{
					ImageIcon imageIcon=new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(lblImage.getWidth(),lblImage.getHeight(),Image.SCALE_SMOOTH ));
					lblImage.setIcon(imageIcon);
					try
					{
						File image=new File(fileName);
						FileInputStream fis=new FileInputStream(image);
						ByteArrayOutputStream bos=new ByteArrayOutputStream();
						byte[] buf=new byte[fis.available()];
						for(int readNum;(readNum=fis.read(buf))!=-1;)
						{
							bos.write(buf,0,readNum);
						}
						person_image=bos.toByteArray();
					}
					catch(Exception e)
					{
						JOptionPane optionPane = new JOptionPane(e, JOptionPane.ERROR_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Failure");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}
			}
		});
		
		setTitle("Update Student");
		setSize(350,580);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}