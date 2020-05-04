package lab3;

import lab3.Creature;
import lab3.Item;

public interface ICreature {

    void give(Creature creature, Item item);

    void take(Item item);

    void react();

    void look(Creature creature);
}
