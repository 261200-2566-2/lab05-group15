public class Ring implements Accessory{
    protected String name;
    protected int hpAddition;
    Ring(String name, int hpAddition){
        this.name = name;
        this.hpAddition = hpAddition;
    }
    public void showStat() {
        System.out.println("Ring stat's ");
        System.out.println("Hp: " + this.hpAddition);
    }
}
