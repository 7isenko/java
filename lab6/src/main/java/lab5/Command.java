package lab5;

import java.io.Serializable;

public class Command implements Serializable {
    private final String name;
    private final Object body;

    public Command(String name) {
        this.name = name;
        this.body = null;
    }

    public Command(String name, Object body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public Object getBody() {
        return body;
    }
}
