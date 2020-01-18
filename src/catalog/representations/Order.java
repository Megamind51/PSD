package catalog.representations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    private String nameUser;
//    private String nameManu;
//    private String nameItem;
    private int itemAmount;
    private int itemPrice;

    @JsonProperty
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /*
    public String getNameManu() {
        return nameManu;
    }

    public void setNameManu(String nameManu) {
        this.nameManu = nameManu;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

     */
    @JsonProperty
    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    @JsonProperty
    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    @JsonCreator
    public Order(@JsonProperty("nameUser") String nameUser, /*String nameManu, String nameItem,*/ @JsonProperty("itemAmount") int itemAmount,@JsonProperty("itemPrice") int itemPrice) {
        this.nameUser = nameUser;
     /*   this.nameManu = nameManu;
        this.nameItem = nameItem; */
        this.itemAmount = itemAmount;
        this.itemPrice = itemPrice;
    }

    public Order clone(){
        return new Order(this.nameUser,/*this.nameManu,this.nameItem,*/this.itemAmount,this.itemPrice);
    }


}
