# Student-Management-System

This project consists of maintaining the record of every student and perform various statistics based on the marks scored by an individual by using Java SE , Hibernate ORM Framework, JFreeChart and  itextpdf.

## Hibernate ORM Framework

<img src="ss/hibernate-logo.png">


Hibernate is a Java framework that simplifies the development of Java application to interact with the database. It is an open source, lightweight, ORM (Object Relational Mapping) tool. Hibernate implements the specifications of JPA (Java Persistence API) for data persistence.

## JFreeChart

JFreeChart is a free 100% Java chart library that makes it easy for developers to display professional quality charts in their applications.

## How to Run:

  1. <a href="https://www.oracle.com/in/java/technologies/javase-downloads.html" target="_blank">Download and install Java SE Development Kit 14 (JDK 14)</a>
  2. <a href="https://www.oracle.com/database/technologies/xe-prior-releases.html" target="_blank">Download and install Oracle Database Express Edition (XE) Release 11.2.0.2.0</a>
  3. <a href="https://github.com/Nachiket724/Student-Management-System.git" target="_blank">Download the GitHub Repository</a>
  4. Place the Repository in one specific folder.
  5. Open "StudentManagementSystem/hibernate.cfg.xml"
      
      a. Change the configuration and keep your Oracle SQL Database password.
  
      <img src="ss/hib.PNG" width="500">
      
  6. Create a new directory in which you want to save the files and images which are being fetch from the database.
  7. Copy the path of that directory and paste it in the "StudentManagementSystem/SendAcknow.java".
  
     <img src="ss/sendAck1.PNG" width="700" height="70">

     <img src="ss/sendAck2.PNG" width="700" height="70">
     
     <img src="ss/sendAck3.PNG" width="700" height="70">
     
  8. Paste the Organization Email- ID and  Organization Password and paste it in the "StudentManagementSystem/EmailSender.java"
      
      <img src="ss/senderEmail.PNG" width="700" height="70">
      
  9. Copy the same directory path and paste it in the "StudentManagementSystem/SendAcknow.java".
      
      <img src="ss/senderEmail2.PNG" width="700" height="70">
