import lab5.lab3.Human;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * @author Semisenko Maxim
 */
public class CollectionManager {
    private TreeSet<Human> collection = new TreeSet<Human>();
    private DBRepository db = new DBRepository();
    private Date date = new Date();
    private String email;

    public CollectionManager(String email) {
        this.email = email;
        load();
    }

    /**
     * Fill the collection with all elements
     */
    public String loadAll() {
        collection.addAll(db.getAllHumans());
        return "Collection is updated with humans by all users";
    }
    /**
     * Fill the collection with your elements
     */
    public String load() {
        collection = new TreeSet<>();
        collection.addAll(db.getHumansByUser(email));
        return "Collection is updated with your humans";
    }

    /**
     * Print an information about collection
     * (type, last initialization date, number of elements)
     */
    public String info() {
        return date.toString() +
                "\nType of collection is " + collection.getClass().getName() +
                "\nSize of collection is " + collection.size();
    }

    /**
     * Add an element into collection
     *
     * @param human - element to add
     */
    public String add(Human human) {

        if (human!=null && collection.add(human)) {
            db.addHuman(human, email);
            load();
            return "You added an object to your collection. Print \"show\" to check it";
        } else return "I can't add it";
    }

    protected String add(TreeSet<Human> humans) {

        if (collection.addAll(humans)) {
            db.addHumans(humans, email);
            load();
            return "Your object were added";
        }
        return "Unknown trouble in add method";
    }

    /**
     * Removes object from collection
     *
     * @param human - element to remove
     */
    public String remove(Human human) {
        try {
            collection.remove(human);
            db.removeHuman(human, email);
            return "You removed an object from collection. Print \"show\" to check it";
        } catch (NullPointerException e) {
            return "example: {\"name\":\"Bill\",\"age\":\"42\",\"skills\":[\"fun\"],\"carry\":[\"stick\"]}";
        }
    }

    /**
     * Print the collection
     */
    public String show() {
        StringBuffer answ = new StringBuffer();
        for (Human human : collection) {
            answ.append("Name: ").append(human.getName()).append("\nAge: ").append(human.getAge());
            if (human.getPlace() != null) answ.append("\nPlace: ").append(human.getPlace());
            if (human.getCarry().size() != 0) answ.append("\nCarry: ").append(human.getCarry());
            answ.append("\nOwner: ").append(human.getOwner()).append("\nCreated: ").append(human.getDate().toLocalDateTime().toString().replace("T"," ").substring(0,19));
            answ.append("\n_________\n");
        }
        try {
            collection.first();
        } catch (NoSuchElementException e) {
            answ.append("Collection is empty");
        }
        return answ.toString();
    }

    /**
     * Clear the collection
     */
    public String clear() {
        collection = new TreeSet<>();
        db.removeAllHumans(email);
        return "Collection has been nullified";
    }

    protected TreeSet<Human> get() {
        return collection;
    }

    protected void set(TreeSet<Human> collection) {
        this.collection = collection;
    }

}
