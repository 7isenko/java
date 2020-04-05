package lab5.lab3;

import java.util.ArrayList;

public enum Item {
    PUN,
    BEER,
    FLAG,
    STICK,
    CUP,
    BOTTLE,
    ;

    private Item storage;
    private Human carrier;
    private ArrayList<Item> storing = new ArrayList<Item>();


    public Item getStorage() {
        return storage;
    }

    public void putInto(Item item) {
        this.storage = item;
        item.storing.add(this);
        System.out.println(this.toString() + " is in the " + item.toString());
    }

    public void setCarrier(Human carrier) {
        if (this.carrier != null) {
            this.carrier.removeCarry(this);
            for (Item i : this.storing) {
                this.carrier.removeCarry(i);
            }
        }
        this.carrier = carrier;
        carrier.addCarry(this);
        for (Item i : this.storing) {
            i.carrier = carrier;
            carrier.addCarry(i);
    }
}

    Human getCarrier() {
        return carrier;
    }

    boolean isCarried() {
        return this.getCarrier() != null;
    }

}
