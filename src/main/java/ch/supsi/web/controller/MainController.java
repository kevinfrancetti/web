package ch.supsi.web.controller;


import ch.supsi.web.model.ItemType;
import ch.supsi.web.service.CategoryService;
import ch.supsi.web.service.ItemService;
import ch.supsi.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
public class MainController {

    private static final Logger log = Logger.getLogger(MainController.class.getName());
    static{
        try {
            FileHandler handler = new FileHandler(MainController.class.getName() + ".log", true);
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);
            log.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model){
        log.info("");
        return "index";
    }

    @RequestMapping(value = "/item/new", method = RequestMethod.GET)
    public String createItemPage(Model model){
        model.addAttribute("types", ItemType.values());
        model.addAttribute("categories", categoryService.getAll());
        return "createItem";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String itemById(@PathVariable int id, Model model){
        return null;
    }


}
