package lab3;

import java.util.ArrayList;

interface Skill {
    String getName();
    ArrayList<Creature> getUsers();
    void addUser(Creature user);
    void removeUser(Creature user);

}
