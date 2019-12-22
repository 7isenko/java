package lab3;

import java.util.ArrayList;

public abstract class Creature implements ICreature {
    private int age;
    private String name;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Item> carry = new ArrayList<>();

    public int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    boolean hasSkill(String skill) {
        for (Skill i : this.skills) {
            if (i.getName().equals(skill)) return true;
        }
        return false;
    }

    void useSkill(Skill skill) {
        if (this.skills.contains(skill)) {
            System.out.println(this.name + " is " + skill.getName() + "ing");
        }
    }

    void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            skill.addUser(this);
        }
    }

    ArrayList<Item> getCarry() {
        return carry;
    }

    void addCarry(Item item) {
        carry.add(item);
    }

    void removeCarry(Item item) {
        carry.remove(item);
    }

    @Override
    public void give(Creature creature, Item item) {
        item.setCarrier(creature);
        System.out.println(this.getName() + " gives " + item.getName() + " to " + creature.getName());
    }

    @Override
    public void take(Item item) {
        if (item.isCarried()) {
            System.out.println(this.getName() + " tried to take the " + item.getName() + " but failed");
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item.getName());
        }
    }

    @Override
    public void look(Creature creature) {
        System.out.println(this.getName() + " is looked at " + creature.getName());
    }

    public void react() {
        System.out.println(this.getName() + " is looking on you");
    }
}
