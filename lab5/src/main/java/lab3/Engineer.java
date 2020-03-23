package lab3;

public class Engineer extends Human {

    @Override
    public void take(Item item) {
        if (item.isCarried()) {
            item.setCarrier(this);
            System.out.println(this.getName() + " stole the " + item + "\nbugaga");
        } else {
            item.setCarrier(this);
            System.out.println(this.getName() + " took the " + item);
        }
    }
}
