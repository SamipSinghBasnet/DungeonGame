public class Player {
    private int HeroLevel;
    private int Atk;
    private int Speed;
    private int Def;
    private int Health;
    private int MaxHealth;
    private int XP;
    // Every 100 xp you level up
    // Level up mechanic updates alls stats by 1
    // player is an encounter

    // default player
    public Player() {
        this.HeroLevel = 1;
        this.Atk = 100;
        this.Speed = 50;
        this.Def = 200;
        this.Health = 200;
        this.MaxHealth = 150;
        this.XP = 0;
    }


    // GETTERS
    public int getAtk() {
        return Atk;
    }
    public int getDef() {
        return Def;
    }
    public int getSpeed() {
        return Speed;
    }
    public int getHealth() {
        return Health;
    }
    public int getMaxHealth() {
        return MaxHealth;
    }
    public int getLevel() {
        return HeroLevel;
    }
    public int getXP() {
        return XP;
    }

    //SETTERS
    public void setAtk(int x) {
        this.Atk += x;
    }
    public void setDef(int x) {
        this.Def += x;
    }
    public void setSpeed(int x) {
        this.Speed += x;
    }
    public void setHealth(int x) {
        this.Health += x;
    }
    public void setMaxHealth(int x) {
        this.MaxHealth += x;
    }
    public void setLevel(int x) {
        this.HeroLevel += x;
    }
    public void setXP(int x) {
        this.XP += x;
    }

}