/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author nguye
 */
public interface IHotel {
    public void addHotel();
    public void checkExist();
    public void updateHotel();
    public void deleteHotel();
    public void searchHotelById();
    public void searchHotelByName();
    public void displayHotelList();
    public boolean quitProgram();
}
