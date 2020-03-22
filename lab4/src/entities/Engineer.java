package lab3;

class Engineer extends Human {
    public Engineer(int age, String name) {
        super(age, name);
        System.out.println(this.getName() + " is here (hiding)");
    }

    @Override
    public void take(Item item) {
        if (item.isCarried()) {
            item.setCarrier(this);
            System.out.println(this.getName() + " stole the " + item.getName() + "\nbugaga");
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item.getName());
        }
    }
}
