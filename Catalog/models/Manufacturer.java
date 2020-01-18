package models;

public class Manufacturer {

    String name;
    Item[] items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer(String name) {
        this.name = name;
    }
}
