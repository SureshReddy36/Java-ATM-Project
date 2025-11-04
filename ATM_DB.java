import java.sql.*;
import java.util.Scanner;

public class ATM_DB {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_db";
    private static final String USER = "root"; 
    private static final String PASS = "root123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("🏦 Welcome to Java ATM (DB Version)");
            System.out.print("Enter your Account Number: ");
            String accNo = sc.nextLine();

            if (!accountExists(conn, accNo)) {
                System.out.println("❌ Account not found!");
                return;
            }

            while (true) {
                System.out.println("\n--- ATM MENU ---");
                System.out.println("1️⃣ Deposit");
                System.out.println("2️⃣ Withdraw");
                System.out.println("3️⃣ Check Balance");
                System.out.println("4️⃣ Transfer Amount");
                System.out.println("5️⃣ Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double dep = sc.nextDouble();
                        updateBalance(conn, accNo, dep, true);
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double wd = sc.nextDouble();
                        updateBalance(conn, accNo, wd, false);
                        break;

                    case 3:
                        showBalance(conn, accNo);
                        break;

                    case 4:
                        sc.nextLine();
                        System.out.print("Enter receiver account number: ");
                        String receiver = sc.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double amt = sc.nextDouble();
                        transferAmount(conn, accNo, receiver, amt);
                        break;

                    case 5:
                        System.out.println("👋 Thank you for using Java ATM!");
                        System.exit(0);

                    default:
                        System.out.println("⚠️ Invalid option!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean accountExists(Connection conn, String accNo) throws SQLException {
        String q = "SELECT account_no FROM accounts WHERE account_no=?";
        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    private static void updateBalance(Connection conn, String accNo, double amount, boolean isDeposit) throws SQLException {
        double current = getBalance(conn, accNo);
        if (!isDeposit && amount > current) {
            System.out.println("❌ Insufficient balance!");
            return;
        }

        double newBalance = isDeposit ? current + amount : current - amount;
        String q = "UPDATE accounts SET balance=? WHERE account_no=?";
        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, accNo);
            ps.executeUpdate();
        }

        System.out.println(isDeposit ? "✅ Deposited Rs." + amount : "💸 Withdrawn Rs." + amount);
    }

    private static void showBalance(Connection conn, String accNo) throws SQLException {
        double bal = getBalance(conn, accNo);
        System.out.println("💰 Current Balance: Rs." + bal);
    }

    private static double getBalance(Connection conn, String accNo) throws SQLException {
        String q = "SELECT balance FROM accounts WHERE account_no=?";
        try (PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("balance");
        }
        return 0;
    }

    private static void transferAmount(Connection conn, String sender, String receiver, double amt) throws SQLException {
        if (!accountExists(conn, receiver)) {
            System.out.println("❌ Receiver not found!");
            return;
        }

        double senderBal = getBalance(conn, sender);
        if (amt > senderBal) {
            System.out.println("❌ Insufficient balance!");
            return;
        }

        conn.setAutoCommit(false);
        try {
            updateBalance(conn, sender, amt, false);
            updateBalance(conn, receiver, amt, true);
            conn.commit();
            System.out.println("📤 Rs." + amt + " transferred successfully to " + receiver);
        } catch (Exception e) {
            conn.rollback();
            System.out.println("⚠️ Transfer failed!");
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
