package bankmanagementapp;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * AppClock — reads the simulated date from the app_settings table.
 *
 * To change the date during your presentation:
 *   1. Open phpMyAdmin
 *   2. Go to bank_management → app_settings table
 *   3. Edit the row where setting_key = 'simulated_date'
 *   4. Set setting_value to your target date e.g. 2026-07-19
 *   5. Leave it empty ("") to use the real today
 *
 * The app reads this fresh on every call, so no restart needed.
 */
public class AppClock {

    private static final String KEY = "simulated_date";

    /**
     * Returns the simulated date if one is set in the DB,
     * otherwise returns real today.
     */
    public static LocalDate today() {
        String stored = readFromDB();
        if (stored == null || stored.trim().isEmpty()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(stored.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("[AppClock] Invalid date in DB: '" + stored + "' — using real today.");
            return LocalDate.now();
        }
    }

    /** Returns today as a "yyyy-MM-dd" string. */
    public static String todayString() {
        return today().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a full timestamp string using the simulated date
     * but the real current time (HH:mm:ss).
     */
    public static String nowTimestamp() {
        LocalDate d = today();
        LocalDateTime now = LocalDateTime.now()
            .withYear(d.getYear())
            .withMonth(d.getMonthValue())
            .withDayOfMonth(d.getDayOfMonth());
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /** Returns true if a simulated date is active in the DB. */
    public static boolean isSimulating() {
        String stored = readFromDB();
        if (stored == null || stored.trim().isEmpty()) return false;
        try {
            LocalDate sim = LocalDate.parse(stored.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return !sim.equals(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // ── Private: reads setting_value from app_settings ───────────────────────
    private static String readFromDB() {
        try {
            Connection con = DBConnection1.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT setting_value FROM app_settings WHERE setting_key = ?");
            ps.setString(1, KEY);
            ResultSet rs = ps.executeQuery();
            String value = rs.next() ? rs.getString("setting_value") : null;
            con.close();
            return value;
        } catch (SQLException e) {
            System.out.println("[AppClock] DB read error: " + e.getMessage());
            return null;
        }
    }
}