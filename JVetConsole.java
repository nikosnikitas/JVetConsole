package jvet;

/*      JVet a veterinary management system in Java.
 *      
 *          Author: Nikos-Nikitas
 *
 *  ****************************************************************
 *  *   Pets and their info are written to a file and accessed *
 *  *   when the user wants.                       *
 *  *                                      *
 *  ****************************************************************
 *  For more find me on GitHub: nikosnikitas
 * */

/*importing necessary classes from packages
for file, IO/FileNotFound exceptions, writing to file, scanner for input 
and Arrays to work with arrays, the Pet class to create pets and the GUI to make the interface.
*/
 
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;
import jvet.Pet;

public class JVetConsole {
    
    public static void add() {

        /*
        Values are added by the user here.
        Input is taken and stored making a new Pet object.
        */

        Pet userMade = new Pet("", "", 0, 'M');
        
        //add name to variable
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter your pet's name: ");
        userMade.name = s.nextLine();
        userMade.name.toUpperCase();
        
        System.out.println("Enter your pet's species: ");
        userMade.species = s.nextLine();
        userMade.species.toUpperCase();
        
        System.out.println("Enter your pet's age: ");
        userMade.age = s.nextInt();

        System.out.println("Enter your pet's gender (M / F): ");
        userMade.gender = s.next().charAt(0);
        
        //writing data to file
        //formatting to string
        
                                                                
        try { FileWriter fw = new FileWriter("data.csv",true); // true appends to the file instead of overwriting it.
    
            fw.write(String.format("\n%s,%s,%s,%s",
 userMade.name, userMade.species, userMade.age, userMade.gender));
            System.lineSeparator();
                
            fw.close();
            System.out.println("INFO: Wrote data successfully!");

        }catch (IOException e) {
            System.out.println("IO ERROR: Error writing to file.");
        }
        
        Scanner s1 = new Scanner(System.in);
        System.out.println("Add another Pet Entry? (Y / N)");
        char c = s1.next().charAt(0);
        if (c == 'N' || c == 'n')
            s1.close();
            menu(); //returns to main menu
        else
            s1.close();
            add();
    }
    
    public static void show() {
        
        //tries to open the file if it exists and reads the data. Throws error if the file is not found.

        try {
            File f = new File("data.csv");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String dataRead = s.nextLine();
                System.out.println(dataRead.replace(",", " | "));
            }
            s.close();
            returnToMenu();

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND: Error finding \"data.csv\" file."); 
            s.close();
            System.exit(0);
        }

    }

    public static void deleteAll(){
        File data = new File("data.csv");
        data.delete();
        try {
            File newData = new File("data.csv");
            newData.createNewFile(); 
            FileWriter fw = new FileWriter(newData);
            fw.write("Name,Species,Age,Gender\n");
            fw.close();
            System.out.println("Data deleted. Returning to Main Menu...");
            menu();
        }
        catch (IOException e) {
            System.out.println("IO ERROR: Could not create empty file.");
            fw.close();
            returnToMenu();
        }
    }
    //makes the main menu
    public static void menu() {
        
        //Making the main menu
        
        //'here' tracks if the user is in menu and loops.

        boolean here=true;
        
            /*giving the user options 'ops' to choose from.
             * Looping through each option in the array with 'i' 
             * and printing it to the screen. 
*/
            String[] ops = {"\nJVet Console", "============", "A Vet Data Management System\nAuthor: Nikos-Nikitas", 
                "For more visit: https://github.com/nikosnikitas","============", "Main Menu",
                "[1] View All Pet Entries.","[2] Add New Pet Entry.", "[3] Delete All Data.","[4] Exit."};
            
               for(int i=0; i < ops.length; i++) {

                System.out.println(ops[i]);
            }
        
            Scanner s = new Scanner(System.in); //scans for user input
            
            System.out.println("Your choice: ");
        while(here) {
                
            //user's choice is asked and read
                
            int c = s.nextInt();

            //if the user makes a valid choice the loop breaks

            switch(c){
                case 1:
                    s.close();
                    show(); //calls to view data
                    break;
                case 2:
                    s.close();
                    add(); //calls to add data
                    break;
                case 3:
                    s.close();
                    deleteAll();
                    break;
                case 4:
                    s.close();
                    System.exit(0); //exits the program
                    break;
                    }
                
            }
        
    }
    
    public static void returnToMenu() {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Press ENTER to return to Main Menu...");
        String k = s1.nextLine();
        if (k.isEmpty())
            s1.close();
            menu();
    }
    
    public static void main(String[] args) {
        
        Pet rufus = new Pet("RUFUS", "DOG", 3, 'M');
        
        Pet katia = new Pet("KATIA", "CAT", 5, 'F');

        Pet rico = new Pet("RICO", "PARROT", 10, 'M');        

    //we try to write the data of our pets to the file.
    
        try { 
            File data = new File("data.csv");
            if (!data.exists() && !data.isDirectory() ) {
                FileWriter fw = new FileWriter(data,false);
            
                fw.write("Name,Species,Age,Gender\n");
                
                fw.write(String.format("%s,%s,%s,%s\n", rufus.name, rufus.species, rufus.age, rufus.gender));
                System.lineSeparator();
                //rufus katia rico name species age
                fw.write(String.format("%s,%s,%s,%s\n", katia.name, katia.species, katia.age, katia.gender));
                System.lineSeparator();
                
                fw.write(String.format("%s,%s,%s,%s", 
rico.name, rico.species, rico.age, rico.gender));
                System.lineSeparator();
                
                fw.close();
                System.out.println("INFO: Wrote data successfully!");
            }

        }catch (IOException e) {
            System.out.println("IO ERROR: Error writing to file.");
        }
        
        menu();
        
        }
    }
