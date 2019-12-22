package lab3;

class Freken extends Human {
    {
        super.setName("Freken Bok");
        super.setAge(40);
        System.out.println(this.getName() + " is here");
    }
    boolean scared = false;

    public boolean isScared() {
        return scared;
    }

    void setScared() {
        this.scared = true;
        System.out.println(this.getName() + " is scared");
    }
}
