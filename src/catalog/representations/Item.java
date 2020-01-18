package catalog.representations;

import java.util.ArrayList;
import java.util.Collections;

public class Item {
    private int q_min;
    private int q_max;
    private int p_min;
    private int time;
    private int id;
    private ArrayList<Order> orders;


    public int getQ_min() {
        return q_min;
    }

    public void setQ_min(int q_min) {
        this.q_min = q_min;
    }

    public int getQ_max() {
        return q_max;
    }

    public void setQ_max(int q_max) {
        this.q_max = q_max;
    }

    public int getP_min() {
        return p_min;
    }

    public void setP_min(int p_min) {
        this.p_min = p_min;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    }

    public Item(int q_min, int q_max, int p_min, int time, int id) {
        this.q_min = q_min;
        this.q_max = q_max;
        this.p_min = p_min;
        this.time = time;
        this.id = id;
        this.orders = new ArrayList<>();
    }

    public Item(int q_min, int q_max, int p_min, int time, int id, ArrayList<Order> order) {
        this.q_min = q_min;
        this.q_max = q_max;
        this.p_min = p_min;
        this.time = time;
        this.id = id;
        for(Order o: order){
            this.orders.add(o.clone());
        }
    }

    public Item clone(){
        return new Item(this.q_min,this.q_max,this.p_min,this.time,this.id,this.orders);
    }

    public int getItemSold(){
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

    public ArrayList<Order> getOrdersFinal(){
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


    public static void main(String args[]){
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


}
