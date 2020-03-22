package lab3;

public interface ICreature {

    void give(Creature creature, Item item);

    void take(Item item);

    void react();

    void look(Creature creature);
}
