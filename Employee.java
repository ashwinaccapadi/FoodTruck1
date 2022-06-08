package com.example.foodtruck1;

public class Employee
{
    private int id;
    private String username;
    private String password;

    public Employee(int i, String u, String p)
    {
        id = i;
        username = u;
        password = p;
    }

    public int getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
