package lab3.skills;

import lab3.Creature;

import java.util.ArrayList;

public class Sing implements Skill {
    private ArrayList<Creature> users = new ArrayList<Creature>();
    private Name name;

    public Sing(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.toString();
    }

    public ArrayList<Creature> getUsers() {
        return users;
    }

    public void addUser(Creature user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addSkill(this);
        }
    }
    public static enum Name {
        rap, scream, hip_hop;
    }
}
