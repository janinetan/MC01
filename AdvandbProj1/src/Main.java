import java.sql.SQLException;


public class Main {
	
	public static void main(String[] args) {
		try {
			new Controller();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
	}
}
