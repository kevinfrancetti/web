package ch.supsi.webapp;

import java.util.ArrayList;
import java.util.List;

public class Server implements Servers {

    private List<Item> items = new ArrayList<>();

    @Override
    public String getItemAsJsonString(int index) {
        return null;
    }

    @Override
    public void putItem(Item item) {
        if(!contains(item))
            items.add(item);
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
}
