package catalog.representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.Collections;

public class Item {
    private int q_min;
    private int q_max;
    private int p_min;
    private int time;
    private String name;
    private ArrayList<Order> orders;

    @JsonProperty
    public int getQ_min() {
        return q_min;
    }

    public void setQ_min(int q_min) {
        this.q_min = q_min;
    }

    @JsonProperty
    public int getQ_max() {
        return q_max;
    }

    public void setQ_max(int q_max) {
        this.q_max = q_max;
    }

    @JsonProperty
    public int getP_min() {
        return p_min;
    }

    public void setP_min(int p_min) {
        this.p_min = p_min;
    }

    @JsonProperty
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public ArrayList<Order> getOrders() {
        //  ArrayList<Order> newOrders = new ArrayList<>();
        //  for(Order o: this.orders)
        //      newOrders.add(o.clone());
        return this.orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder (Order nova){
        this.orders.add(nova);
        Collections.sort(this.orders, new CompareObj());
    }

    @JsonCreator
    public Item(@JsonProperty("q_min") int q_min,@JsonProperty("q_max") int q_max,@JsonProperty("p_min") int p_min,@JsonProperty("time") int time,@JsonProperty("name") String name) {
        this.q_min = q_min;
        this.q_max = q_max;
        this.p_min = p_min;
        this.time = time;
        this.name = name;
        this.orders = new ArrayList<>();
    }


/*
    @JsonCreator
    public Item(@JsonProperty("q_min") int q_min,@JsonProperty("q_max") int q_max,@JsonProperty("p_min") int p_min,@JsonProperty("time") int time,@JsonProperty("id") int id,@JsonProperty("orders") ArrayList<Order> orders) {
        this.q_min = q_min;
        this.q_max = q_max;
        this.p_min = p_min;
        this.time = time;
        this.id = id;
        this.orders = new ArrayList<>();
        for(Order o: orders){
            this.orders.add(o.clone());
        }
    }
    */

    public Item clone(){
        return new Item(this.q_min,this.q_max,this.p_min,this.time,this.name);
    }

    public int calculateItemSold(){
        Collections.sort(this.orders, new CompareObj());
        int max = this.getQ_max();
        int itemSold = 0;
        for(Order o: this.orders){
            if(itemSold + o.getItemAmount() > max )
                return max;
            else itemSold+= o.getItemAmount();
        }

        return itemSold;
    }

    public ArrayList<Order> calculateOrdersFinal(){
        ArrayList<Order> winners = new ArrayList<>();
        int max = this.getQ_max();
        int itemSold = 0;
        Order nova ;
        for(Order o: this.orders){
            nova = o.clone();
            if(itemSold + o.getItemAmount() > max ){
                nova.setItemAmount(max-itemSold);
            }
            itemSold+= o.getItemAmount();
            winners.add(nova);
        }

        return winners;
    }


   /* public static void main(String args[]){
        Order o1 = new Order(1,2,3,1);
        Order o2 = new Order(2,2,5,4);

        Item p = new Item(5,6,1,100,2);
        p.addOrder(o1);
        p.addOrder(o2);
        System.out.println("ORDEM:");
        for(Order o: p.getOrders())
            System.out.println("VALOR: " + o.getItemPrice());


        Collections.sort(p.getOrders(), new CompareObj());

        for(Order o: p.getOrders())
            System.out.println("VALOR: " + o.getItemPrice());

        System.out.println("VENDIDOS:" + p.getItemSold());

        ArrayList<Order> winners = p.getOrdersFinal();
        for(Order o: winners)
            System.out.println("Quant: " + o.getItemAmount());

    }
*/

}
