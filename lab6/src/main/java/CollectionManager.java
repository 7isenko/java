import lab3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * @author Semisenko Maxim
 */
public class CollectionManager {
    private TreeSet<Human> collection = new TreeSet<Human>();
    private JSONReader reader = new JSONReader();
    private JSONWriter writer = new JSONWriter();
    private Date date = new Date();

    /**
     * Auto method, loads elements from file on the start
     */
    protected void setOnLoad() {
        try {
            System.out.println(reader.readFile());
            collection = reader.getHumanCollection();
        } catch (NullPointerException e) {
            System.out.println("Collection is empty. It uses directory: " + reader.getFileAddress());
        }
    }

    /**
     * Update collection from file
     */
    public void load() {
        reader.readFile();
        System.out.println("Collection was updated from your file");
    }

    /**
     * Save collection to file
     */
    public void save() {
        writer.fill(collection);
        writer.writeToFile();
        System.out.println("Collection has been saved to " + writer.getFileAddress());
    }

    /**
     * Print an information about collection
     * (type, initialization date, number of elements, etc.)
     */
    public void info() {
        System.out.println(date.toString());
        System.out.println("Type of collection is " + collection.getClass().getName());
        System.out.println("Size of collection is " + collection.size());
    }

    /**
     * Add an element into collection
     *
     * @param objInString - element in string JSON format
     */
    public void add(String objInString) {
        JSONObject obj;
        try {
            obj = new JSONObject(objInString);
            if (collection.add(reader.parseHuman(obj)))
                System.out.println("You added an object to your collection. Print \"show\" to check it");
        } catch (JSONException e) {
            System.out.println("That was invalid JSON");
        } catch (NullPointerException e) {
            System.out.println("example: {\"name\":\"Bill\",\"age\":\"42\",\"skills\":[\"fun\"],\"carry\":[\"stick\"]}");
        }
    }

    /**
     * Imports collection from file
     *
     * @param path - string path to a file
     */
    public void doImport(String path) {
        if (!path.substring(path.lastIndexOf('.') + 1).equals("json"))
            System.out.println("It is not .json format file");
        else
            try {
                reader.setFileAddress(path);
                reader.getHumanCollection();
                System.out.println("Collection was successfully imported. Print \"show\" to check it");
            } catch (NullPointerException e) {
                System.out.println("Wrong file address: " + path);
            }
    }

    /**
     * Removes object from collection
     *
     * @param objInString - element in string JSON format
     */
    public void remove(String objInString) {
        try {
            JSONObject obj = new JSONObject(objInString);
            collection.remove(reader.parseHuman(obj)); // check return
            System.out.println("You removed an object from collection. Print \"show\" to check it");
        } catch (JSONException e) {
            System.out.println("It is not a JSON");
        } catch (NullPointerException e) {
            System.out.println("example: {\"name\":\"Bill\",\"age\":\"42\",\"skills\":[\"fun\"],\"carry\":[\"stick\"]}");
        }
    }

    /**
     * Print the collection
     */
    public void show() {
        collection.forEach((human -> {
            System.out.println("Name: " + human.getName() + "\nAge: " + human.getAge());
            if (human.getSkills().size() != 0) System.out.println("Skills: " + human.getSkills());
            if (human.getCarry().size() != 0) System.out.println("Carry: " + human.getCarry());
            System.out.println("_________");
        }));
        try {
            collection.first();
        } catch (NoSuchElementException e) {
            System.out.println("Collection is empty");
        }
    }

    /**
     * Clear the collection
     */
    public void clear() {
        collection = new TreeSet<>();
        System.out.println("Collection has been nullified");
    }

    protected TreeSet<Human> get() {
        return collection;
    }

    protected void set(TreeSet<Human> collection) {
        this.collection = collection;
    }

    /**
     * Method, which is used for small tests
     */
    protected void test() {
        ArrayList<Item> carry = new ArrayList<>();
        carry.add(Item.BOTTLE);
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(Skill.SING);
        collection.add(new Human("Amanda", 26, skills, carry));
    }
}
