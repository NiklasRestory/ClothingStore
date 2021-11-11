package com.company;

public class Shirt extends Clothing {
    private boolean hoodie;

    public Shirt(String name, int price, String brand, ForGender forGender, Size size, boolean hoodie) {
        super(name, price, brand, forGender, size);
        this.hoodie = hoodie;
    }

    public Shirt(String name, int price, String brand, Size size, boolean hoodie) {
        super(name, price, brand, size);
        this.hoodie = hoodie;
    }

    public boolean getHoodie() {
        return hoodie;
    }

    public void setHoodie(boolean hoodie) {
        this.hoodie = hoodie;
    }
}
