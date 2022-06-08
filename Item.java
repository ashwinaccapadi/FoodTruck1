package com.example.foodtruck1;

public class Item {
    private int id;
    private String category;
    private String name;
    private double price;

    public Item(int i, String c, String n, double p)
    {
        id = i;
        category = c;
        name = n;
        price = p;
    }

    public int getId()
    {
        return id;
    }

    public String getCategory()
    {
        return category;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setCategory(String c)
    {
        category = c;
    }

    public void setPrice(double p)
    {
        price = p;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String cuterToString()
    {
        return name +  ", $" + price;
    }
    public String otherCutertoString()
    {
        return category + ": " +name +  ", $" + price;
    }

}
