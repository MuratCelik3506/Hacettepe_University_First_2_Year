

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Phone phone = new Phone();
        Passenger passenger = new Passenger();
        Seat seat = new Seat();
        Bus bus = new Bus();

        System.out.print("" +
                "1- Book a seat \n" +
                "2- Display all passengers with their seat numbers\n" +
                "3- Display all available seatIDs \n" +
                "4- Search\n" +
                "5- Exit\n" +
                "Enter your choose : ");
        Scanner in = new Scanner(System.in);
        String choose = in.nextLine();
        int key = Integer.parseInt(choose);
        if (key == 1) {
            System.out.print("Enter seat id: ");
            String seat_id = in.nextLine();
            System.out.print("Enter name: ");
            String p_name = in.nextLine();
            System.out.print("Enter surname: ");
            String p_surname = in.nextLine();
            System.out.print("Enter gender: ");
            String p_gender = in.nextLine();
            System.out.print("Enter country code: ");
            String p_count_code = in.nextLine();
            System.out.print("Enter code: ");
            String p_code = in.nextLine();
            System.out.print("Enter number: ");
            String p_number = in.nextLine();
            System.out.print("Enter type: ");
            String p_type = in.nextLine();
        }
        else if (key == 2) {
            ;

        }
        else if (key == 3) {
            ;
        }
        else if (key == 4) {
            ;
        }
        else if (key == 5) {
            ;
        }
        else {
            ;
        }





    }
}
