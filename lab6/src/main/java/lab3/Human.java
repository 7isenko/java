package lab3;

import java.util.ArrayList;
import java.util.Arrays;

public class Human extends Creature implements Comparable<Human> {

    public void speak() {
        System.out.println(this.getName() + " said something");
    }

    public int compareTo(Human o) {
        int result = this.getName().compareTo(o.getName());
        if (result == 0) result = this.getAge().compareTo(o.getAge());
        if (result == 0)
            if (!this.getSkills().equals(o.getSkills())) {
                ArrayList<Skill> h = this.getSkills();
                h.removeAll(o.getSkills());
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
