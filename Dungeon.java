import java.util.Scanner;

public class Dungeon {
    private Box [] boxes;
    private int numOfLevels;
    private Box newCurrentBox;
    private Player player;

    public void DungeonHelper(int numOfLevels) {
        this.numOfLevels = numOfLevels;
        this.boxes = new Box[numOfLevels];
        this.player = new Player();

        // THIS COULD DEFINITELY BE COMBINED INTO 1 OR 2 LOOPS . . ..
        // Creates N number of boxes
        for(int i = 0; i < numOfLevels; i++) {
            boxes[i] = new Box("["+i+"]");
            // System.out.println("\n"+ boxes[i].getData());
        }
        // sets the last box.next = first box
        boxes[numOfLevels-1].setNext(boxes[0]);
        // sets all next boxes
        for(int j = 0; j < numOfLevels; j++) {
            if(boxes[j].getNext() == null) {
                boxes[j].setNext(boxes[j+1]);
            }
             System.out.println(boxes[j].getNext().getData());
        }
        // sets the first box.next = last box
        boxes[0].setPrevious(boxes[numOfLevels-1]);
        // sets all previous boxes
        for(int k = 0; k < numOfLevels; k++) {
            if(boxes[k].getPrevious() == null) {
                boxes[k].setPrevious(boxes[k-1]);
            }
            // System.out.println(boxes[k].getPrevious().getData());
        }
        startGame();

    }
    // GETS player
    public Player getPlayer() {
        return player;
    }
    // GETS currentBox
    public Box getTheCurrentBox() {
        return newCurrentBox;
    }
    // starts the game by starting the player in the middle
    private void startGame() {
        newCurrentBox = boxes[(int) Math.floor(numOfLevels/2)];
        // newCurrentBox.setCurrent(true);
        newCurrentBox.setDataHolder(newCurrentBox.getData());
        newCurrentBox.setData(newCurrentBox.getData() + " You Are Here");
        commandMenu();
    }
    // what does the player want to do next
    private void commandMenu() {
        System.out.println("\nEnter Command:");
        System.out.println("PRINT | UP | DOWN | ATTACK | RUN | QUIT");

        try (Scanner myObj = new Scanner(System.in)) {
            String command = myObj.nextLine();

            if(command.equalsIgnoreCase("PRINT")) {
                printDungeon(false);
            }
            else if(command.equalsIgnoreCase("down")) {
                move(newCurrentBox,true);
            }
            else if(command.equalsIgnoreCase("up")) {
                move(newCurrentBox,false);
            }
            else if(command.equalsIgnoreCase("attack")) {
                attack(newCurrentBox);
            }
            else if(command.equalsIgnoreCase("run")) {
                run(newCurrentBox);
            }
            else if(command.equals("cheat")) {
                printDungeon(true);
            }
            else if(command.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            // else if(command.equals("use item")) {

            // }
            else {
                System.out.println("Invalid Input . . .");
                commandMenu();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    // player is able to RUN (skip/delete) current box but loses health
    private void run(Box currentBox) {
        player.setHealth(-30);
        if(player.getHealth() <= 0) {
            endGame(false);
        }
        System.out.println("\nHealth Remaining: " + player.getHealth());
        delete(currentBox);
    }
    // if player wins, delete the box
    private void attack(Box currentBox) {
        if(player.getAtk() >= currentBox.getEncounter().getEnemy().getDef()) {
            System.out.println("ooh enemy ");
            System.out.println("you strike");
            System.out.println("\nPLAYER BEATS ENEMY");
            // rolls a random number to see if the player gets an item
            if(currentBox.getEncounter().rollItem()) {
                String itemName = currentBox.getEncounter().getItem().getName();
                System.out.println("\nYou Picked Up A " + itemName + "!");
                useItem(itemName,currentBox);
            }
            else{
                System.out.println("you did not recieve any reward");
            }
            delete(currentBox);
        }
        else {
            System.out.println("enemy  strike");
            System.out.println("\nENEMY BEATS PLAYER");
            // player loses health equal to enemy's attack
            player.setHealth( - currentBox.getEncounter().getEnemy().getAtk());
            // if player loses to enemy, enemy loses def - player attack
            currentBox.getEncounter().getEnemy().setDef(- player.getAtk());
            if(player.getHealth() <= 0) {
                endGame(false);
            }
            System.out.print("\nHealth Remaining: " + player.getHealth());
        }
        commandMenu();
    }
    // uses item
    public void useItem(String itemName, Box currentBox) {
        if(itemName.equals("Health Potion")) {
            player.setHealth(currentBox.getEncounter().getItem().getStatBoost());
            System.out.println(currentBox.getEncounter().getItem().getDescription());
            System.out.println("Player Health: " + player.getHealth());
        }
        else {
            player.setAtk(currentBox.getEncounter().getItem().getStatBoost());
            System.out.println(currentBox.getEncounter().getItem().getDescription());
            System.out.println("Player Attack: " + player.getAtk());
        }
    }
    // deletes the box if the player wins
    private void delete(Box currentBox) {
        currentBox.setData(null);
        currentBox.setIsDeleted(true);
        // if the player wins move them up to the next box
        move(currentBox,true);
        commandMenu();
    }
    // if the player wins/loses
    private void endGame(boolean win) {
        if(win) {
            System.out.println("\nYOU HAVE COMPLETED THE DUNGEON!\n");
        }
        else if(!win) {
            System.out.println("\nGAME OVER!");
        }
        System.exit(0);
    }
    // when the player wants to move
    private void move(Box currentBox, Boolean up) {
        if(!currentBox.getIsDeleted()) {
            // puts box back to default data if not deleted
            currentBox.setData(currentBox.getDataHolder());
        }
        if(up) {
            newCurrentBox = currentBox.getNext();
            // if current box, next box, and previous box are all deleted the game must be over
            if(newCurrentBox.getIsDeleted() && newCurrentBox.getNext().getIsDeleted() && newCurrentBox.getPrevious().getIsDeleted()) {
                endGame(true);
            }
            // if next box is deleted skip the box
            if(newCurrentBox.getIsDeleted()) {
                newCurrentBox = newCurrentBox.getNext();
            }
            newCurrentBox.setDataHolder(newCurrentBox.getData());
            newCurrentBox.setData(newCurrentBox.getData() + " You Are Here");
        }
        // if the player wants to move down
        else if(!up) {
            newCurrentBox = currentBox.getPrevious();
            // if previous box is deleted skip the box
            if(newCurrentBox.getIsDeleted()) {
                newCurrentBox = newCurrentBox.getPrevious();
            }
            newCurrentBox.setDataHolder(newCurrentBox.getData());
            newCurrentBox.setData(newCurrentBox.getData() + " You Are Here");
        }
        printDungeon(false);
        commandMenu();
    }
    // displays dungeon 
    private void printDungeon(boolean cheat) {
        for(int i = 0; i < numOfLevels; i++) {
            if(!boxes[i].getIsDeleted()) {
                System.out.println("\n" + boxes[i].getData());
                if(cheat) {
                    System.out.println("Enemy Attack:" + boxes[i].getEncounter().getEnemy().getAtk());
                    System.out.println("Enemy Defense:" + boxes[i].getEncounter().getEnemy().getDef());
                }
            }
        }
        commandMenu();
    }
    // starts the game by player picking difficulty
    public void startMenu() {
        try (Scanner myObj = new Scanner(System.in)) {
            // easy has 5 levels, medium has 15, and hard has 30
            System.out.println("\nSelect Difficulty:");
            System.out.println("EASY | MEDIUM | HARD | CUSTOM\n");

            String difficulty = myObj.nextLine();

            if(difficulty.equalsIgnoreCase("easy")) {
                numOfLevels = 5;
            }
            else if(difficulty.equalsIgnoreCase("medium")) {
                numOfLevels = 15;
            }
            else if(difficulty.equalsIgnoreCase("hard")) {
                numOfLevels = 30;
            }
            else if(difficulty.equalsIgnoreCase("custom")) {
                System.out.println("Enter Desired Number Of Levels: ");
                numOfLevels = myObj.nextInt();
                if(numOfLevels <= 0 ) {
                    System.out.println("Cannot Have " + numOfLevels + " Levels!");
                    startMenu();
                }
            }
            else {
                // if input is invalid just restart menu
                System.out.println("Invalid Input . . .");
                startMenu();

            }
            System.out.println("Difficulty Chosen: " + difficulty.toUpperCase() + " ");
            DungeonHelper(numOfLevels);

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}