import java.math.BigInteger;
import java.util.Scanner;

public class FACTORIAL_CALCULATION_BY_RECURSION {
    public static void main(String[] args) throws Exception {
        FACTORIAL_CALCULATION_BY_RECURSION object = new FACTORIAL_CALCULATION_BY_RECURSION();
        Scanner scanner = new Scanner(System.in);
        long number;
        System.out.println("FACTORIAL CALCULATOR BY RECURSION :");
        System.out.println("-------------------------------");
        try {
            while (true) {
                System.out.println("ENTER NON-NEGATIVE NUMBER :");
                while (!scanner.hasNextInt()) {
                    System.out.println("ENTER NON-NEGATIVE NUMBER :");
                    scanner.next();
                }
                number = scanner.nextInt();

                while (number < 0) {
                    System.out.println("ENTER NON-NEGATIVE NUMBER :");
                    while (!scanner.hasNextInt()) {
                        System.out.println("ENTER NON-NEGATIVE NUMBER :");
                        scanner.next();
                    }
                    number = scanner.nextInt();
                }
                System.out.println("FACTORIAL FOR " + number + " IS = " + object.factorial(number));
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            // TODO: handle exception
            scanner.close();
        }

    }

    public BigInteger factorial(long number) {
        if (number == 0 || number == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(number).multiply(factorial(number - 1));
    }
}
