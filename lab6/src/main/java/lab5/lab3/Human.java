package lab5.lab3;

import java.io.Serializable;
import java.util.ArrayList;

public class Human extends Creature implements Comparable<Human>, Serializable {

    public void speak() {
        System.out.println(this.getName() + " said something");
    }

    public int compareTo(Human o) {
        int result = this.getName().compareTo(o.getName());
        if (result == 0) result = this.getAge().compareTo(o.getAge());
        if (result == 0)
            if (!this.getPlaces().equals(o.getPlaces())) {
                ArrayList<Place> h = this.getPlaces();
                h.removeAll(o.getPlaces());
                result = h.size();
            }
        if (result == 0)
            if (!this.getCarry().equals(o.getCarry())) {
                ArrayList<Item> h = this.getCarry();
                h.removeAll(o.getCarry());
                result = h.size();
            }
        return result;
    }

    public Human() {
    }

    public Human(String name) {
        this.setName(name);
    }

    public Human(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public Human(String name, int age, ArrayList<Place> places) {
        this.setName(name);
        this.setAge(age);
        this.setPlaces(places);
    }

    public Human(String name, int age, ArrayList<Place> places, ArrayList<Item> carry) {
        this.setName(name);
        this.setAge(age);
        this.setPlaces(places);
        this.setCarry(carry);
    }
}
