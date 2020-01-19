package catalog.representations;

import java.util.*;

public class CompareObj implements Comparator<Order>{

    private ArrayList<Order> sList;

    public CompareObj() {
        sList = new ArrayList<Order>();
    }

    @Override
    public int compare(Order o1, Order o2) {

        if(o1.getNameUser().equals(o2.getNameUser())){
            if(o1.getItemPrice() < o2.getItemPrice())
                return 0;
        }

        if(o1.getItemPrice() > o2.getItemPrice())
            return -1;
        else return 1;




    }
    public void add(Order s1) {
        sList.add(s1);
    }

}