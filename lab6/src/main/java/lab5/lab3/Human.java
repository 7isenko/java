package lab5.lab3;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public class Human implements Comparable<Human>, Serializable {
    private int age;
    private String name;
    private OffsetDateTime date = OffsetDateTime.now();
    private Place place = Place.SPAWN;
    private ArrayList<Item> carry = new ArrayList<>();

    public void speak() {
        System.out.println(this.getName() + " said something");
    }

    public int compareTo(Human o) {
        int result = this.getName().compareTo(o.getName());
        if (result == 0) result = this.getAge().compareTo(o.getAge());
        if (result == 0) result = this.getPlace().compareTo(o.getPlace());
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

    public Human(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public Human(String name, int age, OffsetDateTime date) {
        this.setName(name);
        this.setAge(age);
        this.setDate(date);
    }

    public Human(String name, int age, Place place, ArrayList<Item> carry) {
        this.setName(name);
        this.setAge(age);
        this.setPlace(place);
        this.setCarry(carry);
    }

    public Human(String name, int age, OffsetDateTime date, Place place, ArrayList<Item> carry) {
        this.setName(name);
        this.setAge(age);
        this.setDate(date);
        this.setPlace(place);
        this.setCarry(carry);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


    public ArrayList<Item> getCarry() {
        return carry;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }


    void addCarry(Item item) {
        carry.add(item);
    }

    void removeCarry(Item item) {
        carry.remove(item);
    }

    public void give(Human creature, Item item) {
        item.setCarrier(creature);
        System.out.println(this.getName() + " gives " + item + " to " + creature.getName());
    }

    public void take(Item item) {
        if (item.isCarried()) {
            System.out.println(this.getName() + " tried to take the " + item + " but failed");
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item);
        }
    }

    public void look(Human creature) {
        System.out.println(this.name + " looked at " + creature.getName());
    }

    public void react() {
        System.out.println(this.name + " is looking on you");
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return object.getClass() == getClass() && object.hashCode() == hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + getName() + " " + hashCode();
    }

    public void setCarry(ArrayList<Item> carry) {
        this.carry = carry;
    }
}
