public class FACTORIAL_CALCULATION_BY_RECURSION {
    public static void main(String[] args) throws Exception {
        System.out.println(factorial(5));
    }

    static int factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
