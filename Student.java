import java.sql.*;
import java.util.Scanner;


public class Student {
	Connection cn;
	Statement st;
	public Student() throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		String con = "jdbc:mysql://localhost:3306/Student_DB";
		String dbuser = "root";
		String dbpass = "root";
		Class.forName(driver);
		Connection cn = DriverManager.getConnection(con,dbuser,dbpass);
		Statement st = cn.createStatement();
		this.cn = cn;
		this.st = st;
		
      }
	    
	void dataInsert() {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		int id;
		String name, DOB, DOJ;
		System.out.println("Enter Student Number : \n");
		id = sc.nextInt();
		System.out.println("Enter Student Name : \n");
		name = sc1.nextLine();
		System.out.println("Enter Student DOB : \n");
        DOB = sc2.nextLine();
        System.out.println("Enter Student DOJ : \n");
        DOJ = sc3.nextLine();
        try {
        	PreparedStatement pst = cn.prepareStatement("Insert into Student values(?,?,?,?)");
        	pst.setInt(1, id);
        	pst.setString(2, name);
        	pst.setString(3, DOB);
        	pst.setString(4, DOJ);
        	pst.executeUpdate();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
      } 
	
	void dataRead() {
		try {
			ResultSet rs = st.executeQuery("select * from Student");
			System.out.println("S_NO.  S_NAME     S_DOB          S_DOJ");
			while(rs.next()) {
				System.out.println("|"+ rs.getInt(1)+"|    "+ rs.getString(2)+"  |"+ rs.getString(3)+"|   "+ rs.getString(4)+"|");
					
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	void dataUpdate() {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		int id;
		String name, DOB, DOJ;
		System.out.println("Enter Student Number for Updation : \n");
		id = sc.nextInt();
		System.out.println("Enter new Student Name : \n");
		name = sc1.nextLine();
		System.out.println("Enter new Student DOB : \n");
        DOB = sc2.nextLine();
        System.out.println("Enter new Student DOJ : \n");
        DOJ = sc3.nextLine();
        try {
        	PreparedStatement pst = cn.prepareStatement("update Student set Student_Name=?,Student_DOB=?,Student_DOJ=?"+"where Student_NO=?"); 	
        	pst.setString(1, name);
        	pst.setString(2, DOB);
        	pst.setString(3, DOJ);
            pst.setInt(4, id);
        	pst.executeUpdate();
        	System.out.println(id+"Data Update..");
        	
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	void datadelete() {
			Scanner sc = new Scanner(System.in);
			int id;
			System.out.println("Enter student number for delete : \n");
			id = sc.nextInt();
			try {
				PreparedStatement pst = cn.prepareStatement("delete from Student where Student_NO=?");
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println(id+"Deleted..");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			
	}
	void dataSingleRead() {
		try {
			Scanner sc = new Scanner(System.in);
			int id;
			System.out.println("Enter Student NO for read singlr data: \n");
			id = sc.nextInt();
			ResultSet rs = st.executeQuery("select * from Student where Student_NO="+id+"");
			System.out.println("S_NO.  S_NAME  S_DOB  S_DOJ");
			while(rs.next()) {
				System.out.println("|"+ rs.getInt(1)+"|"+ rs.getString(2)+"|"+ rs.getString(3)+"|"+ rs.getString(4));
				
			}
					
		} 
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args)throws Exception{
		Student obj = new Student();
		int ch;
		do {
			System.out.println("Enter Your Choice:\n 1.Insert\n 2.Read_all\n 3.Update\n 4.Delete\n 5.Read_single\n 6.Exit\n");
			Scanner sc = new Scanner (System.in);
			ch = sc.nextInt();
			switch(ch) {
			case 1: obj.dataInsert();
			break;
			case 2: obj.dataRead();
			break;
			case 3: obj.dataUpdate();
			break;
			case 4: obj.datadelete();
			break;
			case 5: obj.dataSingleRead();
			break;
			case 6: 
				System.out.println("Exit");
				break;
		    default:
		    	System.out.println("Invalid Choice!!");
				
			}
		}while(ch!=6);
			
	}
	
}
