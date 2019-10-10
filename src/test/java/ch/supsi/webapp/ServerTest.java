package ch.supsi.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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