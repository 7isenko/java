package lab3;

class Woman extends Human {
    boolean scared = false;

    public Woman(int age, String name) {
        super(age, name);
        System.out.println(this.getName() + " is here");
    }

    public boolean isScared() {
        return scared;
    }

    void setScared() {
        this.scared = true;
        System.out.println(this.getName() + " is scared");
    }
}
