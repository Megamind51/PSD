package models;

public class Item {
    int q_min;
    int q_max;
    int p_min;
    int time;
    int id;

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

    public Item(int q_min, int q_max, int p_min, int time, int id) {
        this.q_min = q_min;
        this.q_max = q_max;
        this.p_min = p_min;
        this.time = time;
        this.id = id;
    }
}
