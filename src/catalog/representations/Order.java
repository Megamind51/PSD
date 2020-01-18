package catalog.representations;


public class Order {
    private int idUser;
    private int idItem;
    private int itemAmount;
    private int itemPrice;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Order(int idUser, int idItem, int itemAmount, int itemPrice) {
        this.idUser = idUser;
        this.idItem = idItem;
        this.itemAmount = itemAmount;
        this.itemPrice = itemPrice;
    }

    public Order clone(){
        return new Order(this.idUser,this.idItem,this.itemAmount,this.itemPrice);
    }


}
