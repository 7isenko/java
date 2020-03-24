package lab3;

public class Woman extends Human {
    boolean scared = false;

    public boolean isScared() {
        return scared;
    }

    public void setScared() {
        this.scared = true;
        System.out.println(this.getName() + " is scared");
    }
}
