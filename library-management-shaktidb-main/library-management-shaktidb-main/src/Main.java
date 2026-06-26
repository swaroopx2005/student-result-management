import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/librarydb";
        String user = "postgres";
        String password = "1234";

        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // Create table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "id SERIAL PRIMARY KEY, " +
                    "title VARCHAR(100), " +
                    "author VARCHAR(100))");

            while (true) {
                System.out.println("\n===== Library Menu =====");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Delete Book");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                if (choice == 1) {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    String insert = "INSERT INTO books (title, author) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(insert);
                    ps.setString(1, title);
                    ps.setString(2, author);

                    ps.executeUpdate();
                    System.out.println("Book added!");

                } else if (choice == 2) {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM books");

                    System.out.println("\n📚 Book List:");
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author"));
                    }

                } else if (choice == 3) {
                    System.out.print("Enter book ID to delete: ");
                    int id = sc.nextInt();

                    String delete = "DELETE FROM books WHERE id = ?";
                    PreparedStatement ps = conn.prepareStatement(delete);
                    ps.setInt(1, id);

                    ps.executeUpdate();
                    System.out.println("Book deleted!");

                } else if (choice == 4) {
                    System.out.println("Exiting...");
                    break;

                } else {
                    System.out.println("Invalid choice!");
                }
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}