public class Room {
    private String description;
    private Enemy enemy;
    private Room nextRoom;
    private boolean hasTreasure;
    private int treasureValue;
    private boolean enemyBlocksPath;
    private boolean hasSpecialTreasure;
    private String specialTreasure;


    public Room(String description, boolean hasTreasure, int treasureValue, boolean hasSpecialTreasure, String specialTreasure, boolean enemyBlocksPath) {
        this.description = description;
        this.hasTreasure = hasTreasure;
        this.treasureValue = treasureValue;
        this.hasSpecialTreasure = hasSpecialTreasure;
        this.specialTreasure = specialTreasure;
        this.enemyBlocksPath = enemyBlocksPath;
    }

    // Overloaded constructor without special treasure or blocking enemy
    public Room(String description, boolean hasTreasure, int treasureValue) {
        this(description, hasTreasure, treasureValue, false, null, false);
    }

    // Overloaded constructor for rooms without treasure
    public Room(String description) {
        this(description, false, 0);
    }


    public String getDescription() {
        return description;
    }


    public boolean hasTreasure() {
        return hasTreasure;
    }

    public int getTreasureValue() {
        return treasureValue;
    }

    public void removeTreasure() {
        this.hasTreasure = false;
        this.treasureValue = 0;
    }


    public boolean hasSpecialTreasure() {
        return hasSpecialTreasure;
    }

    public String getSpecialTreasure() {
        return specialTreasure;
    }

    public void removeSpecialTreasure() {
        this.hasSpecialTreasure = false;
        this.specialTreasure = null;
    }


    public boolean hasEnemy() {
        return enemy != null;
    }

    public String getEnemy() {
        return enemy != null ? enemy.getName() : null;
    }

    public void removeEnemy() {
        enemy = null;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean isEnemyBlockingPath() {
        return hasEnemy() && enemyBlocksPath;
    }


    public boolean hasNextRoom() {
        return nextRoom != null;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }


    public static Room startingRoom() {
        Room room0 = new Room("You are outside in a forest, a cave is in front of you. Move forward into the cave", false, 0, false, null, false);
        Room room1 = new Room("You are in the dimly lit cave. The air is thick and silent.", false, 0, false, null, false);
        Room room2 = new Room("The cave is getting darker and there is water at your feet. You hear a sound coming towards you", false, 0, false, null, true); // Enemy blocks path
        Room room3 = new Room("You enter a room with flickering torches on the walls. A treasure chest sits in the corner. Open it to see what's inside.", true, 30, false, null, false);
        Room room4 = new Room("This room is has no light. You hear a sound coming towards you", false, 0, false, null, true);
        Room room5 = new Room("You enter a majestic room, with gleaming walls and the sound of a distant waterfall. There is a beautiful treasure chest in front of you. Open it to see what is inside.", false, 0, true, "Golden Amulet", false);
        Room room6 = new Room("You come out the other side of the cave to see sunlight streaming through the trees.", false, 0, false, null, false);

        room0.setNextRoom(room1);
        room1.setNextRoom(room2);
        room2.setNextRoom(room3);
        room3.setNextRoom(room4);
        room4.setNextRoom(room5);
        room5.setNextRoom(room6);

        room1.setEnemy(new Enemy("Goblin"));
        room2.setEnemy(new Enemy("Skeleton Warrior"));
        room3.setEnemy(new Enemy("Giant Bat"));
        room4.setEnemy(new Enemy("Shadow Lurker"));
        room5.setEnemy(new Enemy("Mutated Hog"));

        return room0;
    }
}
