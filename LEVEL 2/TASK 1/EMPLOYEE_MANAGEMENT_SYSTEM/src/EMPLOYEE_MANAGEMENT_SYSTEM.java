import java.util.ArrayList;
import java.util.Scanner;

public class EMPLOYEE_MANAGEMENT_SYSTEM {
    public int ID = 1;
    public ArrayList<Employee> employees = new ArrayList<>();
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
        int old = object.employees.size();
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
        object.employees.add(new Employee(object.ID, object.name, object.salary));
        object.ID++;

        if (old < object.employees.size()) {
            System.out.println("EMPLOYEE ADDED TO THE SYSTEM SUCCESFULLY");
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println("DETAILS :");
            System.out.println("-----------------------------------------");
            System.out.println(
                    "ID : " + object.employees.size() + " | NAME :" + object.name + " | SALARY : " + object.salary);
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

            for (int i = 0; i < object.employees.size(); i++) {
                System.out.print(i + 1 + "    |  ");
                System.out.print(object.employees.get(i).getName() + "   |  ");
                System.out.print(object.employees.get(i).getSalary());
                System.out.println();
            }
        } catch (Exception e) {
        }
        System.out.println();
        System.out.println(object.employees.size() + " RECORDS.");
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
            if ((id - 1) < 0 || (id - 1) >= object.employees.size()) {
                System.out.println("ID IS NOT EXIST");
                return;
            } else {

                old_name = object.employees.get(id - 1).getName();
                old_salary = object.employees.get(id - 1).getSalary();
                System.out.println("EMPLOYEE");
                System.out.println("-----------------------------------------");
                System.out.println();
                System.out.println("ID : | NAME : | SALARY : ");
                System.out.println();
                System.out.print(id + "    |  ");
                System.out.print(object.employees.get(id - 1).getName() + "   |  ");
                System.out.print(object.employees.get(id - 1).getSalary());
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
                    object.employees.get(id - 1).setName(object.name);
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

        object.employees.get(id - 1).setSalary(object.salary);
        if (!old_name.equals(object.employees.get(id - 1).getName())
                || old_salary != object.employees.get(id - 1).getSalary()) {
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
            if ((id - 1) < 0 || (id - 1) >= object.employees.size()) {
                System.out.println("ID IS NOT EXIST");
                return;
            } else {
                object.employees.remove(id - 1);
                System.out.println("EMPLOYEE DELETED IN THE SYSTEM SUCCESSFULLY");
                System.out.println("-----------------------------------------");
                System.out.println();
                return;
            }
        } catch (Exception e) {

        }

    }
}

class Employee {
    private int id;
    private String name;
    private int salary;

    // Constructor
    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}