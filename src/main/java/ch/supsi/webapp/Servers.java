package ch.supsi.webapp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Servers {

    public String getItemAsJsonString(int index);
    public void putItem(Item item);
    public Item getItem(int index);
    public void push();
    public boolean contains(Item item);

}
