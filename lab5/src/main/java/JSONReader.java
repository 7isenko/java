import lab3.Human;
import lab3.Item;
import lab3.Skill;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class JSONReader {
    private JSONArray fileJSONContent;
    private String fileContent = "";
    private String fileAddress = "./collections/default.json";


    public String readFile() {
        fileJSONContent = null;
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileAddress));
            int c;
            while ((c = in.read()) != -1) {
                fileContent += (char) c;
            }
            if (fileContent.equals("")) return "File is empty: " + fileAddress;
            fileJSONContent = new JSONArray(fileContent);
        } catch (FileNotFoundException e) {
            return "Can't find the file " + fileAddress;
        } catch (IOException e) {
            return "Input error with the file " + fileAddress;
        } catch (JSONException e) {
            try {
                fileJSONContent = new JSONArray(new JSONObject(fileContent));
            } catch (JSONException ee) {
                return "Whoops, file by address " + fileAddress + " is not in JSON format";
            }
            return e.getMessage();
        }
        return "Successfully read " + fileAddress;
    }

    public TreeSet<Human> getHumanCollection() {
        TreeSet<Human> collection = new TreeSet<Human>();
        fileJSONContent.forEach(human -> collection.add(parseHuman((JSONObject) human))); // alert lambda function
        return collection;
    }

    public TreeSet<Human> addHumanCollection(TreeSet<Human> collection) {
        fileJSONContent.forEach(human -> collection.add(parseHuman((JSONObject) human))); // alert lambda function
        return collection;
    }

    public Human parseHuman(JSONObject jsonHuman) {
        Human human = new Human();

        try {
            human.setName(jsonHuman.getString("name"));
            human.setAge(jsonHuman.getInt("age"));
            try {
                human.setSkills(jsonToALSConvert((JSONArray) jsonHuman.get("skills")));
            } catch (JSONException e) {
                human.setSkills(new ArrayList<Skill>());
            }
            try {
                human.setCarry(jsonToALIConvert((JSONArray) jsonHuman.get("carry")));
            } catch (JSONException e) {
                human.setCarry(new ArrayList<Item>());
            }
        } catch (Exception e) {
            System.out.println("You put wrong values");
        }

        return human;
    }

    public ArrayList<Skill> jsonToALSConvert(JSONArray jsonArray) {
        ArrayList<Skill> arrayList = new ArrayList<Skill>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(Skill.valueOf(((String)jsonArray.get(i)).toUpperCase()));
            }
        }
        return arrayList;
    }

    public ArrayList<Item> jsonToALIConvert(JSONArray jsonArray) {
        ArrayList<Item> arrayList = new ArrayList<Item>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(Item.valueOf(((String)jsonArray.get(i)).toUpperCase()));
            }
        }
        return arrayList;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
        readFile();
    }

    public JSONArray getFileJSONContent() {
        return fileJSONContent;
    }
}
