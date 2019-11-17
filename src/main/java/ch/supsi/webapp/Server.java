package ch.supsi.webapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Dio boia
 */
public class Server implements Servers {

    public List<Item> items = new ArrayList<>();

    @Override
    public String getItemAsJsonString(int index) {
        return null;
    }

    @Override
    public boolean putItem(Item item) {
        if(!contains(item)){
            items.add(item);
            return true;
        }
        return false;
    }

    @Override
    public Item getItem(int index) {
        return items.get(index);
    }

    @Override
    public void push() {

    }

    @Override
    public boolean contains(Item item) {
        return items.contains(item);
    }

    public List<Item> getAll(){
        return items;
    }


}
