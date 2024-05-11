/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.HotelManagement;
import control.Validation;

/**
 *
 * @author nguye
 */
public class Main {
    public Validation valid = new Validation();
    HotelManagement hm = new HotelManagement();
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {       
        while (true) {
            System.out.println("1. Load file.");
            System.out.println("2. Add new hotel.");
            System.out.println("3. Check exist hotel.");
            System.out.println("4. Update hotel.");
            System.out.println("5. Delete hotel.");
            System.out.println("6. Search hotel by ID.");
            System.out.println("7. Displaying a hotel list.");
            System.out.println("8. Search hotel by name.");
            System.out.println("9. Exit program.");
            int userChoice;
            userChoice = valid.getInt("Enter Choice: ", 0);
            switch(userChoice){
                case 1:
                    hm.loadFromFile();
                    break;
                case 2:
                    hm.addHotel();
                    break;
                case 3:
                    hm.checkExist();
                    break;
                case 4:
                    hm.updateHotel();
                    break;
                case 5:
                    hm.deleteHotel();
                    break;
                case 6:
                    hm.searchHotelById();
                    break;
                case 7:
                    hm.displayHotelList();
                    break;
                case 8:
                    hm.searchHotelByName();
                    break;
                case 9:
                    if (hm.quitProgram()){
                        return;
                    }
                    break;
                default:
                    System.out.println("Not valid choice!");
                    
            }                    
        }
    }
}
