package lab3;

class Boy extends Human {
    public Boy(int age, String name) {
        super(age, name);
        System.out.println(this.getName() + " is here");
    }
}
