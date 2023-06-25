package ati.ati;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class nullList {
    private SimpleIntegerProperty itemNo;
    private SimpleStringProperty itemName;


    public nullList(Integer itemNo, String itemName) {
        this.itemNo = new SimpleIntegerProperty(itemNo) ;
        this.itemName = new SimpleStringProperty(itemName);
    }
    public int getItemNo() {
        return itemNo.get();
    }

    public void setItemNo(int itemNo) {
        this.itemNo = new SimpleIntegerProperty(itemNo);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

}
