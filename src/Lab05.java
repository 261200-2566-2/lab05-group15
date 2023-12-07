/*
All class damage is based on equipped sword's damage cause there is no Bow at this point(want to implement it, but it will cause too much work)
Except Sorceress that need no sword now, but can still equip shield(double attack speed penalty)
Slow effect cause by Sorceress will disappear when character level up, this is not a bug, it's a feature ;p
 */
public class Lab05 {
    public static void main(String[] args) {
        Sword Excalibur = new Sword("Excalibur",100);
        Shield Aegis = new Shield("Aegis",100);
        Gloves Buzzsaw = new Gloves("Buzzsaw",50,20);
        Amulet Mara = new Amulet("Amulet of Mara",30);
        Sword dummySword = new Sword("DummySword",1);
        Warrior John = new Warrior("John",250,Excalibur,Aegis);
        Sorceress Triss = new Sorceress("Triss", 200);
        Archer Aida = new Archer("Aida",210,Excalibur);
        Cleric Jesus = new Cleric("Jesus",100);
        John.showStat();
        Triss.showStat();
        Triss.levelUp(50);
        Triss.showStat();
        //Sorceress Unique Attack
        Triss.magicAttack(John);
        John.equip(Buzzsaw);
        John.showStat();
        Aida.attack(John);
        //Warrior Unique Move
        John.guard();
        Aida.attack(John);
        //Archer Unique Move
        Aida.penetrateAttack(John);
        John.recovery();
        John.showStat();
        Aida.upgrade(Excalibur,300);
        Aida.penetrateAttack(John);
        John.unGuard();
        Aida.penetrateAttack(John);
        Aida.showStat();
        John.recovery();
        Aida.penetrateAttack(John);
        Triss.magicAttack(John);
        //Cleric Unique Move
        Jesus.heal(John);
        John.showStat();
        Jesus.showStat();
        //Sorceress Unique Move
        Triss.slow(John);
        John.showStat();
        John.equip(Mara);
        Triss.magicAttack(John);
    }
}
