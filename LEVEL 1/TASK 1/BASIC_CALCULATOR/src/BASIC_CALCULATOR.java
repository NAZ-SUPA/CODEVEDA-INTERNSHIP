import java.util.Scanner;

public class BASIC_CALCULATOR {
    public static void main(String[] args) throws Exception {
        BASIC_CALCULATOR object = new BASIC_CALCULATOR();
        Scanner scanner = new Scanner(System.in);
        int first_number = 0;
        char operation = '.';
        int second_number = 0;
        try {
            System.out.println("ENTER FIRST NUMBER :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER FIRST NUMBER :");
                scanner.next();
            }
            first_number = scanner.nextInt();
            System.out.println("ENTER OPERATION : (+,-,*,/)");
            operation = scanner.next(".").charAt(0);
            while (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
                System.out.println("ENTER OPERATION : (+,-,*,/)");
                operation = scanner.next(".").charAt(0);
            }
            System.out.println("ENTER SECOND NUMBER :");
            while (!scanner.hasNextInt()) {
                System.out.println("ENTER SECOND NUMBER :");
                scanner.next();
            }
            second_number = scanner.nextInt();
            scanner.close();
            switch (operation) {
                case '+':
                    object.add(first_number, second_number);
                    break;
                case '-':
                    object.subtract(first_number, second_number);
                    break;
                case '*':
                    object.multiply(first_number, second_number);
                    break;
                case '/':
                    object.devide(first_number, second_number);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            object = new BASIC_CALCULATOR();
        }
    }

    public void add(int first_number, int second_number) {
        System.out.println("RESULT :");
        System.out.println(first_number + second_number);
    }

    public void subtract(int first_number, int second_number) {
        System.out.println("RESULT :");
        System.out.println(first_number - second_number);
    }

    public void multiply(int first_number, int second_number) {
        System.out.println("RESULT :");
        System.out.println(first_number * second_number);
    }

    public void devide(int first_number, int second_number) {
        System.out.println("RESULT :");
        if (second_number != 0 && first_number != 0) {
            System.out.println(first_number / second_number);
        } else if (first_number == 0 && second_number != 0) {
            System.out.println(0);
        } else if (first_number == 0) {
            System.out.println("ZERO DEVIDE BY ZERO --> UNDEFINED");
        } else {
            System.out.println("DEVISION BY ZERO --> INFINITY");
        }
    }
}
