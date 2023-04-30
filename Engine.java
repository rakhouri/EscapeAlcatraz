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
        Scanner scnr = new Scanner(System.in);

        ArrayList<Item> inventory = new ArrayList<Item>();
        
        GameMap map = new GameMap();

        Room sallyPort = new Room("Sally Port", "An armored and controlled entryway to the fortress of Alcatraz beyond. All hope abandon ye who enter in.");
        sallyPort.addExit("Armory");
        sallyPort.addExit("Visiting Room");

        Room armory = new Room("Armory", "A place where U.S. soldiers would keep their weapons and store their ammunition. Now it is just an abandoned storage room.");
        Item brokenVacuumMotor = new Item("Motor of a broken vacuum", "Still seems like it can be used.");
        brokenVacuumMotor.addAction("craft");
        armory.addExit("Sally Port");
        armory.addItem(brokenVacuumMotor);

        Room visitingRoom = new Room("Visiting Room", "A place where loved one's can visit. I doubt anyone is here for me.");
        Item letter = new Item("Letter", "A letter to a prisoner from a loved one. I wonder if they will ever get to see them again.");
        letter.addAction("read");
        visitingRoom.addExit("Sally Port");
        visitingRoom.addItem(letter);

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
        


    }
}