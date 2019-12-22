package lab3;

import java.util.ArrayList;

public class Item {
    private String name;
    private Item storage;
    private Creature carrier;
    private ArrayList<Item> storing = new ArrayList<Item>();

    Item(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public Item getStorage() {
        return storage;
    }

    void putInto(Item item) {
        this.storage = item;
        item.storing.add(this);
        System.out.println(this.getName() + " is in the " + item.getName());
    }

    void setCarrier(Creature carrier) {
        if (this.carrier != null) {
            this.carrier.removeCarry(this);
            for (Item i: this.storing) {
                this.carrier.removeCarry(i);
            }
        }
        this.carrier = carrier;
        carrier.addCarry(this);
        for (Item i: this.storing) {
            i.carrier = carrier;
            carrier.addCarry(i);
        }
    }

    Creature getCarrier() {
        return carrier;
    }

    boolean isCarried() {
        return this.getCarrier() != null;
    }
}
