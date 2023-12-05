public class Shield {
    String name;
    int level = 1;
    double defense  = 10*(1+0.05*level);
    Shield(String name){
        this.name = name;
    }
    Shield(String name, int level){
        this.name = name;
        this.level = level;
        this.defense  = 10*(1+0.05*(level-1));
    }
    void showStat(){
        System.out.println("-------------------------------------");
        System.out.println(name + "'s Stat");
        System.out.println("Level: " + level);
        System.out.println("Defense: " + defense);
        System.out.println("-------------------------------------");
    }
}
