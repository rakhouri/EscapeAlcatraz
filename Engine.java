/*
Richard Khouri
CPSC 1060: RPG
5/04/2023
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;

// Import Exceptions, FileOutputStream, and PrintWriter
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Engine {
    public static void main(String[] args) {
        // Create scanner object
        Scanner scnr = new Scanner(System.in);

        // Creates an arraylist of items that serves as the players inventory
        ArrayList<Item> inventory = new ArrayList<Item>();
        
        // Creates a GameMap object named map
        GameMap map = new GameMap();

        // Creates different room objects with proper exits and items
        Room sallyPort = new Room("Sally Port", "An armored and controlled entryway to the fortress of Alcatraz beyond. All hope abandon ye who enter in.");
        sallyPort.addExit("Armory");
        sallyPort.addExit("Visiting Room");
        sallyPort.addExit("Cell Block A");
        map.addRoom(sallyPort);
    
        Room armory = new Room("Armory", "A place where U.S. soldiers would keep their weapons and store their ammunition. Now it is just an abandoned storage room.");
        Item brokenVacuumMotor = new Item("Motor of a broken vacuum", "Still seems like it can be used.");
        brokenVacuumMotor.addAction("craft");
        armory.addExit("Sally Port");
        armory.addItem(brokenVacuumMotor);
        map.addRoom(armory);

        Room visitingRoom = new Room("Visiting Room", "A place where loved one's can visit. I doubt anyone is here for me.");
        Item letter = new Item("Letter", "A letter to a prisoner from a loved one. I wonder if they will ever get to see them again.");
        letter.addAction("read");
        visitingRoom.addExit("Sally Port");
        visitingRoom.addItem(letter);
        map.addRoom(visitingRoom);

        Room cellBlockA = new Room("Cell Block A", "A long hall of cells and despair. My cell is on this hall. There is a man shouting nonsense. The musical notes of a harmonica can be heard upstairs.");
        Item man = new Item("Man", "Seems like he is up to no good");
        man.addAction("speak");
        Item raincoat = new Item("Raincoat", "A shiny yellow raincoat. Must have belonged to another prisoner.");
        raincoat.addAction("craft");
        Item magazine = new Item("Magazine", "A Time's Man Of The Year magazine. John F. Kennedy can be seen on the cover.");
        magazine.addAction("read");
        cellBlockA.addExit("Cell Block B");
        cellBlockA.addExit("My Cell");
        cellBlockA.addItem(man);
        cellBlockA.addItem(raincoat);
        cellBlockA.addItem(magazine);
        map.addRoom(cellBlockA);

        Room cellBlockB = new Room("Cell Block B", "Another corridor of lifeless cells. I don't think I am welcomed up here.");
        Item cardboard = new Item("Cardboard", "A piece of cardboard. Maybe I can craft a boat with it.");
        cardboard.addAction("craft");
        Item harmonica = new Item("Harmonica", "A mouth organ. This might cheer up some inmates.")   ;
        harmonica.addAction("play");
        cellBlockB.addExit("Cell Block A");
        cellBlockB.addItem(cardboard);
        cellBlockB.addItem(harmonica);
        map.addRoom(cellBlockB);

        Room cafeteria = new Room("Cafeteria", "Where the inmates eat food. Bad food.");
        Item fork = new Item("Fork", "A conviently pronged eating utensil. Now I just need something to eat.");
        fork.addAction("none");
        Item sawBlades = new Item("Old saw blade", "They still have some sharpness to them. Now I just need a motor.");
        sawBlades.addAction("craft");
        cafeteria.addExit("Cell Block A");
        cafeteria.addItem(fork);
        cafeteria.addItem(sawBlades);
        map.addRoom(cafeteria);

        Room playerCell = new Room("My Cell", "Home sweet home.");
        Item towel = new Item("Towel", "This thing never seems to be dry.");
        towel.addAction("none");
        Item cup = new Item("Cup", "There is still some water in it");
        cup.addAction("none");
        playerCell.addExit("Cell Block A");
        playerCell.addItem(towel);
        playerCell.addItem(cup);
        map.addRoom(playerCell);

        Room roofOfCellBlockB = new Room("Roof of Cell Block B", "Accessed through a utility corridor behind my cell. Climbed up Cell Block B. I better not stay here for too long. Escape has to be close.");
        roofOfCellBlockB.addExit("My Cell");


        Room roof = new Room("Roof", "On top of the world! A sign reads \"NO ONE ESCAPES ALIVE\". If only I had a boat to sail off on." );
        roof.addExit("Roof of Cell Block B");
        map.addRoom(roof);

        // FileOutputStream fileStream = null;

        // try {
        //     fileStream = new FileOutputStream("gamelog.txt");
        // } catch (Exception FileNotFoundException) {
        //     System.out.println("File Not Found");
        //     System.exit(0);
        // }

        // PrintWriter outFS = new PrintWriter(fileStream);

        boolean win = false;
        boolean validRoom = false;
        String userRoom = "Sally Port";
        String userInput = "";
        Room currentRoom;

        // Starts the game by printing the opening statement
        System.out.println("Welcome To Alacatraz.\nYOU BEAR THE MARK! YOU ARE CURSED!\n");
        // outFS.println("Welcome To Alacatraz.\nYOU BEAR THE MARK! YOU ARE CURSED!\n");

        // Prints out the initial Sally Room information and sets the currentRoom to Sally Port
        System.out.println(map.getRoom(userRoom));
        currentRoom = map.getRoom(userRoom);
        
        // Loops while the player has not escaped
        while (win == false) {
            userInput = scnr.next();

            // If the user wants to go to a different room
            if (userInput.equalsIgnoreCase("go") || userInput.equalsIgnoreCase("move") || userInput.equalsIgnoreCase("exit")) {
                validRoom = false;
                // Asks user where they would like to go and gets their input
                System.out.println("Where would you like to go?");
                scnr.nextLine(); // Scanner buffer
                userRoom = scnr.nextLine();
                
                // Validates that the user selected a proper exit
                validRoom = currentRoom.validateRoom(userRoom);
                
                // While the user does not selected a valid room, ask they for another input
                while (validRoom == false) {
                    System.out.println("\nInvalid exit.\n");
                    userRoom = scnr.nextLine();
                    validRoom = currentRoom.validateRoom(userRoom);
                } 
                // Set the current room to the new room the user selected
                currentRoom = map.getRoom(userRoom);

                // Print out the current room's information
                System.out.println("\n" + currentRoom);
            } 

        }

        // outFS.close();
    }
}