public class Enemy {
    private int Atk;
    private int Speed;
    private int Def;
    private int Life;
    private int ExpValue;

    // default enemy
    public Enemy(int attack, int defense) {
        this.Atk = attack;
        this.Speed = 65;
        this.Def = defense;
        this.Life = 90;
        this.ExpValue = 50;
    }

    // GETTERS
    public int getAtk() {
        return Atk;
    }
    public int getSpeed() {
        return Speed;
    }
    public int getDef() {
        return Def;
    }
    public int getLife() {
        return Life;
    }
    public int getXP() {
        return ExpValue;
    }

    // SETTERS
    public void setAtk(int x) {
        this.Atk += x;
    }
    public void setSpeed(int x) {
        this.Speed += x;
    }
    public void setDef(int x) {
        this.Def += x;
    }
    public void setLife(int x) {
        this.Life += x;
    }
    public void setXP(int x) {
        this.ExpValue += x;
    }

}