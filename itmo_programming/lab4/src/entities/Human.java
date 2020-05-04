package lab3;

class Human extends Creature {

    public Human(int age, String name) {
        super(age, name);
    }

    void speak() {
        System.out.println(this.getName() + " said something");
    }
}
