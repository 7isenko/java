package lab3;

import java.util.ArrayList;

public class Julius extends Human {
    {
        super.setName("Julius");
        super.setAge(42);
        System.out.println(this.getName() + " is here");
    }

    @Override
    public void react() {
        System.out.println("Julius does not react");
    }

    @Override
    void speak() {
        System.out.println(this.getName() + " is silent");
    }

    @Override
    public void take(Item item) {
        if (item.isCarried()) {
            System.out.println(this.getName() + " tried to take the " + item.getName() + " but failed");
            search(this.getCarry());
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item.getName());
        }
    }

    private void search(ArrayList<Item> carry) {
        if (carry.get(0).getName().equals("coffee")) {
            System.out.println(this.getName() + " dunked in coffee");
        } else {
            if (carry.get(0) != null)
            System.out.println(this.getName() + " took " + carry.get(0).getName());
            else System.out.println(this.getName() + " has nothing");
        }
    }
}
