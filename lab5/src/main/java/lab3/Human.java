package lab3;

import lab3.skills.Skill;

import java.util.ArrayList;

public class Human extends Creature implements Comparable<Human> {

    public void speak() {
        System.out.println(this.getName() + " said something");
    }

    public int compareTo(Human o) {
        int result = this.getName().compareTo(o.getName());
        if (result == 0) result = this.getAge().compareTo(o.getAge());
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
    public Human(String name, int age, ArrayList<Skill> skills) {
        this.setName(name);
        this.setAge(age);
        this.setSkills(skills);
    }

    public Human(String name, int age, ArrayList<Skill> skills, ArrayList<Item> carry) {
        this.setName(name);
        this.setAge(age);
        this.setSkills(skills);
        this.setCarry(carry);
    }
}
