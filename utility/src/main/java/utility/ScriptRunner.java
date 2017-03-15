package utility;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ScriptRunner {

    public static void run(String jdbcUrl, String scriptPath) {

        Connection connection = null;
        Statement stmt;
        Scanner scanner = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl);
            stmt = connection.createStatement();

            scanner = new Scanner(new File(scriptPath));
            String content = scanner.useDelimiter("\\Z").next();

            String[] split = content.split(";;");

            for (String sql : split) {
                try {
                    stmt.execute(sql.trim());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
