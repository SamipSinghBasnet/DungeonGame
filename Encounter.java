import java.util.Random;

public class Encounter {
    private Enemy enemy;
    private Random ran = new Random();
    private Item item;

    public Encounter() {
        this.enemy = new Enemy(ran.nextInt(100),ran.nextInt(150));
    }
    public boolean rollItem() {
        // health potion
        if(ran.nextInt(10) == 1) {
            this.item = new Item("Health Potion", "Adds +30 Health", 30);
            return true;
        }
        // attack potion
        else if(ran.nextInt(10) == 2) {
            this.item = new Item("Attack Potion", "Adds +30 Attack", 30);
            return true;
        }
        // defense potion

         else if(ran.nextInt(10) == 3) {
             this.item = new Item("Defense Potion", "Adds +30 Defense", 30);
            return true;
        }
        else {
            return false;
        }
    }

    // GET
    public Enemy getEnemy() {
        return enemy;
    }
    public Item getItem() {
        return item;
    }


}