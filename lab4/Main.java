package lab3;

/*
Это снова раздался тот странный искусственный голос, и снова фрекен Бок подпрыгнула на стуле и с
недоверием посмотрела на Малыша. Дядя Юлиус ничего не заметил. Он был так поглощен своими мыслями,
что ничего не слышал и ничего не говорил. Фрекен Бок подала ему кофе. Он протянул, не глядя, руку,
чтобы взять булочку, но сделать этого не сумел, потому что в этот миг из-за стола показалась
маленькая пухлая ручка и потянула корзинку к себе. Но дядя Юлиус и этого не заметил. Он по-прежнему
был всецело погружен в свои мысли и очнулся, только когда сунул в горячий кофе пальцы вместо булочки
и понял, что булочки он так и не взял и макать ему нечего. Он подул на обожженную руку и рассердился.
Но тут же снова углубился в свои мысли.
 */
public class Main {

    public static void main(String[] args) {
        Engineer karlson = new Engineer(32, "Karlson");
        Woman freken = new Woman(40, "Freken Bok");
        Boy boy = new Boy(9, "Eric");
        Swede julius = new Swede(42, "Julius");
        Human josh = new Human(22, "Josh") {
            // local class inside an anonymous class
            class Meet {
                String meet;

                Meet(String meet) {
                    this.meet = meet;
                }

                private void meeting() {
                    System.out.println(meet);
                }
            }

            Meet meet = new Meet("Josh appeared");

            void meeting(Meet meet) {
                meet.meeting();
            }

            {
                meeting(meet);
            }
        };
        Item bun = new Item("bun");
        Item basket = new Item("basket");
        bun.putInto(basket);
        Item coffee = new Item("coffee");
        basket.setCarrier(julius);
        coffee.setCarrier(freken);
        System.out.println("The World is done\n");

        karlson.speak();
        freken.setScared();
        freken.look(boy);
        julius.react();
        julius.speak();
        freken.give(julius, coffee);
        karlson.take(basket);
        julius.take(bun);

        Sing rap = new Sing(Sing.Name.rap);
        freken.addSkill(rap);
        freken.useSkill(rap);
        System.out.println(freken.toString());
        System.out.println(bun.toString());

        karlson.look(josh);
        karlson.useSkill(rap);
    }
}
