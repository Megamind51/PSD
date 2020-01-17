package models;

public class Bid {

    int value;
    int units;
    int id;
    String username;
    String manufacturer_name;

    public Bid(int value, int units, int id, String username, String manufacturer_name) {
        this.value = value;
        this.units = units;
        this.id = id;
        this.username = username;
        this.manufacturer_name = manufacturer_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }
}
