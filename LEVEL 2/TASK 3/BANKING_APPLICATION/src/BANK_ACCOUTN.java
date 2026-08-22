import java.util.ArrayList;
import java.util.Scanner;

public class BANK_ACCOUTN {
    public int id = 1;
    private int logged_in;
    public static BANK_ACCOUTN object = new BANK_ACCOUTN();
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<DATA> accounts = new ArrayList<>();
    public String name, password = "";
    public int amount = 0;
    public boolean logged = false;

    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("WELCOME TO BANKING APPLICATION");
        System.out.println("---------------------------------------");
        System.out.println();
        object.intro();

    }

    public void intro() {
        try {
            while (true) {
                if (!object.logged) {
                    System.out.println("CHOOSE A FUNCTION (CREATE,LOGIN) ACCOUNT :");
                    String function = object.scanner.nextLine().trim();
                    while (!function.equalsIgnoreCase("CREATE") && !function.equalsIgnoreCase("LOGIN")) {
                        System.out.println("CHOOSE A VALID FUNCTION (CREATE,LOGIN) ACCOUNT :");
                        function = object.scanner.nextLine().trim();
                    }
                    switch (function.toUpperCase()) {
                        case "CREATE":
                            object.create();
                            break;
                        case "LOGIN":
                            object.login();
                            break;
                    }
                } else {
                    System.out.println("CHOOSE A FUNCTION (DEPOSIT,WITHDRAW,CHECK) :");
                    String function = object.scanner.nextLine().trim();
                    while (!function.equalsIgnoreCase("DEPOSIT") && !function.equalsIgnoreCase("WITHDRAW")
                            && !function.equalsIgnoreCase("CHECK")) {
                        System.out.println("CHOOSE A VALID FUNCTION (DEPOSIT,WITHDRAW,CHECK) :");
                        function = object.scanner.nextLine().trim();
                    }
                    switch (function.toUpperCase()) {
                        case "DEPOSIT":
                            object.deposit();
                            break;
                        case "WITHDRAW":
                            object.withdraw();
                            break;
                        case "CHECK":
                            object.check_balanc();
                            break;
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    public void create() {
        int old = object.accounts.size();
        System.out.println("NAME (LETTERS AND SPACE) :");
        try {
            while (!object.scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                object.scanner.nextLine();
            }
            object.name = object.scanner.nextLine();
            while (!object.name.matches("^[a-zA-Z\\s]*$") || object.name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!object.scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    object.scanner.nextLine();
                }
                object.name = object.scanner.nextLine();
            }
            System.out.println("PASSWORD (LETTERS,NUMBERS,!#%?) :");
            while (!object.scanner.hasNextLine()) {
                System.out.println("ENTER A PASSWORD (LETTERS,NUMBERS,!#%?) :");
                object.scanner.nextLine();
            }
            object.password = object.scanner.nextLine();

            while (!object.password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!#%?])[a-zA-Z0-9!#%?]+$")
                    || object.password.isEmpty()) {
                System.out.println("ENTER A STRONG PASSWORD (LETTERS,NUMBERS,!#%?) :");
                while (!object.scanner.hasNextLine()) {
                    System.out.println("ENTER A PASSWORD (LETTERS,NUMBERS,!#%?) :");
                    object.scanner.nextLine();
                }
                object.password = object.scanner.nextLine();
            }
            object.accounts.add(new DATA(object.id - 1, object.name, object.password, object.amount));
            object.id++;
            if (old < object.accounts.size()) {
                System.out.println("ACCOUNT CREATED SUCCESFULLY");
                System.out.println("-----------------------------------------");
                System.out.println();
                System.out.println("DETAILS :");
                System.out.println("-----------------------------------------");
                System.out.println(
                        "ID : " + object.accounts.size() + " | NAME :" + object.name + " | AMOUNT : " + object.amount);
                System.out.println();
            }

        } catch (Exception e) {

        }
        return;

    }

    public void login() {
        System.out.println("NAME :");
        try {
            while (!object.scanner.hasNextLine()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                object.scanner.nextLine();
            }
            object.name = object.scanner.nextLine();
            while (!object.name.matches("^[a-zA-Z\\s]*$") || object.name.isEmpty()) {
                System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                while (!object.scanner.hasNextLine()) {
                    System.out.println("ENTER A VALID NAME (LETTERS AND SPACE):");
                    object.scanner.nextLine();
                }
                object.name = object.scanner.nextLine();
            }
            System.out.println("PASSWORD (LETTERS,NUMBERS,!#%?) :");
            while (!object.scanner.hasNextLine()) {
                System.out.println("ENTER A PASSWORD (LETTERS,NUMBERS,!#%?) :");
                object.scanner.nextLine();
            }
            object.password = object.scanner.nextLine();

            System.out.println("ID :");
            while (!object.scanner.hasNextInt()) {
                System.out.println("ENTER A VALID ID (NUMBERS) :");
                object.scanner.nextLine();
            }
            object.logged_in = Integer.parseInt(object.scanner.nextLine().trim());
            if (logged_in - 1 < 0 || logged_in - 1 >= object.accounts.size()) {
                System.out.println("NOT EXIST SUCH ID");
                return;
            }

            String bank_account_name = object.accounts.get(object.logged_in - 1).getName();
            String bank_account_password = object.accounts.get(object.logged_in - 1).getPassword();

            if (!bank_account_name.equals(object.name) || !bank_account_password.equals(object.password)) {
                System.out.println("WRONG NAME OR PASSWORD");
            } else {
                object.logged = true;
                System.out.println();
                System.out.println("LOGGED IN SUCCESFFULY");
                System.out.println("WELCOME " + object.name + " TO YOUR BANK ACCOUNT");
                System.out.println();
            }
            return;

        } catch (Exception e) {

        }

    }

    public void deposit() {
        System.out.println("AMOUNT (NUMBERS):");
        while (!object.scanner.hasNextInt()) {
            System.out.println("PLEASE ENTER A VALID AMOUNT (NNUMBER) :");
            object.scanner.nextLine();
        }
        object.amount = Integer.parseInt(object.scanner.nextLine());
        object.accounts.get(object.logged_in - 1)
                .setAmount(object.accounts.get(object.logged_in - 1).getAmount() + amount);
        System.out.println("AMOUNT ADDED SUCCESSFULY TO THE ACCOUNT");
        System.out.println("-----------------------------------------");
        System.out.println();
        return;
    }

    public void withdraw() {
        System.out.println("AMOUNT (NUMBERS):");
        while (!object.scanner.hasNextInt()) {
            System.out.println("PLEASE ENTER A VALID AMOUNT (NNUMBER) :");
            object.scanner.nextLine();
        }
        object.amount = Integer.parseInt(object.scanner.nextLine());
        int current_amount = object.accounts.get(logged_in - 1).getAmount();
        if (current_amount < amount) {
            System.out.println("THERE IS NO ENOUGH BALANCE FOR THIS TRANSACTION.");
        } else {
            object.accounts.get(object.logged_in - 1).setAmount(current_amount - amount);
            System.out.println("THE TRANSACTION SUCCEED.");
        }
        System.out.println("-----------------------------------------");
        System.out.println();
        return;
    }

    public void check_balanc() {
        System.out.println("ACCOUNT BALANCE : " + object.accounts.get(object.logged_in - 1).getAmount());
        System.out.println("-----------------------------------------");
        System.out.println();
        return;
    }
}

class DATA {
    private String name, password;
    private int id, amount;

    public DATA(int id, String name, String password, int amount) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAmount() {
        return amount;
    }

}