package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class StoredProcedure {
    static final String DB_URL = "jdbc:mysql://localhost/doctorapp?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";
    private static PreparedStatement preparedStatement = null;

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    //public Scanner in;

    public StoredProcedure() {
        //in = new Scanner(System.in);
        try {
            // Open a connection and select the database named CS
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 1. create table Archive
    public static void createTable() {
        stmt = conn.createStatement();

        String queryDrop = "DROP TABLE IF EXISTS Archive";
        Statement stmtDrop = conn.createStatement();
        stmtDrop.execute(queryDrop);

        String createTableSQL = "CREATE TABLE Archive (" +
                " uID INT," +
                " updatedAt TIMESTAMP)");
        stmt.execute(createTableSQL);
        System.out.println("Table called Archive created successfully...");
    }

    // 2. create procedure haveNotModified
    public static void createProcedures()
    {
        String queryDrop = "DROP PROCEDURE IF EXISTS haveNotModified";
        Statement stmtDrop = conn.createStatement();
        stmtDrop.execute(queryDrop);

        String createInParameterProcedure = "DELIMITER // " +
                "CREATE PROCEDURE haveNotModified (IN cutoff TIMESTAMP) " +
                "BEGIN " +
                "INSERT INTO Archive " +
                "SELECT uID, updatedAt FROM PublicUsers " +
                "WHERE updatedAt < cutoff; " +
                "DELETE FROM PublicUsers p " +
                "WHERE p.uID IN ( " +
                "   SELECT a.uID " +
                "   FROM Archive a ) " +
                "END // " +
                "DELIMITER ;";
        stmt.executeUpdate(createInParameterProcedure);
    }

    // 3. call procedure haveNotModified with input: cutoff
    public static void callProcedures(String cutoff)
    {
        System.out.println("\nCalling the procedure haveNotModified");
        CallableStatement cs = conn.prepareCall("{CALL haveNotModified(?)}");
        cs.setString(1,cutoff);
        cs.executeQuery();

        // print out result from Archive
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Archive");
        System.out.println("Displaying record after executing stored procedure...");

        while(rs.next())
        {
            int id = rs.getInt("id");
            String updateAt = rs.getString("updatedAt");
            System.out.println("uID:" + id + " UpdatedAt:" + updateAt);
        }
    }
}