package school_17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School_17Table2 {
	public static void createTable2(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS StudentMarks (" + "RollNo INT PRIMARY KEY, " +"Subject VARCHAR(10), " +"Marks INT)");
		ps.executeUpdate();
	}
}
