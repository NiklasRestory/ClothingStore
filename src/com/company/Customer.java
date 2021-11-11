package com.company;

import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<Clothing> shoppingCart = new ArrayList<>();
    private int money = 1000;

    public ArrayList<Clothing> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Clothing> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Customer(String name, String address, String gender) {
        super(name, address, gender);
    }

    public void addToShoppingCart(Clothing clothingToBuy) {
        shoppingCart.add(clothingToBuy);
    }

    public boolean purchase() {
        boolean purchaseSuccessful = false;
        int totalPrice = 0;
        for (Clothing clothing: shoppingCart) {
            totalPrice += clothing.getPrice();
        }
        if (totalPrice <= money) {
            //Run function to deliverycrew. DeliveryCrew.Send(shoppingCart);
            shoppingCart = new ArrayList<>();
            money -= totalPrice;
            purchaseSuccessful = true;
            System.out.println("Thank you for your purchase! Total cost " + totalPrice + ". You have " + money + " left.");
        }
        else {
            System.out.println("I'm afraid you cannot afford this. It costs " + totalPrice + " and you have " + money + ".");
        }
        return purchaseSuccessful;
    }

    public boolean RemoveFromShoppingCart(String input) {
        boolean removeSuccessful = false;
        Clothing clothingToRemove = null;

        for (Clothing clothing: shoppingCart) {
            if (clothing.getName().toLowerCase().equals(input)) {
                clothingToRemove = clothing;
            }
        }
        if (clothingToRemove != null) {
            shoppingCart.remove(clothingToRemove);
            removeSuccessful = true;
        }

        return removeSuccessful;
    }
}
