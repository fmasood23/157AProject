package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Trigger {

    static final String DB_URL = "jdbc:mysql://localhost/doctorapp?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";
    private static PreparedStatement preparedStatement = null;

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    // public Scanner in;

    public Trigger() {
        // in = new Scanner(System.in);
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void TriggerCheckDuplicateBeforeInsertReservation() {
        stmtDrop = conn.createStatement();
        stmtDrop.execute("DROP TRIGGER IF EXISTS Check_Duplicate_Before_Insert_Reservation");

        String triggerSQL = "delimiter // " +
                "CREATE TRIGGER Check_Duplicate_Before_Insert_Reservation " +
                "BEFORE INSERT ON Reservation " +
                "FOR EACH ROW " +
                "BEGIN " +
                " IF exists (select * from Reservation where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and dID = NEW.dID) " +
                " THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - this doctor is not available at this time'; " +
                " END IF; " +
                "END; " +
                "// " +
                "delimiter ; ";
        stmt.execute(triggerSQL);
        System.out.println("Trigger Check_Duplicate_Before_Insert_Reservation created successfully...");
    }

    public static void TriggerCheckDuplicateBeforeUpdateReservation() {
        stmtDrop = conn.createStatement();
        stmtDrop.execute("DROP TRIGGER IF EXISTS Check_Duplicate_Before_Update_Reservation");

        String triggerSQL = "delimiter // " +
                "CREATE TRIGGER Check_Duplicate_Before_Update_Reservation " +
                "BEFORE UPDATE ON Reservation " +
                "FOR EACH ROW " +
                "BEGIN " +
                " IF exists (select * from Reservation where appointmentDate = NEW.appointmentDate and appointmentTime = NEW.appointmentTime and dID = NEW.dID) " +
                " THEN signal sqlstate '45000' set message_text = 'TriggerError: Duplicate appointments - this doctor is not available at this time'; " +
                " END IF; " +
                "END; " +
                "// " +
                "delimiter ; ";
        stmt.execute(triggerSQL);
        System.out.println("Trigger Check_Duplicate_Before_Update_Reservation created successfully...");
    }

    public static void TriggerValidStarInsertReviews() {
        stmtDrop = conn.createStatement();
        stmtDrop.execute("DROP TRIGGER IF EXISTS Valid_Star_Insert_Reviews");

        String triggerSQL = "delimiter // " +
                "CREATE TRIGGER Valid_Star_Insert_Reviews " +
                "BEFORE INSERT ON Reviews " +
                "FOR EACH ROW " +
                "BEGIN " +
                "IF NEW.stars>5 or NEW.stars<1 " +
                "  THEN signal sqlstate '45000' set message_text = 'TriggerError: Number of stars cannot be less than 1 or greater than 5'; " +
                "END IF; " +
                "END; " +
                "// " +
                "delimiter ;";
        stmt.execute(triggerSQL);
        System.out.println("Trigger Valid_Star_Insert_Reviews created successfully...");
    }

    public static void TriggerValidStarUpdateReviews() {
        stmtDrop = conn.createStatement();
        stmtDrop.execute("DROP TRIGGER IF EXISTS Valid_Star_Update_Reviews");

        String triggerSQL = "delimiter // " +
                "CREATE TRIGGER Valid_Star_Update_Reviews " +
                "BEFORE UPDATE ON Reviews " +
                "FOR EACH ROW " +
                "BEGIN " +
                "IF NEW.stars>5 or NEW.stars<1 " +
                "  THEN signal sqlstate '45000' set message_text = 'TriggerError: Number of stars cannot be less than 1 or greater than 5'; " +
                "END IF; " +
                "END; " +
                "// " +
                "delimiter ; ";
        stmt.execute(triggerSQL);
        System.out.println("Trigger Valid_Star_Update_Reviews created successfully...");
    }
}