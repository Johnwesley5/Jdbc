package school_17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	private static final String Url="jdbc:mysql://localhost:3306/school_17";
	private static final String uName="root";
	private static final String pass="Wesley@949";
	
	public static Scanner sc=new Scanner(System.in);
	
	public static void insertData(Connection connection) throws SQLException {
		int RollNo,Cls=0,Marks=0;
		String Name="",Section="",DOB="",Sub="";
		
		PreparedStatement ps=connection.prepareStatement("insert into StudentDetails values(?,?,?,?,?);");
		
		System.out.println("Enter student Roll Number:");
		RollNo=sc.nextInt();
        System.out.println("Enter student name:");
        sc.nextLine();
        Name=sc.nextLine();
        System.out.println("Enter student class:");
        Cls=sc.nextInt();
        System.out.println("Enter student section:");
        Section=sc.next();
        System.out.println("Enter the student date-of-birth:");
        DOB=sc.next();
        
        ps.setInt(1, RollNo);
        ps.setString(2, Name);
        ps.setInt(3, Cls);
        ps.setString(4,  Section);
        ps.setString(5, DOB);
        
        PreparedStatement ps1=connection.prepareStatement("insert into StudentMarks values(?,?,?);");
        
        System.out.println("Enter subject name:");
		Sub=sc.next();
		System.out.println("Enter student marks:");
		Marks=sc.nextInt();
		
		ps1.setInt(1, RollNo);
		ps1.setString(2, Sub);
		ps1.setInt(3, Marks);
		
		int rows=ps.executeUpdate();
		int rows1=ps1.executeUpdate();
		
		if(rows==0 || rows1==0) {
			System.out.println("Error occured during Insertion...Try again!!");
		}
		else {
			System.out.println("Insertion completed..");
		}
		
	}
	
	public static void displayData(Connection connection) throws SQLException {
		String que="SELECT SD.RollNo,SD.Name,SD.Cls,SD.Section,SD.DOB, SM.Subject, SM.Marks"+"  FROM StudentDetails SD LEFT JOIN StudentMarks SM ON SD.RollNo = SM.RollNo;";
		
		PreparedStatement ps=connection.prepareStatement(que);
		
		ResultSet rs= ps.executeQuery();
		
		
		while(rs.next())
		{
			int RollNo=rs.getInt("RollNo");
			String Name=rs.getString("Name");
			int Cls=rs.getInt("Cls");
			String Section=rs.getString("Section");
			String DOB=rs.getString("DOB");
			String Subject=rs.getString("Subject");
			int Marks=rs.getInt("Marks");
			
			System.out.println("Roll Number: "+RollNo+"\nName: "+Name+"\nClass: "+Cls+"\nSection: "+Section+"\nDate-of-Birth: "+DOB+"\nSubject: "+Subject+"\nMarks: "+Marks);
		}
	}
	public static void updateData(Connection connection) throws SQLException {
		PreparedStatement ps =connection.prepareStatement("update StudentDetails set Cls=? where RollNo=?");
		
		System.out.println("Enter RollNo");
		int RollNo=sc.nextInt();
		System.out.println("Enter the class");
		int Cls=sc.nextInt();
		
		
		ps.setInt(1, Cls);
		ps.setInt(2, RollNo);
		
		PreparedStatement ps1=connection.prepareStatement("update StudentMarks set Marks=? where RollNo=?");
		System.out.println("Enter the marks:");
		int Marks=sc.nextInt();
		
		ps1.setInt(1, Marks);
		ps1.setInt(2, RollNo);
		
		int rows=ps.executeUpdate();
		ps1.executeUpdate();
		
		if(rows==0) {
			System.out.println("Try again!!");
		}
		else {
			System.out.println("Data updated..");
		}
	}
	
	public static void deleteData(Connection connection) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("delete from StudentDetails where RollNo=?");
		
		System.out.println("Enter the Roll Number of the student:");
		int RollNo=sc.nextInt();
		ps.setInt(1, RollNo);
		
		PreparedStatement ps1=connection.prepareStatement("delete from StudentMarks where RollNo=?");
		ps1.setInt(1, RollNo);
		
		int rows=ps.executeUpdate();
		int rows1=ps1.executeUpdate();
		
		if(rows==0 || rows1==0) {
			System.out.println("Try again!!");
		}
		else {
			System.out.println("Deleted Sucessfully..");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int choice=0,tableNum=0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection(Url,uName,pass);
		
		School_17Table1.createTable1(connection);
		School_17Table2.createTable2(connection);
		
		while(choice!=5) {
			System.out.println("Enter your Choice");
			System.out.println("1.Insert Data");
			System.out.println("2.Delete Data");
			System.out.println("3.Update Data");
			System.out.println("4.Display Data");
			System.out.println("5.Exit");
			
			choice=sc.nextInt();
			
			switch(choice)
			{
				case 1:
					insertData(connection);
					displayData(connection);
					break;
				case 2:
					deleteData(connection);
					displayData(connection);
					break;
				case 3:
					updateData(connection);
					break;
				case 4:
					displayData(connection);
					break;
				case 5:
					System.out.println("Thanks for visiting...");
					return;
				default :
					System.out.println("Enter an valid option");
			}
		}
	}
}
