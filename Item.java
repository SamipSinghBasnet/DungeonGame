public class Item {
    private String name = null;
    private String description = null;
    // what does the item ADD to?
    //EX: adds to health, ex, attack
    int statBoost = 0;

    public Item(String name, String description, int statBoost) {
        this.name = name;
        this.description = description;
        this.statBoost = statBoost;
    }

    // GET
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getStatBoost() {
        return statBoost;
    }

    // SET
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatBoost(int statBoost) {
        this.statBoost = statBoost;
    }
}