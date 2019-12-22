package lab3;

import java.util.ArrayList;

public class Sing implements Skill {
    private ArrayList<Creature> users = new ArrayList<Creature>();
    private String name;

    Sing(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Creature> getUsers() {
        return users;
    }

    public void addUser(Creature user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addSkill(this);
        }
    }
}
