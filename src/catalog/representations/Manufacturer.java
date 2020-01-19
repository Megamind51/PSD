package catalog.representations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class Manufacturer {

    private String name;
    private long id;
    private HashMap<String,Item> items;
    private HashMap<String,History> history;


    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public HashMap<String,Item> getItems() {
        return items;
    }

    public Item getItem(String nameProduct){
        return this.items.get(nameProduct);
    }

    @JsonProperty
    public void setItems(HashMap<String,Item> items) {
        this.items = items;
    }

    @JsonProperty
    public HashMap<String,History> getHistory() {
        return history;
    }

    @JsonProperty
    public void setHistory(HashMap<String,History> history) {
        this.history = history;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Item item){
        this.items.put(item.getName(),item);
    }

    public void removeItem(String name){
        this.items.remove(name);
    }

    public void addHistory(Item p){
       this.history.put(p.getName(),new History(p));
    }

    @JsonCreator
    public Manufacturer(@JsonProperty("name")String name,@JsonProperty("id") long id) {
        this.name = name;
        this.id=id;
        this.items = new HashMap<>();
        this.history = new HashMap<>();
    }
}
