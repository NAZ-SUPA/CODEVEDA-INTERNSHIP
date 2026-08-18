import java.util.ArrayList;
import java.util.Scanner;

public class EMPLOYEE_MANAGEMENT_SYSTEM {
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Integer> salaries = new ArrayList<>();
    public String name = "";
    public int salary = 0;
    public Scanner scanner = new Scanner(System.in);
    public static EMPLOYEE_MANAGEMENT_SYSTEM object = new EMPLOYEE_MANAGEMENT_SYSTEM();

    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("WELCOME TO EMPLOYEE MANAGEMENT SYSTEM ");
        System.out.println("---------------------------------------");
        System.out.println();
        object.intro();
    }

    public void intro() {
        try {
            while (true) {
                System.out.println("CHOOSE A FUNCTION (ADD,VIEW,UPDATE,DELETE) :");
                String function = object.scanner.nextLine().trim();
                while (!function.equalsIgnoreCase("ADD") && !function.equalsIgnoreCase("VIEW")
                        && !function.equalsIgnoreCase("UPDATE") && !function.equalsIgnoreCase("DELETE")) {
                    System.out.println("CHOOSE A VALID FUNCTION (ADD,VIEW,UPDATE,DELETE) :");
                    function = object.scanner.nextLine().trim();
                }
                switch (function.toUpperCase()) {
                    case "ADD":
                        object.add();
                        break;
                    case "VIEW":
                        object.view();
                        break;
                    case "UPDATE":
                        object.update();
                        break;
                    case "DELETE":
                        object.delete();
                        break;
                }
            }

        } catch (Exception e) {
        }
    }

    public void add() {
        int old = object.names.size();
        int old2 = object.salaries.size();
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
            object.names.add(object.name);
            System.out.println("SALARY (500-30000) :");
            while (!object.scanner.hasNextInt()) {
                System.out.println("PLEASE ENTER A VALID SALARY (NNUMBER)(500-3000):");
                object.scanner.nextInt();
            }
            object.salary = object.scanner.nextInt();
            object.scanner.nextLine();
            while (object.salary < 500 || object.salary > 3000) {
                System.out.println("ENTER A VALID SALARY (NNUMBER)(500-3000):");
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER A VALID SALARY (NNUMBER)(500-3000):");
                    scanner.next();
                }
                object.salary = object.scanner.nextInt();
                object.scanner.nextLine();
            }
        } catch (Exception e) {
        }
        object.salaries.add(object.salary);
        if (old < object.names.size() && old2 < object.salaries.size()) {
            System.out.println("EMPLOYEE ADDED TO THE SYSTEM SUCCESFULLY");
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println("DETAILS :");
            System.out.println("-----------------------------------------");
            System.out.println("ID : " + names.size() + " | NAME :" + object.name + " | SALARY : " + object.salary);
            System.out.println();
        }
        return;
    }

    public void view() {
        System.out.println("EMPLOYEES");
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("ID : | NAME : | SALARY : ");
        System.out.println();
        try {

            for (int i = 0; i < object.names.size(); i++) {
                System.out.print(i + 1 + "    |  ");
                System.out.print(object.names.get(i) + "   |  ");
                System.out.print(object.salaries.get(i));
                System.out.println();
            }
        } catch (Exception e) {
        }
        System.out.println();
        System.out.println(object.names.size() + " RECORDS.");
        System.out.println("-----------------------------------------");
        System.out.println();
        return;
    }

    public void update() {
        System.out.println("ID :");
        int id = 0;
        String old_name = "";
        int old_salary = 0;
        try {
            while (!object.scanner.hasNextInt()) {
                System.out.println("ENTER A VALID ID (NUMBER):");
                object.scanner.nextInt();
            }
            id = Integer.parseInt(object.scanner.nextLine().trim());
            if ((id - 1) < 0 || (id - 1) >= object.names.size()) {
                System.out.println("ID IS NOT EXIST");
                return;
            } else {

                old_name = object.names.get(id - 1);
                old_salary = object.salaries.get(id - 1);
                System.out.println("EMPLOYEE");
                System.out.println("-----------------------------------------");
                System.out.println();
                System.out.println("ID : | NAME : | SALARY : ");
                System.out.println();
                System.out.print(id + "    |  ");
                System.out.print(object.names.get(id - 1) + "   |  ");
                System.out.print(object.salaries.get(id - 1));
                System.out.println();

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
                    object.names.set(id - 1, object.name);
                    System.out.println("SALARY (500-30000) :");
                    while (!object.scanner.hasNextInt()) {
                        System.out.println("PLEASE ENTER A VALID SALARY (NNUMBER)(500-3000):");
                        object.scanner.nextInt();
                    }
                    object.salary = object.scanner.nextInt();
                    object.scanner.nextLine();
                    while (object.salary < 500 || object.salary > 3000) {
                        System.out.println("ENTER A VALID SALARY (NNUMBER)(500-3000):");
                        while (!scanner.hasNextInt()) {
                            System.out.println("ENTER A VALID SALARY (NNUMBER)(500-3000):");
                            scanner.next();
                        }
                        object.salary = object.scanner.nextInt();
                        object.scanner.nextLine();
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {

        }

        object.salaries.set(id - 1, object.salary);
        if (!old_name.equals(object.names.get(id - 1)) || old_salary != object.salaries.get(id - 1)) {
            System.out.println("EMPLOYEE UPDATED IN THE SYSTEM SUCCESFULLY");
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println("DETAILS :");
            System.out.println("-----------------------------------------");
            System.out.println("ID : " + id + " | NAME :" + object.name + " | SALARY : " + object.salary);
            System.out.println();
        }
        return;
    }

    public void delete() {
        System.out.println("ID :");
        int id = 0;
        try {
            while (!object.scanner.hasNextInt()) {
                System.out.println("ENTER A VALID ID (NUMBER):");
                object.scanner.nextInt();
            }
            id = Integer.parseInt(object.scanner.nextLine().trim());
            if ((id - 1) < 0 || (id - 1) >= object.names.size()) {
                System.out.println("ID IS NOT EXIST");
                return;
            } else {
                object.names.remove(id - 1);
                object.salaries.remove(id - 1);
                System.out.println("EMPLOYEE DELETED IN THE SYSTEM SUCCESSFULLY");
                System.out.println("-----------------------------------------");
                System.out.println();
                return;
            }
        } catch (Exception e) {

        }

    }
}
