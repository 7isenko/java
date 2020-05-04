import lab5.SHA1Encoder;
import lab5.lab3.Human;
import lab5.lab3.Item;
import lab5.lab3.Place;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class DBRepository {
    static Connection connection;
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "zxcv";
    // данных для входа на хелиос тут не будет >:D

    public DBRepository() {
        start();
        init();
    }

    public boolean login(String email, String password) {
        try {
            String sql = "select email, password from userlist where email like '" + email + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) return register(email);
            else return rs.getObject("password").equals(SHA1Encoder.encryptPassword(password));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean register(String email) {
        String[] generated = CryptoPasswordGenerator.gen();
        String password = generated[0];
        String encodedPassword = generated[1];
        try {
            addUser(email, encodedPassword);
            //MailSender.sendPassword(email, password);
            System.out.println("password is " + password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void addUser(String email, String encodedPassword) throws SQLException {
        String sql = "insert into userlist (email, password) VALUES (?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, email);
        st.setString(2, encodedPassword);
        st.executeUpdate();
    }


    public boolean addHuman(Human human, String owner) {
        try {
            String sql = "insert into humanlist (name, age, date, place, carry, owner) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, human.getName());
            st.setInt(2, human.getAge());
            st.setString(3, human.getDate().toString());
            st.setString(4, human.getPlace().toString());
            st.setString(5, human.getCarry().toString());
            st.setString(6, owner);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Human> getHumansByUser(String email) {
        ArrayList<Human> humans = new ArrayList<>();
        try {
            String sql = "select * from humanlist where owner like '" + email + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                OffsetDateTime date = OffsetDateTime.parse(rs.getString("date"));
                Place place = Place.valueOf(rs.getString("place"));

                ArrayList<Item> carry = new ArrayList<>();
                String rawArray = rs.getString("carry");
                String[] rawCarry = rawArray.substring(1, rawArray.length() - 1).split(",");
                if (!rawCarry[0].equals(""))
                    for (String item :
                            rawCarry) {
                        carry.add(Item.valueOf(item.trim()));
                    }

                humans.add(new Human(name, age, date, place, carry, email));
            }
            return humans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<Human> getAllHumans() {
        ArrayList<Human> humans = new ArrayList<>();
        try {
            String sql = "select * from humanlist";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                OffsetDateTime date = OffsetDateTime.parse(rs.getString("date"));
                Place place = Place.valueOf(rs.getString("place"));

                ArrayList<Item> carry = new ArrayList<>();
                String rawArray = rs.getString("carry");
                String[] rawCarry = rawArray.substring(1, rawArray.length() - 1).split(",");
                if (!rawCarry[0].equals(""))
                    for (String item :
                            rawCarry) {
                        carry.add(Item.valueOf(item.trim()));
                    }

                String owner = rs.getString("owner");
                humans.add(new Human(name, age, date, place, carry, owner));
            }
            return humans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addHumans(TreeSet<Human> humans, String owner) {
        humans.forEach(human -> addHuman(human, owner));
    }

    public boolean removeHuman(Human human, String owner) {
        try {
            String sql = "delete from humanlist where name=? and age=? and place=? and carry=? and owner=?"; // I ignore date, cuz it makes some problems
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, human.getName());
            st.setInt(2, human.getAge());
            st.setString(3, human.getPlace().toString());
            st.setString(4, human.getCarry().toString());
            st.setString(5, owner);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeAllHumans(String owner) {
        try {
            String sql = "delete from humanlist where owner=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, owner);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void start() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    private void init() {
        try {
            Statement st = connection.createStatement();
            String sql = "create table if not exists userlist\n" +
                    "(\n" +
                    "    email varchar(255),\n" +
                    "    password varchar(255)\n" +
                    ");";
            st.executeUpdate(sql);

            Statement st2 = connection.createStatement();
            String sql2 = "create table if not exists humanlist\n" +
                    "(\n" +
                    "    name varchar(255),\n" +
                    "    age int,\n" +
                    "    date varchar(255),\n" +
                    "    place varchar(255),\n" +
                    "    carry varchar(255),\n" +
                    "    owner varchar(255)\n" +
                    ");";
            st2.executeUpdate(sql2);
            System.out.println("DB initiated");
        } catch (SQLException e) {
            System.out.println("DB initiated with exceptions");
            e.printStackTrace();
        }
    }

    //TODO: не забыть
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
