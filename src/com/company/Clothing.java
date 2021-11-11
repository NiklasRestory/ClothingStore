package com.company;

public class Clothing {
    private String name;
    private int price;
    private String brand;
    private ForGender forGender;
    private Size size;

    public enum Size {
        Small,
        Medium,
        Large,
        ExtraLarge
    }

    public enum ForGender {
        Male,
        Female,
        Other,
        All
    }

    public Clothing(String name, int price, String brand, ForGender forGender, Size size) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.forGender = forGender;
        this.size = size;
    }
    public Clothing(String name, int price, String brand, Size size) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.forGender = ForGender.All;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ForGender getForGender() {
        return forGender;
    }

    public void setForGender(ForGender forGender) {
        this.forGender = forGender;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
