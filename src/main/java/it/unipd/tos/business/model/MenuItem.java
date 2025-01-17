////////////////////////////////////////////////////////////////////
// Elia Rech 1123583
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.model;

public class MenuItem {
    ItemType itemType;
    String name;
    double price;

    public MenuItem(ItemType itemType, String name, double price){
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public ItemType getItemType() {
        return itemType;
    }
}