package catalog.representations;


import java.util.ArrayList;

public class History  {
    private int q_min;
    private int q_max;
    private int p_min;
    private int time;
    private int id;
    private ArrayList<Order> orders;
    private int item_sold;
    private ArrayList<Order> ordersWon;


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


    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }


    public History(Item p) {
        this.q_min = p.getQ_min();
        this.q_max = p.getQ_max();
        this.p_min = p.getP_min();
        this.time = p.getTime();
        this.id = p.getId();
        for(Order o: p.getOrders()){
            this.orders.add(o.clone());
        }
        this.item_sold = p.getItemSold();
        this.ordersWon = p.getOrdersFinal();
    }



}
