package lab3;

import lab3.exceptions.CoffeeIsHot;

import java.util.ArrayList;

public class Swede extends Human {

    @Override
    public void react() {
        System.out.println("Swede does not react");
    }

    @Override
    public void speak() {
        System.out.println(this.getName() + " is silent");
    }

    @Override
    public void take(Item item) {
        if (item.isCarried()) {
            System.out.println(this.getName() + " tried to take the " + item.getName() + " but failed");
            try {
                search(this.getCarry());
            } catch (CoffeeIsHot coffeeIsHot) {
                System.out.println(coffeeIsHot.getMessage());
                System.out.println(this.getName() + " got burnt");
            }
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item.getName());
        }
    }

    private void search(ArrayList<Item> carry) throws CoffeeIsHot {
        if (carry.get(0).getName().equals("coffee")) {
            System.out.println(this.getName() + " dunked in the " + carry.get(0).getName());
            throw new CoffeeIsHot("Coffee is hot");
        } else {
            if (carry.get(0) != null)
                System.out.println(this.getName() + " took " + carry.get(0).getName());
            else System.out.println(this.getName() + " has nothing");
        }
    }
}

