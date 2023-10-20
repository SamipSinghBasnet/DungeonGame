public class Box {
    private String data;
    private String dataHolder;
    private Box next;
    private Box previous;
    private Boolean isDeleted;
    private Encounter encounter = new Encounter();

    public Box(String data) {
        this.data = data;
        this.next = null;
        this.previous = null;
        this.isDeleted = false;
    }
    // GETTERS
    public String getData() {
        return data;
    }
    public String getDataHolder() {
        return dataHolder;
    }
    public Box getNext() {
        return next;
    }
    public Box getPrevious() {
        return previous;
    }
    public boolean getIsDeleted() {
        return isDeleted;
    }
    public Encounter getEncounter() {
        return encounter;
    }

    // SETTERS
    public void setData(String data) {
        this.data = data;
    }
    public void setDataHolder(String dataHolder) {
        this.dataHolder = dataHolder;
    }
    public void setNext(Box next) {
        this.next = next;
    }
    public void setPrevious(Box previous) {
        this.previous = previous;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}