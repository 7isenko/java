import lab3.Human;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.TreeSet;

public class JSONWriter {
    private JSONArray humanList = new JSONArray();
    private String fileAddress = "./collections/default.json";

    public void fill(TreeSet<Human> collection) {
        collection.forEach(this::addHuman);
    }

    public void addHuman(Human h) {
        JSONObject human = new JSONObject();
        human.put("name", h.getName());
        human.put("age", h.getAge());
        human.put("skills", h.getSkills());
        human.put("carry", h.getCarry());

        humanList.put(human);
    }

    public String writeToFile() {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileAddress));
            byte[] buffer = humanList.toString().getBytes();
            out.write(buffer, 0, buffer.length);
            out.close();
        } catch (FileNotFoundException e) {
            return "Can't find the file: " + fileAddress;
        } catch (IOException e) {
            return "Output error with file: " + fileAddress;
        }
        return "File saved in " + fileAddress;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
}
