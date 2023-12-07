public class Sword {
    String name;
    int level = 1;
    double damage = 15*(1+0.1*(level-1));
    Sword(String name){
        this.name = name;
    }
    Sword(String name, int level){
        this.name = name;
        this.level = level;
        this.damage = 15*(1+0.1*(level-1));
    }
    void showStat(){
        System.out.println("-------------------------------------");
        System.out.println(name + "'s Stat");
        System.out.println("Level: " + level);
        System.out.println("Damage: " + damage);
        System.out.println("-------------------------------------");
    }
}
