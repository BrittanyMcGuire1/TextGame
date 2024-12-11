import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player("Hero", 100);
        Room currentRoom = Room.startingRoom();

        System.out.println("Welcome to the Adventure Game!");
        System.out.println("Type 'look' to inspect the room\n'move' to move to the next room\n'check' to check your health\n'fight' to battle\n'open' to open treasure\n'quit' to exit.");
        System.out.println("\n" + currentRoom.getDescription());
        while (true) {
            System.out.print("\nType your next move ");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "look":
                    look(currentRoom);
                    break;

                case "move":
                    if (currentRoom.isEnemyBlockingPath()) {
                        System.out.println("The " + currentRoom.getEnemy() + " blocks your path! You must defeat it before moving to the next room.");
                    } else if (currentRoom.hasNextRoom()) {
                        currentRoom = currentRoom.getNextRoom();
                        System.out.println("You move to the next room.");
                        System.out.println(currentRoom.getDescription());
                    } else {
                        System.out.println("You’ve reached the end of the adventure.");
                        if (player.hasSpecialTreasure()) {
                            System.out.println("Congratulations! You found the special treasure and completed your journey with a rare reward!");
                        } else {
                            System.out.println("Congratulations on completing your adventure!");
                        }
                        scanner.close();
                        return;
                    }
                    break;

                case "fight":
                    if (currentRoom.hasEnemy()) {
                        System.out.println("You engage in a battle with " + currentRoom.getEnemy());
                        player.decreaseHealth(40); // Example damage
                        System.out.println("You defeated the enemy but lost some health! Your health is now " + player.getHealth() + ".");
                        currentRoom.removeEnemy();
                    } else {
                        System.out.println("There’s nothing to fight here.");
                    }
                    break;


                case "open":
                    openChest(currentRoom, player);
                    break;

                case "check":
                    System.out.println("Your current health is: " + player.getHealth());
                    break;

                case "quit":
                    System.out.println("Thank you for playing! Goodbye.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid action. Try 'look', 'move', 'fight', 'check', 'open', or 'quit'.");
            }

            // Check player health after each action
            if (player.getHealth() <= 0) {
                System.out.println("You have perished in the adventure. Game over.");
                scanner.close();
                return;
            }
        }
    }


    public static void look(Room currentRoom) {
        System.out.println("You look around the room.");
        System.out.println(currentRoom.getDescription());

        if (currentRoom.hasTreasure()) {
            System.out.println("You see a treasure chest in the room!");
        } else {
            System.out.println("There’s no treasure here.");
        }

        if (currentRoom.hasSpecialTreasure()) {
            System.out.println("You notice something sparkling in the corner... A special treasure!");
        }

        if (currentRoom.hasEnemy()) {
            System.out.println("You encounter a fearsome " + currentRoom.getEnemy() + "!");
        }
    }


    public static void openChest(Room currentRoom, Player player) {
        if (currentRoom.hasTreasure()) {
            System.out.println("You open the treasure chest and find a potion!");
            System.out.println("You drink the potion and feel revitalized.");
            player.increaseHealth(currentRoom.getTreasureValue());
            currentRoom.removeTreasure();
        } else if (currentRoom.hasSpecialTreasure()) {
            System.out.println("You find a special treasure: " + currentRoom.getSpecialTreasure() + "!");
            System.out.println("This treasure seems to be something special.");
            player.collectSpecialTreasure();
            currentRoom.removeSpecialTreasure();
        } else {
            System.out.println("There's no treasure chest to open here.");
        }
    }
}
