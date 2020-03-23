package lab3;

import java.util.ArrayList;

public abstract class Creature implements ICreature {
    private int age;
    private String name;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Item> carry = new ArrayList<>();

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

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    boolean hasSkill(Skill skill) {
        for (Skill i : this.skills) {
            if (i.equals(skill)) return true;
        }
        return false;
    }

    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    public ArrayList<Item> getCarry() {
        return carry;
    }

    void addCarry(Item item) {
        carry.add(item);
    }

    void removeCarry(Item item) {
        carry.remove(item);
    }

    public void give(Creature creature, Item item) {
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

    public void look(Creature creature) {
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


