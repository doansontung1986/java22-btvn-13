package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtility {

    public static int inputIntegerNumber() throws InputMismatchException {
        return new Scanner(System.in).nextInt();
    }

    public static double inputDoubleNumber() throws InputMismatchException {
        return new Scanner(System.in).nextDouble();
    }

    public static String inputString() throws NullPointerException {
        return new Scanner(System.in).nextLine();
    }

}
