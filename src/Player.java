class Player {
    private String name;
    public int health;
    private boolean hasSpecialTreasure;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.hasSpecialTreasure = false;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }
    public void increaseHealth(int amount) {
        health += amount;
        System.out.println("Your health is now: " + health);
    }
    public void collectSpecialTreasure() {
        hasSpecialTreasure = true;
    }

    public boolean hasSpecialTreasure() {
        return hasSpecialTreasure;
    }

}