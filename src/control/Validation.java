package control;

import java.util.Scanner;

public class Validation {

    //Lay chuoi 
    public static String getString(String welcome, String msg) { //Enter new ....
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        //update
        if (welcome.contains("Enter new")) {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.equals("")) {
                return "";
            }
        } else {
            do {
                System.out.print(welcome);
                result = sc.nextLine();
                if (result.isEmpty()) {
                    System.out.println(msg);
                } else {
                    check = false;
                }
            } while (false);
        }
        return result;

    }

    //lay chuoi voi pattern cu the
    //H01
    //H02
    //Hxx va sdt
    public static String getStringreg(String welcome, String pattern, String msg, String msgreg) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!result.matches(pattern)) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    //lay int
    public static int getInt(String welcome, int min) {

        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        if (welcome.contains("Enter new")) {
            String result;
            result = sc.nextLine();
            if (result.equals("")) {
                return -1;
            } else {
                do {
                    try {
                        number = Integer.parseInt(result);
                        if (number < min) {
                            System.out.println("Number must be large than " + min);
                        } else {
                            check = false;
                        }
                    } catch (Exception e) {
                        System.out.println("Input number!!!");
                    }
                } while (check || number < min);
            }
        } else {
            do {
                try {
                    number = Integer.parseInt(sc.nextLine());
                    if (number < min) {
                        System.out.println("Number must be large than " + min);
                    } else {
                        check = false;
                    }
                } catch (Exception e) {
                    System.out.println("Input number!!!");
                }
            } while (check || number < min);
        }
        return number;
    }



}
