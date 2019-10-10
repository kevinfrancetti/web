package ch.supsi.webapp;

public interface Servers {

    public String getItemAsJsonString(int index);
    public boolean putItem(Item item);
    public Item getItem(int index);
    public void push();
    public boolean contains(Item item);

}
