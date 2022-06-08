package com.example.foodtruck1;

import android.content.Context;

import java.util.ArrayList;

public class IceCream
{
    private int id;
    private double price;
    private Item container;
    private Item flavor;
    private Item topping;
    private ArrayList<Item> things;
    private ItemManager im;

    public IceCream(int i, Item c, Item f, Item t)
    {
        id = i;
        container = c;
        flavor = f;
        topping = t;
    }



    public int getId()
    {
        return id;
    }

    public double getPrice()
    {
        price = container.getPrice() + flavor.getPrice() + topping.getPrice();
        return price;
    }

    public int getID() { return id; }

    public String getContainer() { return container.getName(); }

    public String getFlavor() {return flavor.getName(); }

    public String getTopping() {return topping.getName(); }

    public void setId(int i)
    {
        id = i;
    }

    public void setContainer(Item c)
    {
        container = c;
    }

    public void setFlavor(Item f) { flavor =f;  }

    public void setTopping(Item t) {topping = t;  }

    public void setPrice(double p)
    {
        price = p;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Order ID: " + id +
                ", Container: " + container +
                ", Flavor: " + flavor +
                ", Topping: " + topping +
                '}';
    }

    public String cuterToString() {
        return "Item{" +
                " Container: " + getContainer() +
                ", Flavor: " + getFlavor() +
                ", Topping: " + getTopping() +
                '}';
    }
}
