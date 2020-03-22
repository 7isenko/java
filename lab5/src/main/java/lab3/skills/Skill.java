package lab3.skills;

import lab3.Creature;

import java.util.ArrayList;

public interface Skill {
    String getName();
    ArrayList<Creature> getUsers();
    void addUser(Creature user);
}
