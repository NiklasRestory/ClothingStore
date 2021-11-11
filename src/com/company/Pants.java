package com.company;

public class Pants extends Clothing {
    private boolean slim;

    public Pants(String name, int price, String brand, ForGender forGender, Size size, boolean slim) {
        super(name, price, brand, forGender, size);
        this.slim = slim;
    }

    public Pants(String name, int price, String brand, Size size, boolean slim) {
        super(name, price, brand, size);
        this.slim = slim;
    }

    public boolean isSlim() {
        return slim;
    }

    public void setSlim(boolean slim) {
        this.slim = slim;
    }
}
