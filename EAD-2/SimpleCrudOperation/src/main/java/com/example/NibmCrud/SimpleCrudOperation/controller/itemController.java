package com.example.NibmCrud.SimpleCrudOperation.controller;

import com.example.NibmCrud.SimpleCrudOperation.model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/items")
public class itemController {
    private List<Item> items = new ArrayList<>();
    private int currentId = 1;

    @GetMapping
    public List<Item> getAllItems(){
        return items;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        item.setId(currentId++);
        items.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
        for(Item item : items)
        {
            if(item.getId() == id)
            {
                item.setName(updatedItem.getName());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        for (Item item : items)
        {
            if (item.getId() == id)
            {
                items.remove(item);
                return "Item with ID"+ id + "deleted.";
            }
        }
        return "Item Not Found";
    }
}