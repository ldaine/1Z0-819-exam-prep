import java.sql.Connection;
import java.sql.DriverManager;

public class DatabasesWithJDBCApp {
    public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");
       String url = "";
       Connection conn = DriverManager.getConnection(url);
    }
}
