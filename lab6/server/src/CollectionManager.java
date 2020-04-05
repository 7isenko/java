import lab5.JSONReader;
import lab5.JSONWriter;
import lab5.lab3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * @author Semisenko Maxim
 */
public class CollectionManager {
    private TreeSet<Human> collection = new TreeSet<Human>();
    private JSONReader reader = new JSONReader();
    private JSONWriter writer = new JSONWriter();
    private Date date = new Date();
    private String email;

    public CollectionManager(String email) {
        this.email = email;
    }

    /**
     * Update collection from file
     */
    public String load() {
        reader.readFile();
        return "Collection was updated from your file";
    }

    /**
     * Save collection to file
     */
    public String save() {
        writer.fill(collection);
        writer.writeToFile();
        return "Collection has been saved to " + writer.getFileAddress();
    }

    /**
     * Print an information about collection
     * (type, initialization date, number of elements, etc.)
     */
    public String info() {
        return date.toString() +
                "\nType of collection is " + collection.getClass().getName() +
                "\nSize of collection is " + collection.size();
    }

    /**
     * Add an element into collection
     *
     * @param objInString - element in string JSON format
     */
    public String add(String objInString) {
        JSONObject obj;
        try {
            obj = new JSONObject(objInString);
            if (collection.add(reader.parseHuman(obj)))
                return "You added an object to your collection. Print \"show\" to check it";
        } catch (JSONException e) {
            return "That was invalid JSON";
        } catch (NullPointerException e) {
            return "example: {\"name\":\"Bill\",\"age\":\"42\",\"place\":[\"zoo\"],\"carry\":[\"stick\"]}";
        }
        return "Unknown trouble in add method";
    }

    /**
     * Imports collection from file
     *
     * @param path - string path to a file
     */
    public String doImport(String path) {
        if (!path.substring(path.lastIndexOf('.') + 1).equals("json"))
            return "It is not .json format file";
        else
            try {
                reader.setFileAddress(path);
                reader.getHumanCollection();
                return "Collection was successfully imported. Print \"show\" to check it";
            } catch (NullPointerException e) {
                return "Wrong file address: " + path;
            }
    }

    /**
     * Removes object from collection
     *
     * @param objInString - element in string JSON format
     */
    public String remove(String objInString) {
        try {
            JSONObject obj = new JSONObject(objInString);
            collection.remove(reader.parseHuman(obj)); // check return
            return "You removed an object from collection. Print \"show\" to check it";
        } catch (JSONException e) {
            return "It is not a JSON";
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
        return "Collection has been nullified";
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
        carry.add(Item.values()[new Random().nextInt(Item.values().length)]);
        ArrayList<Place> places = new ArrayList<>();
        Place place = (Place.values()[new Random().nextInt(Place.values().length)]);
        collection.add(new Human("Amanda", 26, place, carry));
    }
}
