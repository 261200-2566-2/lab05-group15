public class Archer {
    protected String name;
    protected int level = 1;
    protected double maxHP = 100+10*(level-1);
    protected double hp = maxHP;
    protected Boolean dead = false;
    protected double maxMana = 50+2*(level-1);
    protected double mana = maxMana;
    protected double maxAttackSpeed = 30*(0.1+0.03*(level-1));
    protected double attackSpeedPenalty = 0;
    protected double attackSpeed = maxAttackSpeed*((100-attackSpeedPenalty)/100);
    protected double attack;
    protected double defense;
    protected double magicResistance = 10;
    protected double penetration = 10+ (double) (level - 1) /4;
    protected Sword sword;
    protected Boolean swordEquipped = false;
    protected Shield shield;
    protected Boolean shieldEquipped = false;
    protected Ring ring;
    protected Amulet amulet;
    protected Gloves gloves;
    Archer(String name,int level){
        this.name = name;
        this.level = level;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Archer(String name,int level,Sword sword){
        this.name = name;
        this.level = level;
        attack = sword.damage;
        swordEquipped = true;
        this.sword = sword;
        attackSpeedPenalty += 5;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Archer(String name,int level,Shield shield){
        this.name = name;
        this.level = level;
        defense = shield.defense;
        shieldEquipped = true;
        this.shield = shield;
        attackSpeedPenalty += 10;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Archer(String name,int level,Sword sword,Shield shield){
        this.name = name;
        this.level = level;
        attack = sword.damage;
        swordEquipped = true;
        this.sword = sword;
        attackSpeedPenalty += 5;
        defense = shield.defense;
        shieldEquipped = true;
        this.shield = shield;
        attackSpeedPenalty += 10;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    public void recovery(){
        if(hp == maxHP && mana == maxMana){
            System.out.println("-------------------------------------");
            System.out.println(name + " is already at his/her best condition");
            System.out.println("-------------------------------------");
        }else{
            if(!dead){
                System.out.println("-------------------------------------");
                System.out.println(name + " is healed");
                System.out.println("-------------------------------------");
                hp += maxHP*0.3;
                if(hp > maxHP) hp = maxHP;
                mana += maxMana*0.3;
                if(mana > maxMana) mana = maxMana;
            }else{
                System.out.println("-------------------------------------");
                System.out.println("We already lost " + name);
                System.out.println("Nothing can heal " + name);
                System.out.println("-------------------------------------");
            }
        }
    }
    public void godRevive(){
        if(dead){
            System.out.println("-------------------------------------");
            System.out.println(name + " received god's blessing!");
            System.out.println(name + " has come back to life!!!");
            System.out.println("-------------------------------------");
            dead = false;
            hp = maxHP;
            mana = maxMana;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " has wasted god's blessing!");
            System.out.println("-------------------------------------");
        }
    }
    public void updateStat(){
        maxHP = 100+10*(level-1);
        maxMana = 50+2*(level-1);
        maxAttackSpeed = 30*(0.1+0.03*(level-1));
        attackSpeed = maxAttackSpeed*((100-attackSpeedPenalty)/100);
        penetration = 10 + ((double) level /4);
        if(penetration > 100) penetration = 100;
        if(swordEquipped) attack = sword.damage;
        if(shieldEquipped) defense = shield.defense;
    }
    public void levelUp(int level){
        this.level += level;
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " level up!!!");
        System.out.println(name + " is now level " + level);
        System.out.println("-------------------------------------");
        if(!dead) recovery();
    }
    public void equip(Sword sword){
        if(!swordEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is equipping " + sword.name);
            System.out.println("-------------------------------------");
            attack = sword.damage;
            swordEquipped = true;
            this.sword = sword;
            attackSpeedPenalty += 5;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " already equipping " + sword.name);
            System.out.println(name + " can't equip any more sword!");
            System.out.println("-------------------------------------");
        }
    }
    public void unEquip(Sword sword){
        if(swordEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is unequipping " + sword.name);
            System.out.println("-------------------------------------");
            attack = 0.0;
            swordEquipped = false;
            this.sword = null;
            attackSpeedPenalty -= 5;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " isn't equipping any sword!");
            System.out.println("-------------------------------------");
        }
    }
    public void equip(Shield shield){
        if(!shieldEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is equipping " + shield.name);
            System.out.println("-------------------------------------");
            defense = shield.defense;
            shieldEquipped = true;
            this.shield = shield;
            attackSpeedPenalty += 10;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " already equipping " + shield.name);
            System.out.println(name + " can't equip any more shield!");
            System.out.println("-------------------------------------");
        }
    }
    public void unEquip(Shield shield){
        if(shieldEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is unequipping " + shield.name);
            System.out.println("-------------------------------------");
            defense = 0.0;
            shieldEquipped = false;
            this.shield = null;
            attackSpeedPenalty -= 10;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " isn't equipping any shield!");
            System.out.println("-------------------------------------");
        }
    }
    public void equip(Ring ring){
        this.ring = ring;
        this.maxHP += ring.hpAddition;
        updateStat();
    }
    public void equip(Amulet amulet){
        this.amulet = amulet;
        this.magicResistance += this.magicResistance*amulet.magicResistance;
        updateStat();
    }
    public void equip(Gloves gloves){
        this.gloves = gloves;
        this.magicResistance += gloves.magicResistance;
        this.maxAttackSpeed += gloves.attackSpeed;
        updateStat();
    }
    public void showStat(){
        System.out.println("-------------------------------------");
        System.out.println(name + "'s Stat ");
        System.out.println("Level: " + level);
        if(dead){
            System.out.println("HP: " + this.hp + "/" +maxHP + " [DEAD]");
        }else{
            System.out.println("HP: " + this.hp + "/" +maxHP);
        }
        System.out.println("MP: " + this.mana + "/" +maxMana);
        System.out.println("Attack Speed: " + this.attackSpeed + "/" +maxAttackSpeed);
        System.out.println("Damage: " + this.attack);
        System.out.println("Defense: " + this.defense);
        System.out.println("Penetration Bonus: " + this.penetration);
        if(sword != null) System.out.println("Sword: " + sword.name);
        if(shield != null) System.out.println("Shield: " + shield.name);
        if(gloves != null) System.out.println("Gloves: " + gloves.name);
        if(amulet != null) System.out.println("Amulet: " + amulet.name);
        if(ring != null) System.out.println("Ring: " + ring.name);
        System.out.println("-------------------------------------");
    }
    public void attack(Warrior opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-opponent.defense;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void attack(Archer opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-opponent.defense;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void attack(Cleric opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-opponent.defense;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void attack(Sorceress opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-opponent.defense;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void upgrade(Sword sword, int level){
        sword.level += level;
        sword.damage = 15*(1+0.1*(sword.level-1));
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " is upgrading " + sword.name + "!");
        System.out.println(sword.name + "'s level is now " + sword.level + "!!!");
        System.out.println("-------------------------------------");
    }
    public void upgrade(Shield shield, int level){
        shield.level += level;
        shield.defense  = 10*(1+0.05*(shield.level-1));
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " is upgrading " + shield.name + "!");
        System.out.println(shield.name + "'s level is now " + shield.level + "!!!");
        System.out.println("-------------------------------------");
    }
    public void duel(Warrior opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.attack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.attack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Archer opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.attack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.attack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Cleric opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.attack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.attack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Sorceress opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.attack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.magicAttack(this);
        }else {
            this.attack(opponent);
            opponent.magicAttack(this);
        }
    }
    public void penetrateAttack(Warrior opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-(opponent.defense*(100-penetration)/100);
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void penetrateAttack(Archer opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-(opponent.defense*(100-penetration)/100);
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void penetrateAttack(Cleric opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-(opponent.defense*(100-penetration)/100);
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    public void penetrateAttack(Sorceress opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-(opponent.defense*(100-penetration)/100);
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
}
