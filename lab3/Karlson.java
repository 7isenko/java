package lab3;

class Karlson extends Human {
    {
        super.setName("Karlson");
        super.setAge(33);
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
