import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            while (!title.matches("^[a-zA-Z\\s]*$") || title.trim().isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title = scanner.nextLine();
            }
            System.out.println("AUTHOR (LETTERS AND SPACE) :");

            author = scanner.nextLine();
            while (!author.matches("^[a-zA-Z\\s]*$") || author.trim().isEmpty()) {
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
            while (!title.matches("^[a-zA-Z\\s]*$") || title.trim().isEmpty()) {
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
            prepared_statement.setString(1, title.toLowerCase());

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
            while (!title_old.matches("^[a-zA-Z\\s]*$") || title_old.trim().isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title_old = scanner.nextLine();
            }
            System.out.println("TITLE NEW (LETTERS AND SPACE) :");

            title_new = scanner.nextLine();
            while (!title_new.matches("^[a-zA-Z\\s]*$") || title_new.trim().isEmpty()) {
                System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                while (!scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID TITLE (LETTERS AND SPACE):");
                    scanner.nextLine();
                }
                title_new = scanner.nextLine();
            }
            System.out.println("AUTHOR NEW (LETTERS AND SPACE) :");

            author_new = scanner.nextLine();
            while (!author_new.matches("^[a-zA-Z\\s]*$") || author_new.trim().isEmpty()) {
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

        String sql = "UPDATE Books SET title = ?, author = ? WHERE title = ? ";
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
            while (!title.matches("^[a-zA-Z\\s]*$") || title.trim().isEmpty()) {
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

        String sql = "SELECT title, author, is_borrowed, book_id FROM Books WHERE title = ?";
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
            while (!name.matches("^[a-zA-Z\\s]*$") || name.trim().isEmpty()) {
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
            while (!name.matches("^[a-zA-Z\\s]*$") || name.trim().isEmpty()) {
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
            id = scanner.nextInt();
            scanner.nextLine(); // Clear newline
            if (id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
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
            id = scanner.nextInt();
            scanner.nextLine(); // Clear newline
            if (id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("NAME (LETTERS AND SPACE) :");
            while (!scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE) :");
                scanner.nextLine();
            }
            name = scanner.nextLine();
            while (!name.matches("^[a-zA-Z\\s]*$") || name.trim().isEmpty()) {
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
            while (!name.matches("^[a-zA-Z\\s]*$") || name.trim().isEmpty()) {
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
            prepared_statement.setString(1, name.toLowerCase());
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
        function = scanner.nextLine().trim();
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

        // FIX: Removed the word 'String' here so it updates the global variable instead
        // of creating a local one
        function = scanner.nextLine().trim();

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
                    addTransation(connection);
                    break;
                case "UPDATE":
                    updateTransaction(connection);
                    break;
                case "DELETE":
                    deleteTransaction(connection);
                    break;
                case "SEARCH":
                    searchTransaction(connection);
                    break;
            }
        }
        return;
    }

    public static void addTransation(Connection connection) {
        int user_id = 0;
        int book_id = 0;
        String sql;
        boolean is_borrowed = false;

        try {
            System.out.println("USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            user_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (user_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                user_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            book_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (book_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                book_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("CHOOSE A FUNCTION (BORROW,RETURN) :");
            String actionFunction = scanner.nextLine().trim(); // Renamed to avoid shadowing
            while (!actionFunction.equalsIgnoreCase("BORROW") && !actionFunction.equalsIgnoreCase("RETURN")) {
                System.out.println("CHOOSE A VALID FUNCTION (BORROW,RETURN) :");
                actionFunction = scanner.nextLine().trim();
            }

            sql = "SELECT user_id FROM Users WHERE user_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, user_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    boolean found = false;
                    if (result_set.next()) {
                        found = true;
                    }
                    if (!found) {
                        System.out.println("NO USER FOUND WITH USER ID : " + user_id);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "SELECT is_borrowed FROM Books WHERE book_id = ? ";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, book_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    boolean found = false;
                    if (result_set.next()) {
                        found = true;
                        is_borrowed = result_set.getBoolean("is_borrowed");
                    }
                    if (!found) {
                        System.out.println("NO BOOK FOUND WITH BOOK ID : " + book_id);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (actionFunction.toUpperCase().equals("BORROW")) {
                if (is_borrowed) {
                    System.out.println("SORRY THIS BOOK IS ALREADY BORROWED.");
                    return;
                } else {
                    sql = "INSERT INTO Transactions (book_id,user_id,action_type) VALUES(?,?,?)";
                    try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                        preapared_statment.setInt(1, book_id);
                        preapared_statment.setInt(2, user_id);
                        preapared_statment.setString(3, "BORROW");
                        int row_affected = preapared_statment.executeUpdate();
                        if (row_affected > 0) {
                            System.out.println("THE BOOK BORROWED SUCCESSFULLY.");
                        } else {
                            System.out.println("WARNING : THE TRANSACTION (BORROW) FAILED TO BE ADDED.");
                            return;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    sql = "UPDATE Books SET is_borrowed = 1 WHERE book_id = ?";
                    try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                        preapared_statment.setInt(1, book_id);
                        int row_affected = preapared_statment.executeUpdate();
                        if (row_affected > 0) {
                            System.out.println("THE BOOK IS NOW MARKED AS BORROWED.");
                        } else {
                            System.out.println("WARNING : THE PROCESS OF MAKING THE BOOK BORROWED FAILED.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                if (!is_borrowed) {
                    System.out.println("THIS BOOK IS NOT BORROWED.");
                } else {
                    sql = "SELECT transaction_id FROM Transactions WHERE user_id = ? AND book_id =? AND return_date IS NULL";
                    try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                        preapared_statment.setInt(1, user_id);
                        preapared_statment.setInt(2, book_id);
                        try (ResultSet reasult_set = preapared_statment.executeQuery()) {
                            if (!reasult_set.next()) {
                                System.out.println(
                                        "THIS BOOK IS NOT CURRENTLY BORROWED BY THIS USER OR NOT BORROWED AT ALL.");
                            } else {
                                sql = "UPDATE Transactions SET return_date = ? WHERE book_id =? AND user_id = ? AND return_date IS NULL";
                                try (PreparedStatement preapared_statment_ = connection.prepareStatement(sql)) {
                                    preapared_statment_.setDate(1, Date.valueOf(LocalDate.now()));
                                    preapared_statment_.setInt(2, book_id);
                                    preapared_statment_.setInt(3, user_id);
                                    int row_affected = preapared_statment_.executeUpdate();
                                    if (row_affected > 0) {
                                        System.out.println("THE BOOK RETURNED SUCCESSFULLY");
                                    } else {
                                        System.out.println("WARNING : THE TRANSACTION (RETURN) FAILED TO BE ADDED.");
                                    }

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                sql = "UPDATE Books SET is_borrowed = 0 WHERE book_id = ?";
                                try (PreparedStatement preapared_statment_ = connection.prepareStatement(sql)) {
                                    preapared_statment_.setInt(1, book_id);
                                    int row_affected = preapared_statment_.executeUpdate();
                                    if (row_affected > 0) {
                                        System.out.println("THE BOOK NOW IS RETURNED AND AVAILABLE.");
                                    } else {
                                        System.out
                                                .println("WARNING : THE PROCESS OF MAKING THE BOOK AVAILABLE FAILED.");
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchTransaction(Connection connection) {
        int user_id = 0;
        int book_id = 0;
        String sql;
        try {
            System.out.println("USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            user_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (user_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                user_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            book_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (book_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                book_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            sql = "SELECT user_id FROM Users WHERE user_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, user_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    boolean found = false;
                    if (result_set.next()) {
                        found = true;
                    }
                    if (!found) {
                        System.out.println("NO USER FOUND WITH USER ID : " + user_id);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "SELECT book_id FROM Books WHERE book_id = ? ";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, book_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    if (!result_set.next()) {
                        System.out.println("NO BOOK FOUND WITH BOOK ID : " + book_id);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "SELECT transaction_id,action_type,borrow_date,return_date FROM Transactions WHERE user_id = ? AND book_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, user_id);
                preapared_statment.setInt(2, book_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    boolean found = false;

                    System.out.println();
                    System.out.println("SEARCH RESULT : ");
                    System.out.println();

                    while (result_set.next()) {
                        found = true;

                        int transaction_id = result_set.getInt("transaction_id");
                        String action_type = result_set.getString("action_type");
                        Date borrow_date = result_set.getDate("borrow_date");
                        String return_date_string = result_set.getString("return_date");
                        String display_return_date = (return_date_string == null) ? "STILL NOT RETURNED"
                                : return_date_string;

                        System.out.println("TRANSACTION ID : " + transaction_id);
                        System.out.println("BOOK ID : " + book_id);
                        System.out.println("USER ID : " + user_id);
                        System.out.println("ACTION TYPE : " + action_type);
                        System.out.println("BORROW DATE : " + borrow_date);
                        System.out.println("RETURN DATE : " + display_return_date);

                        System.out.println();
                    }
                    if (!found) {
                        System.out.println("THERE IS NO TRANSACTION FOR THIS USER AND THIS BOOK.");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteTransaction(Connection connection) {
        int transaction_id = 0;
        String sql;
        try {
            System.out.println("TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            transaction_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (transaction_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                transaction_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            sql = "SELECT book_id,return_date FROM Transactions WHERE transaction_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, transaction_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    if (result_set.next()) {
                        Date return_date = result_set.getDate("return_date");
                        int book_id = result_set.getInt("book_id");

                        // FIX: If the transaction is active, set the book back to available before
                        // deleting
                        if (return_date == null) {
                            sql = "UPDATE Books SET is_borrowed = 0 WHERE book_id = ?";
                            try (PreparedStatement preapared_statment_ = connection.prepareStatement(sql)) {
                                preapared_statment_.setInt(1, book_id);
                                int row_affected = preapared_statment_.executeUpdate();
                                if (row_affected > 0) {
                                    System.out.println("NOW THE BOOK IS AVAILABLE TO BE BORROWED.");
                                } else {
                                    System.out.println("WARNING : THE BOOK FAILED TO BE UPDATED TO AVAILABLE.");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("NO TRANSACTION FOUND WITH THAT ID.");
                        return; // Exit if transaction doesn't exist
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "DELETE FROM Transactions WHERE transaction_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, transaction_id);
                int row_affected = preapared_statment.executeUpdate();
                if (row_affected > 0) {
                    System.out.println("THE TRANSACTIONS DELETED SUCCESSFULLY.");
                } else {
                    System.out.println("WARNING : TRANSACTION FAILED TO BE DELETED.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTransaction(Connection connection) {
        int user_id_new = 0;
        int book_id_new = 0;
        int transaction_id = 0;
        int book_id_old = 0;
        String sql;
        boolean is_borrowed;
        Date return_date;

        try {
            System.out.println("TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            transaction_id = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (transaction_id <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID TRANSACTION ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                transaction_id = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("NEW USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID NEW USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            user_id_new = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (user_id_new <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID NEW USER ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                user_id_new = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            System.out.println("NEW BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER A VALID NEW BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                scanner.nextLine();
            }
            book_id_new = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            if (book_id_new <= 0) {
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID NEW BOOK ID (NUMBERS JUST POSITIVE AND NON ZERO) :");
                    scanner.nextLine();
                }
                book_id_new = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            }

            sql = "SELECT book_id ,return_date FROM Transactions WHERE transaction_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, transaction_id);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    if (result_set.next()) {
                        book_id_old = result_set.getInt("book_id");
                        return_date = result_set.getDate("return_date");

                        if (return_date != null) {
                            System.out.println(
                                    " ERROR : THIS TRANSACTION IS CLOSED (THE BOOK WAS ALREADY RETURNED). HISTORICAL RECORDS CANNOT BE MODIFIED.");
                            return;
                        }
                    } else {
                        System.out.println("THERE IS NO TRANSACTION FOR THIS ID.");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            sql = "SELECT user_id FROM Users WHERE user_id = ?";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, user_id_new);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    boolean found = false;
                    if (result_set.next()) {
                        found = true;
                    }
                    if (!found) {
                        System.out.println("NO USER FOUND WITH USER ID : " + user_id_new);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "SELECT is_borrowed FROM Books WHERE book_id = ? ";
            try (PreparedStatement preapared_statment = connection.prepareStatement(sql)) {
                preapared_statment.setInt(1, book_id_new);
                try (ResultSet result_set = preapared_statment.executeQuery()) {
                    if (result_set.next()) {
                        is_borrowed = result_set.getBoolean("is_borrowed");

                        if (is_borrowed && book_id_new != book_id_old) {
                            System.out.println("SORRY THIS NEW BOOK IS ALREADY BORROWED BY ANOTHER USER.");
                            return;
                        }
                    } else {
                        System.out.println("NO BOOK FOUND WITH BOOK ID : " + book_id_new);
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "UPDATE Transactions SET book_id = ? , user_id = ? WHERE transaction_id = ?";
            try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
                prepared_statement.setInt(1, book_id_new);
                prepared_statement.setInt(2, user_id_new);
                prepared_statement.setInt(3, transaction_id);
                int row_affected = prepared_statement.executeUpdate();
                if (row_affected > 0) {
                    System.out.println("OLD BOOK UPDATED TO NEW BOOK.");
                    System.out.println("OLD USER UPDATED TO NEW USER.");
                } else {
                    System.out.println("WARNING : THE TRANSACTION FAILED TO BE UPDATED.");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (book_id_new != book_id_old) {
                sql = "UPDATE Books SET is_borrowed = ? WHERE book_id = ?";
                try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
                    prepared_statement.setBoolean(1, false);
                    prepared_statement.setInt(2, book_id_old);
                    int row_affected = prepared_statement.executeUpdate();
                    if (row_affected > 0)
                        System.out.println("THE OLD BOOK NOW IS AVAILABLE.");
                    else {
                        System.out.println(
                                "WARNING : THE OLD BOOK FAILED TO BE UPDATED AND ADDED TO THE CURRENT TRANSACTION.");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                sql = "UPDATE Books SET is_borrowed = ? WHERE book_id = ?";
                try (PreparedStatement prepared_statement = connection.prepareStatement(sql)) {
                    prepared_statement.setBoolean(1, true);
                    prepared_statement.setInt(2, book_id_new);
                    int row_affected = prepared_statement.executeUpdate();
                    if (row_affected > 0)
                        System.out.println("THE NEW BOOK NOW IS BORROWED.");
                    else {
                        System.out.println("WARNING : THE NEW BOOK FAILED TO BE UPDATED TO BORROWED.");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}