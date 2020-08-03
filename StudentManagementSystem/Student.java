//package p1;

import java.sql.Blob;
class Student
{
	private int rno;
	private String name;
	private int marks1;
	private int marks2;
	private int marks3;

	private Blob image;
	
	public Student()
	{
		rno=0;
		name="";
		marks1=0;
		marks2=0;
		marks3=0;
		image=null;
	}
	
	public Student(int rno, String name,int marks1,int marks2,int marks3)
	{
		this.rno=rno;
		this.name=name;
		this.marks1=marks1;
		this.marks2=marks2;
		this.marks3=marks3;
		//this.image=image;
	}
	
	public int getRno()				//Accessor
	{
		return rno;
	}
	 
	public void setRno(int rno) 	//Mutator
	{
		this.rno=rno;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int getMarks1()				//Accessor
	{
		return marks1;
	}
	 
	public void setMarks1(int marks1) 	//Mutator
	{
		this.marks1=marks1;
	}
	
	public int getMarks2()				//Accessor
	{
		return marks2;
	}
	 
	public void setMarks2(int marks2) 	//Mutator
	{
		this.marks2=marks2;
	}
	public int getMarks3()				//Accessor
	{
		return marks3;
	}
	 
	public void setMarks3(int marks3) 	//Mutator
	{
		this.marks3=marks3;
	}
	public Blob getImage() 
	{
		return image;
	}

	public void setImage(Blob image) 
	{
		this.image = image;
	}
}