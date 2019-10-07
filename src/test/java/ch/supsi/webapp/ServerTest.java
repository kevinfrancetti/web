package ch.supsi.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerTest {

    static Server s = null;

    @Before
    public void setUp() throws Exception {
        s = new Server();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getItemAsJsonString() {
        System.out.println("ola");
    }

    @Test
    public void putItem() {

        List<Item> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Item item = new Item();
            item.setName(randomString());
            item.setAuthor(randomString());
            item.setDescription(randomString());
            list.add(item);
        }

        list.forEach((i) -> s.putItem(i));

        Item item = new Item();
        item.setAuthor(randomString());
        item.setName(randomString());
        item.setDescription(randomString());

        //list.add(item);

        for(Item i : list){
            assert (s.contains(i));
        }


    }

    @Test
    public void setName() {
        System.out.println("Name");
    }

    public String randomString() {

        Random r = new Random();
        String s = new String();
        for(int i = 0; i < 6; i++){
            char c = (char) (r.nextInt(122-32) + 32);
            s += c;
        }
        return s;
    }

}