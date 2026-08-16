import java.util.Scanner;

public class NUMBER_GUESSING_GAME {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int number = (int) Math.round(Math.random() * 10);
        int attemp = 0;
        int answer = -1;

        System.out.println("NUMBER GUESSING GAME");
        System.out.println("ENTER A VALID NUMBER BETWEEN (0-10) :");
        System.out.println("-------------------------------");

        try {
            while (true) {
                while (answer != number && attemp != 5) {

                    while (!scanner.hasNextInt()) {
                        System.out.println("ENTER A VALID NUMBER BETWEEN (0-10) :");
                        scanner.next();
                    }
                    answer = scanner.nextInt();

                    while (answer < 0 || answer > 10) {
                        System.out.println("ENTER A VALID NUMBER BETWEEN (0-10) :");
                        while (!scanner.hasNextInt()) {
                            System.out.println("ENTER A VALID NUMBER BETWEEN (0-10) :");
                            scanner.next();
                        }
                        answer = scanner.nextInt();
                    }

                    if (answer != number) {
                        if (answer < number) {
                            System.out.println("TOO LOW");
                        } else if (answer > number) {
                            System.out.println("TOO HIGH");
                        }
                        attemp++;
                    }
                }

                if (answer == number) {
                    System.out.println("-------------------------------");
                    System.out.println("YOU WON");
                    System.out.println("CORRECT ANSWER : " + number);
                } else {
                    System.out.println("-------------------------------");
                    System.out.println("YOU FAILED");
                    System.out.println("CORRECT ANSWER : " + number);
                }

                attemp = 0;
                answer = -1;
                System.out.println();
                System.out.println("-------------------------------");
                System.out.println();
                System.out.println("NEW ROUND");
                System.out.println("ENTER A VALID NUMBER BETWEEN (0-10) :");
                System.out.println("-------------------------------");
                number = (int) Math.round(Math.random() * 10);
            }
        } catch (Exception e) {
            scanner.close();
        }
    }
}