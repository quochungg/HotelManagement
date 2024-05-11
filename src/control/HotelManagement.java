/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Hotel;

/**
 *
 * @author nguye
 */
public class HotelManagement implements IHotel {

    Validation valid = new Validation();
    private static ArrayList<Hotel> list;

    public HotelManagement() {
        list = new ArrayList<>();
    }
    
    public void loadFromFile(){
        list = loadFile();
    }
    
    @Override
    public void addHotel() {
        int userChoice;
        do {
            System.out.println("\t Add New Hotel");
            String hotelId = "";
            do {
                hotelId = valid.getStringreg("Enter hotel ID: ", "^H\\d{2}$", "ID can not be empty!!", "ID must be in Hxx format!!");
                if (isDuplicate(hotelId)) {
                    System.out.println("ID can not be duplicate");
                }
            } while (isDuplicate(hotelId));
            String hotelName = valid.getString("Enter hotel name: ", "Hotel name can not be empty");
            int hotelRoom = valid.getInt("Enter available room: ", 5);
            String hotelAddress = valid.getString("Enter hotel address: ", "Hotel address can not be empty");
            String hotelPhone = valid.getStringreg("Enter phone number: ", "^0\\d{9}$", "Phone number can not be empty!", "Please enter right phone number!");
            int rating = valid.getInt("Enter rating: ", 0);
            Hotel newHotel = new Hotel(hotelId, hotelName, hotelRoom, hotelAddress, hotelPhone, rating);
            //ArrayList hotelList = loadFile(); // tra ve tat ca hotel dang ton tai trong file dat 
            list.add(newHotel);
            //saveFile(hotelList);
            userChoice = valid.getInt("Do you want to add new hotel? (Yes:1, No:0):", 0);
            if (userChoice == 0) {
                return;
            }
        } while (userChoice == 1);
    }

    @Override
    public void checkExist() {
        System.out.println("\tCheck Exist Hotel");
        String userHotelId = valid.getStringreg("Enter hotel ID to check: ", "^H\\d{2}$", "ID can not be empty!!", "ID must be in Hxx format!!");
        //ArrayList<Hotel> hotelList = loadFile(); 
        for (Hotel hotel : list) {
            if (hotel.getHotelId().equals(userHotelId)) {
                System.out.println("Hotel is existed!!!");
                return;
            }
        }
        System.out.println("Hotel does not exist!!!");
    }

    @Override
    public void updateHotel() {
        System.out.println("\tUpdate Hotel");
        String hotelID = valid.getStringreg("Enter hotel ID to update: ", "^H\\d{2}$", "ID can not be empty!!", "ID must be in Hxx format!!");
        //ArrayList<Hotel> hotelArray = loadFile();
        Hotel hotelUpdate = new Hotel();
        for (Hotel hotel : list) {
            if (hotel.getHotelId().equals(hotelID)) {
                hotelUpdate = hotel;
            }
        }
        
        String updateName = valid.getString("Enter new name (Current value: " + hotelUpdate.getHotelName() + "): ", "Name can not be empty!");
        if (updateName.isEmpty()) { //neu updatename no rong ""
            updateName = hotelUpdate.getHotelName();
        }
        hotelUpdate.setHotelName(updateName);
        
        int updateRoom = valid.getInt("Enter new room available (Current value: " + hotelUpdate.getHotelRoomAvailable() + "): ", 0);
        if (updateRoom == -1) {
            updateRoom = hotelUpdate.getHotelRoomAvailable();
        }
        hotelUpdate.setHotelRoomAvailable(updateRoom);
        
        String updateAdress = valid.getString("Enter new address (Current value: " + hotelUpdate.getHotelAddress() + "): ", "Address can not be empty!");
        if (updateAdress.isEmpty()) {
            updateAdress = hotelUpdate.getHotelAddress();
        }
        hotelUpdate.setHotelAddress(updateAdress);
        
        String updatePhone = valid.getString("Enter new phone (Current value: " + hotelUpdate.getHotelPhone() + "): ", "Phone can not be empty!");
        if (updatePhone.isEmpty()) {
            updatePhone = hotelUpdate.getHotelPhone();
        }
        hotelUpdate.setHotelPhone(updatePhone);
        
        int updateRating = valid.getInt("Enter new rating (Current value: " + hotelUpdate.getHotelRating() + "): ", 0);
        if (updateRating == -1) {
            updateRating = hotelUpdate.getHotelRating();
        }
        hotelUpdate.setHotelRating(updateRating);

    }

    @Override
    public void deleteHotel() {
        int userChoice;
        System.out.println("\tDelete Hotel");
        String userHotelId = valid.getStringreg("Enter hotel ID to delete: ", "^H\\d{2}$", "ID can not be empty!!", "ID must be in Hxx format!!");
        //ArrayList<Hotel> hotelList = loadFile(); //tra ve tat ca hotel dang ton tai trong file dat
        for (Hotel hotel : list) {
            if (hotel.getHotelId().equals(userHotelId)) {
                userChoice = valid.getInt("Do you want to delete the hotel? (Yes:1, No:0): ", 0);
                if (userChoice == 1) {
                    list.remove(hotel); 
                    System.out.println("Delete Successfull!!!");
                    return;
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public void searchHotelById() {
        System.out.println("\tSearch hotel by ID");
        String userHotelId = valid.getStringreg("Enter hotel ID to search: ", "^H\\d{2}$", "ID can not be empty!!", "ID must be in Hxx format!!");
        ArrayList<Hotel> searchList = new ArrayList<>();// tao ra de chua tat ca nhung hotel thoa man tim kiem cua nguoi dung
        for (Hotel hotel : list) {
            if (hotel.getHotelId().equals(userHotelId)) {
                searchList.add(hotel);
            }
        }
        if (searchList.isEmpty()) {
            System.out.println("Hotel does not exist!!!");
            return;
        }
        printHotels(searchList);
    }


    @Override
    public void searchHotelByName() {
        System.out.println("\tSearch hotel by name");
        String userHotelName = valid.getString("Enter hotel name to search: ", "Name can not be empty!!");
        ArrayList<Hotel> searchList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getHotelName().contains(userHotelName)) {
                searchList.add(hotel);
            }
        }
        if (searchList.isEmpty()) {
            System.out.println("Hotel does not exist!!!");
            return;
        }
        printHotels(searchList);
    }

    @Override
    public void displayHotelList() {
        printHotels(list);
    }

    @Override
    public boolean quitProgram() {
        int userChoice;
        saveFile(list);
        userChoice = valid.getInt("Do you want to exit the program (1:Yes, 0:No):", 0);
        return userChoice == 1;
    }

    //READ FILE
    public ArrayList loadFile() {
        ArrayList<Hotel> hotelList = new ArrayList();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("src/output/Hotel.dat"))) {
                String line;
                while ((line = br.readLine()) != null) { //lan chay 1: load H01: Sunset: 50: Quan 1: 0875271522: 4: 40 vao bien line
                    String part[] = line.split(":");
                    String hotelId = part[0].trim();
                    String hotelName = part[1].trim();
                    int hotelRoomAvailable = Integer.parseInt(part[2].trim());
                    String hotelAddress = part[3].trim();
                    String phone = part[4].trim();
                    int hotelRating = Integer.parseInt(part[5].trim());
                    Hotel hotel = new Hotel(hotelId, hotelName, hotelRoomAvailable, hotelAddress, phone, hotelRating);
                    hotelList.add(hotel);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return hotelList;
    }

    //WRITE FILE
    public void saveFile(ArrayList<Hotel> hotelList) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/output/Hotel.dat"))) {
                for (Hotel hotel : hotelList) {
                    bw.write(hotel.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //check xem co khach san nao bi trung ko 
    private boolean isDuplicate(String hotelId) {
        for (Hotel hotel : list) {
            if (hotel.getHotelId().equals(hotelId)) {
                return true;
            }
        }
        return false;
    }

    public void printHotels(ArrayList<Hotel> hotelList) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("#ID        #NAME               #ROOMS      #PHONE              #RATING            #ADDRESS             ");
        for (Hotel hotel : hotelList) {
            String idstr = String.format("%-10s", "ID#");
            String id = String.format("%-10s", hotel.getHotelId());
            String name = String.format("%-18s", hotel.getHotelName());
            String rooms = String.format("%-10s", hotel.getHotelRoomAvailable());
            String phone = String.format("%-20s", hotel.getHotelPhone());
            String rating = String.format("%-15s", hotel.getHotelRating());
            String address = String.format("%-20s", hotel.getHotelAddress());
            System.out.println("#" + id + "# " + name + "# " + rooms + "# " + phone + "# " + rating + "# " + address);
        }

        System.out.println("                                                                                               TOTAL: " + hotelList.size() + " hotel[s]#");

        System.out.println("");
        System.out.println("");
    }
}
