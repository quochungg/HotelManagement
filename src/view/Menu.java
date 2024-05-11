
package view;

import control.Validation;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Menu {
    private final String title;
    private final ArrayList<String> optionList = new ArrayList();
    Validation valid = new Validation();
    
    public Menu(String title) {
        this.title = title;
    }
      
    public void addOption(String option){
        optionList.add(option);
    }
    
    public void printMenu(){
        if (optionList.isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        System.out.println("\t"+this.title+"\n");
        for (String string : optionList) {
            System.out.println(string);
        }
    }
    
    public int getChoice() {
        int max = optionList.size();
        return valid.getInt(("Choose [1..." + max + "]: "), 0);
    }
    
}
