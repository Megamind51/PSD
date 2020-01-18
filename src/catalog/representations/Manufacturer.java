package catalog.representations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Manufacturer {

    private String name;
    private long id;
    private ArrayList<Item> items;
    private ArrayList<History> history;


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
    public ArrayList<Item> getItems() {
        return items;
    }

    @JsonProperty
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @JsonProperty
    public ArrayList<History> getHistory() {
        return history;
    }

    @JsonProperty
    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }


    @JsonCreator
    public Manufacturer(@JsonProperty("name")String name,@JsonProperty("id") long id) {
        this.name = name;
        this.id=id;
        this.items = new ArrayList<Item>();
        this.history = new ArrayList<History>();
    }
}
