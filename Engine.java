/*
Richard Khouri
CPSC 1060: RPG
GitHub Repository: https://github.com/rakhouri/EscapeAlcatraz
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
        armory.addExit("Sally Port");
        armory.addItem(brokenVacuumMotor);
        map.addRoom(armory);

        Room visitingRoom = new Room("Visiting Room", "A place where loved one's can visit. I doubt anyone is here for me.");
        Item letter = new Item("Letter", "A letter to a prisoner from a loved one. I wonder if they will ever get to see them again.");
        letter.setItemContent("You open up the Letter and read: \n\tMy name is John Anglin. I escaped from Alcatraz in June 1962 with my brother Clarence and Frank Morris. I'm 83 years old and in bad shape. I have cancer. Yes we all made it that night but barely!");
        visitingRoom.addExit("Sally Port");
        visitingRoom.addItem(letter);
        map.addRoom(visitingRoom);

        Room cellBlockA = new Room("Cell Block A", "A long hall of cells and despair. My cell is on this hall. There is a man shouting nonsense. The musical notes of a harmonica can be heard upstairs.");
        Item raincoat = new Item("Raincoat", "A shiny yellow raincoat. Must have belonged to another prisoner.");
        Item magazine = new Item("Magazine", "A Time's Man Of The Year magazine. John F. Kennedy can be seen on the cover.");
        magazine.setItemContent("You open up the TIME magazine and read: \n\tIn his first year as President, John Fitzgerald Kennedy showed qualities that have made him a promising leader. Those same qualities, if developed further, may make him a great President.");
        cellBlockA.addExit("Cell Block B");
        cellBlockA.addExit("My Cell");
        cellBlockA.addExit("Cafeteria");
        cellBlockA.addItem(raincoat);
        cellBlockA.addItem(magazine);
        map.addRoom(cellBlockA);

        Room cellBlockB = new Room("Cell Block B", "Another corridor of lifeless cells. I don't think I am welcomed up here.");
        Item cardboard = new Item("Cardboard", "A piece of cardboard. Maybe I can craft a boat with it.");
        Item harmonica = new Item("Harmonica", "A mouth organ. This might cheer up some inmates.")   ;
        cellBlockB.addExit("Cell Block A");
        cellBlockB.addItem(cardboard);
        cellBlockB.addItem(harmonica);
        map.addRoom(cellBlockB);

        Room cafeteria = new Room("Cafeteria", "Where the inmates eat food. Bad food.");
        Item fork = new Item("Fork", "A conviently pronged eating utensil. Now I just need something to eat.");
        Item sawBlades = new Item("Old saw blade", "They still have some sharpness to them. Now I just need a motor.");
        cafeteria.addExit("Cell Block A");
        cafeteria.addItem(fork);
        cafeteria.addItem(sawBlades);
        map.addRoom(cafeteria);

        Room playerCell = new Room("My Cell", "Home sweet home. I always imagined It would be possible to escape through these walls.");
        Item towel = new Item("Towel", "This thing never seems to be dry.");
        Item cup = new Item("Cup", "There is still some water in it");
        playerCell.addExit("Cell Block A");
        playerCell.addItem(towel);
        playerCell.addItem(cup);
        map.addRoom(playerCell);

        Room utilityCorridor = new Room("Utility Corridor", "Accessed through a hole in my cell. Climbed up Cell Block B. I better not stay here for too long. Escape has to be close.");
        utilityCorridor.addExit("My Cell");
        utilityCorridor.addExit("Roof");
        map.addRoom(utilityCorridor);


        Room roof = new Room("Roof", "On top of the world! A sign reads \"NO ONE ESCAPES ALIVE\". Now all I need to do is ESCAPE. If only I had a boat to sail off this island.");
        roof.addExit("Utility Corridor");
        map.addRoom(roof);

        Item homemadeDrill;
        Item makeShiftRaft;

        // Creates a FileOutputStream object
        FileOutputStream fileStream = null;

        // Attempts to create a new FileOutputStream and if the file is not found, it catches the exception and ends the program
        try {
            fileStream = new FileOutputStream("gamelog.txt");
        } catch (Exception FileNotFoundException) {
            System.out.println("File Not Found");
            System.exit(0);
        }

        // Creates a PrintWiter object.
        PrintWriter outFS = new PrintWriter(fileStream);

        boolean win = false;
        boolean validRoom = false;
        boolean validItem = false;
        boolean hasMagazine = false;
        boolean hasLetter = false;
        boolean craftDrill = false;
        boolean craftRaft = false;
        boolean holeDrilled = false;
        String userRoom = "Sally Port";
        String userInput = "";
        int itemIndex = 0;
        Room currentRoom;

        // Starts the game by printing the opening statement
        System.out.println("Welcome To Alacatraz.\nYOU BEAR THE MARK! YOU ARE CURSED!\n");
        outFS.println("Welcome To Alacatraz.\nYOU BEAR THE MARK! YOU ARE CURSED!\n");

        // Prints out the initial Sally Room information and sets the currentRoom to Sally Port
        System.out.println(map.getRoom(userRoom));
        currentRoom = map.getRoom(userRoom);
        
        // Loops while the player has not escaped
        while (win == false) {
            userInput = scnr.nextLine();

            // If the user wants to go to a different room. How the player moves
            if (userInput.equalsIgnoreCase("go") || userInput.equalsIgnoreCase("move") || userInput.equalsIgnoreCase("exit") ||userInput.equalsIgnoreCase("go east") ||
                userInput.equalsIgnoreCase("go west") || userInput.equalsIgnoreCase("go north") || userInput.equalsIgnoreCase("go south")) {

                // Asks user where they would like to go and gets their input
                System.out.println("Where would you like to go?");
                outFS.println("Where would you like to go?");
                userRoom = scnr.nextLine();
                
                // Validates that the user selected a proper exit
                validRoom = currentRoom.validateRoom(userRoom);
                
                // While the user does not selected a valid room, ask they for another input
                while (validRoom == false) {
                    System.out.println("Invalid exit.");
                    outFS.println("Invalid exit.");
                    userRoom = scnr.nextLine();
                    validRoom = currentRoom.validateRoom(userRoom);
                } 
                // Set the current room to the new room the user selected
                currentRoom = map.getRoom(userRoom);

                // Print out the current room's information
                System.out.println("\n" + currentRoom);
                outFS.println("\n" + currentRoom);
            
            // If the user wants to look around the room
            } else if (userInput.equalsIgnoreCase("look around") || userInput.equalsIgnoreCase("look") || userInput.equalsIgnoreCase("survery") || userInput.equalsIgnoreCase("survery area") ||
                userInput.equalsIgnoreCase("scout") || userInput.equalsIgnoreCase("scout area")) {
                
                // Calls the look around method which displays the room description and any items that are present
                System.out.println(currentRoom.lookAround());
                outFS.println(currentRoom.lookAround());

            // If the user wants to pick up an item.
            } else if (userInput.equalsIgnoreCase("pick up") || userInput.equalsIgnoreCase("grab") || userInput.equalsIgnoreCase("take") || userInput.equalsIgnoreCase("acquire") || userInput.equalsIgnoreCase("pickup")) {
                validItem = false;

                // If there are no items in the room, output that there are no items
                if (currentRoom.getItems().size() == 0) {
                    System.out.println("There are no items to pick up.");
                    outFS.println("There are no items to pick up.");
                // If there is one item, pick up that one item and add it to the inventory while removing it from the room object
                } else if (currentRoom.getItems().size() == 1) {
                    System.out.println("Picked up " + currentRoom.listItems());
                    outFS.println("Picked up " + currentRoom.listItems());
                    inventory.add(currentRoom.getItems().get(0));
                    currentRoom.getItems().remove(0);
                // If there are multiple items in the room, ask the user which one they want to pick up
                } else if (currentRoom.getItems().size() > 1) {
                    System.out.println("What item do you want to pick up?");
                    outFS.println("What item do you want to pick up?");
                    System.out.println(currentRoom.listItems());
                    outFS.println(currentRoom.listItems());
                    userInput = scnr.nextLine();
                    
                    // Validates that the user chose a valid item
                    validItem = currentRoom.validateItem(userInput);

                    // Loops and asks the user to select a valid item
                    while (validItem == false) {
                        System.out.println("Please input a valid item.");
                        outFS.println("Please input a valid item.");
                        userInput = scnr.nextLine();
                        validItem = currentRoom.validateItem(userInput);
                    }
                    
                    // Finds the index that the item is in the items arraylist in the Room.java class
                    itemIndex = currentRoom.getItemIndex(userInput);

                    // Prints that it picked the item up and adds it to the players inventory while removing it from the room object
                    System.out.println("Picked up " + currentRoom.getItems().get(itemIndex));
                    outFS.println("Picked up " + currentRoom.getItems().get(itemIndex));
                    inventory.add(currentRoom.getItems().get(itemIndex));
                    currentRoom.getItems().remove(itemIndex);
                }
            // If the user wants to view their inventory
            } else if (userInput.equalsIgnoreCase("inventory") || userInput.equalsIgnoreCase("open inventory") || userInput.equalsIgnoreCase("view inventory") || userInput.equalsIgnoreCase("items") 
                || userInput.equalsIgnoreCase("view items")) {
                
                // If the user has no items in their inventory, print that they have no items
                if (inventory.size() == 0) {
                    System.out.println("You have no items.");
                    outFS.println("You have no items.");
                // If they do have items, print out the user's inventory
                } else {
                    System.out.println("\nINVENTORY: ");
                    outFS.println("\nINVENTORY: ");
                    
                    for (Item i : inventory) {
                        System.out.println("\t" + i + "- " + i.getDescription());
                        outFS.println("\t" + i + "- " + i.getDescription());
                    }
                }
            // If the user wants to read the magazine or letter
            } else if (userInput.equalsIgnoreCase("read") || userInput.equalsIgnoreCase("read letter") || userInput.equalsIgnoreCase("read magazine") || userInput.equalsIgnoreCase("open magazine") ||
                userInput.equalsIgnoreCase("open letter")) {
                    
                    // Iterates through the user's inventory to make sure that they have the items to read
                    hasLetter = letter.hasItem(inventory);
                    hasMagazine = magazine.hasItem(inventory);
                    
                    // If the user does not have the letter or magazine, print that they have no items to read
                    if (hasLetter == false && hasMagazine == false) {
                        System.out.println("You have no items to read.");
                        outFS.println("You have no items to read.");
                    // If the user has the letter but no magazine, print letter contents
                    } else if (hasLetter == true && hasMagazine == false) {
                        System.out.println(letter.getContent());
                        outFS.println(letter.getContent());
                    // If the user has the magazine but no letter, print magazine contents
                    } else if (hasLetter == false && hasMagazine == true) {
                        System.out.println(magazine.getContent());
                        outFS.println(magazine.getContent());
                    // If the user has both
                    } else {
                        // Ask the user which one they want to read
                        System.out.println("Which would you like to read? Magazine or Letter?");
                        outFS.println("Which would you like to read? Magazine or Letter?");
                        userInput = scnr.nextLine();

                        // Validate that the user selected either magazine or letter
                        while (!userInput.equalsIgnoreCase("magazine") & !userInput.equalsIgnoreCase("letter")) {
                            System.out.println("Please choose which one you want to read.");
                            outFS.println("Please choose which one you want to read.");
                            userInput = scnr.nextLine();
                        }
                        
                        // If they chose letter, print out it's content
                        if (userInput.equalsIgnoreCase(letter.getName())) {
                            System.out.println(letter.getContent());
                            outFS.println(letter.getContent());
                        // If they chose magazine, print out it's content
                        } else {
                            System.out.println(magazine.getContent());
                            outFS.println(letter.getContent());
                        }
                    }
            // If the user wants to talk to the man in Cell Block A
            } else if (userInput.equalsIgnoreCase("talk") || userInput.equalsIgnoreCase("man") || userInput.equalsIgnoreCase("talk to man") || userInput.equalsIgnoreCase("talk man")
                || userInput.equalsIgnoreCase("speak") || userInput.equalsIgnoreCase("speak to man")) {
                
                // If the user is in Cell Block A
                if (currentRoom.getName().equalsIgnoreCase("Cell Block A")) {
                    System.out.println("You attempt to communicate with the man:");
                    outFS.println("You attempt to communicate with the man:");
                    System.out.println("Fresh meat huh? My name is Weasel. I got put in jail originally for treason but I ended up causing some trouble and now they got me locked up on Alcatraz. If you plan on escaping I wouldn't bother. No one escapes Alcatraz.");
                    outFS.println("Fresh meat huh? My name is Weasel. I got put in jail originally for treason but I ended up causing some trouble and now they got me locked up on Alcatraz. If you plan on escaping I wouldn't bother. No one escapes Alcatraz.");
                // If not, print that there is no one to talk to
                } else {
                    System.out.println("There is on one else to talk to.");
                    outFS.println("There is on one else to talk to.");
                }
            // If the user wants to play the harmonica
            } else if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("use harmonica") || userInput.equalsIgnoreCase("play harmonica") || userInput.equalsIgnoreCase("play music")
                || userInput.equalsIgnoreCase("harmonica")) {
                
                // Checks to see if the harmonica is in the user's inventory
                if (harmonica.hasItem(inventory)) {
                    System.out.println("You play the sweet sweet melodies of Piano Man on the harmonica. The other prisoners enjoy it. Anything to mask the hopelessness that is Alcatraz.");
                    outFS.println("You play the sweet sweet melodies of Piano Man on the harmonica. The other prisoners enjoy it. Anything to mask the hopelessness that is Alcatraz.");
                // If the user does not have the harmonica, print that there is nothing to play
                } else {
                    System.out.println("You don't have anything to play at the moment.");
                    outFS.println("You don't have anything to play at the moment.");
                }
            // If the user wants to use their items to craft a new one.
            } else if (userInput.equalsIgnoreCase("craft") || userInput.equalsIgnoreCase("make") || userInput.equalsIgnoreCase("combine")) {

                // Checks that the user has a broken vacuum motor and saw blades and that they have not crafted it before
                if (brokenVacuumMotor.hasItem(inventory) && sawBlades.hasItem(inventory) && craftDrill == false) {
                    // Print that the user craft a new homemade drill item
                    System.out.println("You combine the broken vacuum motor and old saw blades to create a homemade drill.");
                    outFS.println("You combine the broken vacuum motor and old saw blades to create a homemade drill.");

                    // Create new item object and add it to the user's inventory while removing the items used to craft the new object.
                    homemadeDrill = new Item("Homemade Drill", "Can't believe I was able to make this. I hope no guards find this.");
                    inventory.add(homemadeDrill);
                    inventory.remove(brokenVacuumMotor);
                    inventory.remove(sawBlades);

                    craftDrill = true;
                // Checks to see if the user has cardboard and a raincoat in their inventory and that they have no crafted a raft before
                } else if (cardboard.hasItem(inventory) && raincoat.hasItem(inventory) && craftRaft == false) {
                    // Print that the user created a make-shift raft
                    System.out.println("You combine the cardboard and the raincoat to create a make-shift raft.");
                    outFS.println("You combine the cardboard and the raincoat to create a make-shift raft.");

                    // Create new makeshift raft item object and add it to the user's inventory while removing the items used to craft it
                    makeShiftRaft = new Item("Make-shift Raft", "Now I only need to big body of water to set sail on this. Good thing we are on an island.");
                    inventory.add(makeShiftRaft);
                    inventory.remove(cardboard);
                    inventory.remove(raincoat);

                    craftRaft = true;
                // If the user does not have the sufficient items to craft, print that they have nothing to craft right now
                } else {
                    System.out.println("You have nothing to craft right now.");
                    outFS.println("You have nothing to craft right now.");
                }
            // If the user wants to break/drill a hole in the wall
            } else if (userInput.equalsIgnoreCase("break") || userInput.equalsIgnoreCase("drill") || userInput.equalsIgnoreCase("drill wall") || userInput.equalsIgnoreCase("break wall")) {
                
                // Checks to see if the user is in their cell and has crafted a drill and has not drilled a hole before
                if (currentRoom.getName().equalsIgnoreCase("my cell") && craftDrill && holeDrilled == false) {
                    // Prints out that a hole has been drilled
                    System.out.println("You successfully drill a hole into the wall and cover it with a towel. Could this be the way to freedom?");
                    outFS.println("You successfully drill a hole into the wall and cover it with a towel. Could this be the way to freedom?");

                    // Adds new exit to the player's cell
                    playerCell.addExit("Utility Corridor");

                    // Prints out player cell information with new exit
                    System.out.println("\n" + playerCell);
                    outFS.println("\n" + playerCell);

                    holeDrilled = true;
                // If the user has a drill but is not in their cell. Print that they cannot break anything
                } else if (craftDrill && !currentRoom.getName().equalsIgnoreCase("my cell")) {
                    System.out.println("You have nothing to break around you. I'm sure there is something to break in my own cell.");
                    outFS.println("You have nothing to break around you. I'm sure there is something to break in my own cell.");
                // If the user has no drill and is not in their cell. Say that they craft break anything
                } else {
                    System.out.println("You have nothing to break around you nor have the tools to break something. I'm sure you could find some.");
                    outFS.println("You have nothing to break around you nor have the tools to break something. I'm sure you could find some.");
                }
            // If the user wants to escape
            } else if (userInput.equalsIgnoreCase("escape") || userInput.equalsIgnoreCase("jump") || userInput.equalsIgnoreCase("freedom") || userInput.equalsIgnoreCase("jump off") || userInput.equalsIgnoreCase("leave")) {
                
                // Check that they have drilled the hole, crafted the raft, crafted the drill, and are on the roof
                if (holeDrilled && craftRaft && craftDrill && currentRoom.getName().equalsIgnoreCase("roof")) {
                    // If all the cases are met. Print out the wining statement
                    System.out.println("You climb down the bakery smoke stack at the rear of the cell house. After climbing over the fence, you sneak into the northeast shore of the island and launch your raft.");
                    outFS.println("You climb down the bakery smoke stack at the rear of the cell house. After climbing over the fence, you sneak into the northeast shore of the island and launch your raft.");
                    System.out.println("You set sail for San Fransico.");
                    outFS.println("You set sail for San Fransico.");
                    System.out.println("You have escaped Alcatraz.");
                    outFS.println("You have escaped Alcatraz.");
                    System.out.println("Paradiso awaits.");
                    outFS.println("Paradiso awaits.");

                    // Sets win to true to end the game
                    win = true;
                // If they are on the roof but don't have the raft, print that they cannot escape without it
                } else if (currentRoom.getName().equalsIgnoreCase("roof") && !craftRaft) {
                    System.out.println("Escape is close. I just need a raft to set sail. Swiming would be suicide.");
                    outFS.println("Escape is close. I just need a raft to set sail. Swiming would be suicide.");
                // If they are no on the roof, print there is nowhere to escape
                } else {
                    System.out.println("There is nowhere to escape. It isn't that easy.");
                    outFS.println("There is nowhere to escape. It isn't that easy.");
                }
            // If the user types an unknown command, print that the word is unknown
            } else {
                System.out.println("I don't know the word \"" + userInput + "\".");
                outFS.println("I don't know the word \"" + userInput + "\".");
            }
        }
        // Closes the PrintWriter file.
        outFS.close();
    }
}