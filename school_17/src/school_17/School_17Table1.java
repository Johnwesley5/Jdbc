package school_17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School_17Table1 {
	public static void createTable1 (Connection connection) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("CREATE TABLE IF NOT EXISTS StudentDetails (" +"RollNo INT, " +"Name VARCHAR(20), " +"Cls INT, " + "Section VARCHAR(1), " + "DOB VARCHAR(10))");
		
		ps.executeUpdate();
	}
}
