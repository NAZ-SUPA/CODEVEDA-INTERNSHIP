import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LIBRARY_MANAGEMENT_SYSTEM {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Scanner scanner = new Scanner(System.in);
    public static String workspace = "";
    public static String function = "";
    public static Connection connection;

    public LIBRARY_MANAGEMENT_SYSTEM() throws SQLException {
        LIBRARY_MANAGEMENT_SYSTEM.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) throws Exception {

        try {
            new LIBRARY_MANAGEMENT_SYSTEM();
            intro();

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBook(Connection connection) {
        String title = "", author = "";

        System.out.println("TITLE (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            title = scanner.nextLine();
            while (!title.matches("^[a-zA-Z\\s]*$") || title.isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title = scanner.nextLine();
            }
            System.out.println("AUTHOR (LETTERS AND SPACE) :");

            author = scanner.nextLine();
            while (!author.matches("^[a-zA-Z\\s]*$") || author.isEmpty()) {
                System.out.println("ENTER A VALID AUTHOR (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID AUTHOR (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                author = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO Books (title,author) VALUES (?,?)";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, title);
            prepared_statement.setString(2, author);
            int affected_rows = prepared_statement.executeUpdate();
            if (affected_rows > 0)
                System.out.println("BOOK INSERTED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE BOOK FAILED TO BE ADDED.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteBook(Connection connection) {
        String title = "";

        System.out.println("TITLE (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            title = scanner.nextLine();
            while (!title.matches("^[a-zA-Z\\s]*$") || title.isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM Books WHERE LOWER(title) =?";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, title.toUpperCase());

            int row_affected = prepared_statement.executeUpdate();
            if (row_affected > 0)
                System.out.println("BOOK DELETED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE BOOK FAILED TO BE DELETED.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBook(Connection connection) {
        String title_new = "";
        String title_old = "";
        String author_new = "";

        System.out.println("TITLE OLD (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            title_old = scanner.nextLine();
            while (!title_old.matches("^[a-zA-Z\\s]*$") || title_old.isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title_old = scanner.nextLine();
            }
            System.out.println("TITLE NEW (LETTERS AND SPACE) :");

            title_new = scanner.nextLine();
            while (!title_new.matches("^[a-zA-Z\\s]*$") || title_new.isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title_new = scanner.nextLine();
            }
            System.out.println("AUTHOR NEW (LETTERS AND SPACE) :");

            author_new = scanner.nextLine();
            while (!author_new.matches("^[a-zA-Z\\s]*$") || author_new.isEmpty()) {
                System.out.println("ENTER A VALID AUTHOR (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID AUTHOR (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                author_new = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "UPDATE Books SET title = ?, author = ? WHERE book_id = (SELECT book_id FROM Books WHERE title = ?) ";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, title_new);
            prepared_statement.setString(2, author_new);
            prepared_statement.setString(3, title_old);
            int row_affected = prepared_statement.executeUpdate();
            if (row_affected > 0)
                System.out.println("BOOK UPDATED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE BOOK FAILED TO BE UPDATED.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectBook(Connection connection) {

        String title = "";

        System.out.println("TITLE (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            title = scanner.nextLine();
            while (!title.matches("^[a-zA-Z\\s]*$") || title.isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "SELECT title, author, is_borrowed FROM  Books WHERE title = ?";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, title);
            try (ResultSet result_set = prepared_statement.executeQuery()) {
                System.out.println("SEARCH RESULT :");
                boolean found = false;

                while (result_set.next()) {
                    found = true;

                    String book_title = result_set.getString("title");
                    String book_author = result_set.getString("author");
                    boolean is_borrowed = result_set.getBoolean("is_borrowed");
                    int book_id = result_set.getInt("book_id");

                    System.out.println("BOOK ID : " + book_id);
                    System.out.println("TITLE : " + book_title);
                    System.out.println("AUTHOR : " + book_author);
                    System.out.println("IS BORROWED : " + String.valueOf(is_borrowed).toUpperCase());
                }

                if (!found) {
                    System.out.println("NO BOOK FOUND WITH THIS TITLE : " + title);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Connection connection) {

        String name = "";

        System.out.println("NAME (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            while (!name.matches("^[a-zA-Z\\s]*$") || name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                name = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO Users (name) VALUES (?)";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, name);
            int row_affected = prepared_statement.executeUpdate();
            if (row_affected > 0)
                System.out.println("USER ADDED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE USER FAILED TO BE ADDED.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(Connection connection) {

        String name = "";
        int id = 0;

        System.out.println("NAME (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            while (!name.matches("^[a-zA-Z\\s]*$") || name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                name = scanner.nextLine();
            }

            System.out.println("ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            id = Integer.parseInt(scanner.nextLine().trim());
            if (id < 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                id = Integer.parseInt(scanner.nextLine().trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM Users WHERE name = ? AND user_id = ?";

        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, name);
            prepared_statement.setInt(2, id);
            int row_affected = prepared_statement.executeUpdate();
            if (row_affected > 0)
                System.out.println("USER DELETED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE USER FAILED TO BE DELETED.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(Connection connection) {
        int id = 0;
        String name = "";

        System.out.println("ID (NUMBERS JUST POSITIVE AND NON ZERO) :");

        try {
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            id = Integer.parseInt(scanner.nextLine().trim());
            if (id < 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                id = Integer.parseInt(scanner.nextLine().trim());
            }
            System.out.println("NAME (LETTERS AND SPACE) :");
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            while (!name.matches("^[a-zA-Z\\s]*$") || name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                name = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "UPDATE Users SET name = ? WHERE user_id = ?";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, name);
            prepared_statement.setInt(2, id);
            int row_affected = prepared_statement.executeUpdate();
            if (row_affected > 0)
                System.out.println("USER UPDATED SUCCESSFULLY.");
            else
                System.out.println("WARNING : THE USER FAILED TO BE UPDATED.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectUser(Connection connection) {
        String name = "";

        System.out.println("NAME (LETTERS AND SPACE) :");
        try {
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            while (!name.matches("^[a-zA-Z\\s]*$") || name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                name = scanner.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "SELECT user_id, name FROM Users WHERE lower(name) = ?";
        try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
            prepared_statement.setString(1, name);
            try (ResultSet result_set = prepared_statement.executeQuery()) {
                System.out.println("SEARCH RESULT : ");
                boolean found = false;

                while (result_set.next()) {
                    found = true;

                    int id = result_set.getInt("user_id");
                    String Name = result_set.getString("name");

                    System.out.println("ID : " + id);
                    System.out.println("NAME : " + Name);
                }

                if (!found) {
                    System.out.println("NO USER FOUND WITH THIS NAME : " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void intro() {

        System.out.println();
        System.out.println("WELCOME TO LIBRARY MANAGEMENT SYSTEM");
        System.out.println();

        System.out.println("CHOOSE A WORKSPACE (BOOKS,USERS,TRANSACTIONS) :");
        String function = scanner.nextLine().trim();
        while (!function.equalsIgnoreCase("BOOKS") && !function.equalsIgnoreCase("USERS")
                && !function.equalsIgnoreCase("TRANSACTIONS")) {
            System.out.println("CHOOSE A VALID WORKSPACE (BOOKS,USERS,TRANSACTIONS) :");
            function = scanner.nextLine().trim();
        }
        switch (function.toUpperCase()) {
            case "BOOKS":
                workspace = "books";
                function();
                break;
            case "USERS":
                workspace = "users";
                function();
                break;
            case "TRANSACTIONS":
                workspace = "transactions";
                function();
                break;
        }
    }

    public static void function() {

        System.out.println();
        System.out.println("WELCOME TO " + workspace.toUpperCase() + " WORKSPACE");
        System.out.println();

        System.out.println("CHOOSE A FUNCTION (ADD,UPDATE,DELETE,SEARCH) :");
        String function = scanner.nextLine().trim();
        while (!function.equalsIgnoreCase("ADD") && !function.equalsIgnoreCase("UPDATE")
                && !function.equalsIgnoreCase("DELETE") && !function.equalsIgnoreCase("SEARCH")) {
            System.out.println("CHOOSE A VALID FUNCTION (ADD,UPDATE,DELETE,SEARCH) :");
            function = scanner.nextLine().trim();
        }
        switch (function.toUpperCase()) {
            case "ADD":
                function = "ADD";
                break;
            case "UPDATE":
                function = "UPDATE";
                break;
            case "DELETE":
                function = "DELETE";
                break;
            case "SEARCH":
                function = "SEARCH";
                break;
        }
        caller();
        return;
    }

    public static void caller() {
        if (workspace.equals("books")) {
            switch (function) {
                case "ADD":
                    addBook(connection);
                    break;
                case "UPDATE":
                    updateBook(connection);
                    break;
                case "DELETE":
                    deleteBook(connection);
                    break;
                case "SEARCH":
                    selectBook(connection);
                    break;
            }
        } else if (workspace.equals("users")) {
            switch (function) {
                case "ADD":
                    addUser(connection);
                    break;
                case "UPDATE":
                    updateUser(connection);
                    break;
                case "DELETE":
                    deleteUser(connection);
                    break;
                case "SEARCH":
                    selectUser(connection);
                    break;
            }
        } else {
            switch (function) {
                case "ADD":
                    break;
                case "UPDATE":
                    break;
                case "DELETE":
                    break;
                case "SEARCH":
                    break;
            }
        }
        return;
    }

}
