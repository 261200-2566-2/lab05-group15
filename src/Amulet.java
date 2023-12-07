public class Amulet implements Accessory{
    protected String name;
    protected int magicResistance;
    Amulet(String name, int magicResistance){
        this.name = name;
        this.magicResistance = magicResistance;
    }
    public void showStat() {
        System.out.println("Amulet stat's ");
        System.out.println("Magic Resistance: " + this.magicResistance);
    }
}
