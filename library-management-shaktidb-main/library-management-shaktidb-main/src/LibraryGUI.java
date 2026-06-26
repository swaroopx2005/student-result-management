import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryGUI {
    static Connection conn;

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/librarydb";
        String user = "postgres";
        String password = "1234";

        try {
            conn = DriverManager.getConnection(url, user, password);

            // Create table if not exists
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "id SERIAL PRIMARY KEY, " +
                    "title VARCHAR(100), " +
                    "author VARCHAR(100))");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create GUI window
        JFrame frame = new JFrame("Library Management");
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);

        JLabel idLabel = new JLabel("Book ID:");
        JTextField idField = new JTextField(10);
        JButton deleteButton = new JButton("Delete Book");

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton updateButton = new JButton("Update Book");
        JTextArea output = new JTextArea(10, 30);
        JScrollPane scroll = new JScrollPane(output);

        // Add Book button action
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();

                String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, author);
                ps.executeUpdate();

                output.setText("Book added successfully!\n");

                titleField.setText("");
                authorField.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                String sql = "DELETE FROM books WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    output.setText("Book deleted successfully!\n");
                } else {
                    output.setText("Book not found!\n");
                }

                idField.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();

                String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setInt(3, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    output.setText("Book updated successfully!\n");
                } else {
                    output.setText("Book not found!\n");
                }

                idField.setText("");
                titleField.setText("");
                authorField.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // View Books button action
        viewButton.addActionListener(e -> {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM books");

                output.setText("📚 Book List:\n");
                while (rs.next()) {
                    output.append(rs.getInt("id") + " | " +
                            rs.getString("title") + " | " +
                            rs.getString("author") + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add components to frame
        frame.add(titleLabel);
        frame.add(titleField);
        frame.add(authorLabel);
        frame.add(authorField);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(scroll);
        frame.add(idLabel);
        frame.add(idField);
        frame.add(deleteButton);
        frame.add(updateButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}