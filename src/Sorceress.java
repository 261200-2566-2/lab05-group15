public class Sorceress {
    protected String name;
    protected int level = 1;
    protected double maxHP = 100+10*(level-1);
    protected double hp = maxHP;
    protected Boolean dead = false;
    protected double maxMana = 80+3*(level-1);
    protected double mana = maxMana;
    protected Boolean manaExhausted = false;
    protected double maxAttackSpeed = 30*(0.1+0.03*(level-1));
    protected double attackSpeedPenalty = 0;
    protected double attackSpeed = maxAttackSpeed*((100-attackSpeedPenalty)/100);
    protected double attack = 20+(level*0.5);
    protected double defense;
    protected double magicResistance = 20;
    protected Shield shield;
    protected Boolean shieldEquipped = false;
    protected Ring ring;
    protected Amulet amulet;
    protected Gloves gloves;
    Sorceress(String name,int level){
        this.name = name;
        this.level = level;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Sorceress(String name,int level,Shield shield){
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
        maxMana = 80+3*(level-1);
        maxAttackSpeed = 30*(0.1+0.03*(level-1));
        attackSpeed = maxAttackSpeed*((100-attackSpeedPenalty)/100);
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
        System.out.println("-------------------------------------");
        System.out.println(name + " can't equip sword type weapon");
        System.out.println("-------------------------------------");

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
            attackSpeedPenalty -= 20;
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
        if(shield != null) System.out.println("Shield: " + shield.name);
        if(gloves != null) System.out.println("Gloves: " + gloves.name);
        if(amulet != null) System.out.println("Amulet: " + amulet.name);
        if(ring != null) System.out.println("Ring: " + ring.name);
        System.out.println("-------------------------------------");
    }
    public void magicAttack(Warrior opponent){
        if(!dead && !manaExhausted){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = (20 + (level*0.8))-opponent.magicResistance;
                mana -= 15;
                if(mana <= 0) manaExhausted = true;
                if(damage < 0) damage = 0;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
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
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't attack.");
        }else if(mana <= 15){
            System.out.println(name + " doesn't have enough mana. " + name + " can't attack the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void magicAttack(Archer opponent){
        if(!dead && !manaExhausted){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = 20-opponent.magicResistance;
                mana -= 15;
                if(mana <= 0) manaExhausted = true;
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
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't attack.");
        }else if(mana <= 15){
            System.out.println(name + " doesn't have enough mana. " + name + " can't attack the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void magicAttack(Cleric opponent){
        if(!dead && !manaExhausted){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = 20-opponent.magicResistance;
                mana -= 15;
                if(mana <= 0) manaExhausted = true;
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
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't attack.");
        }else if(mana <= 15){
            System.out.println(name + " doesn't have enough mana. " + name + " can't attack the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void magicAttack(Sorceress opponent){
        if(!dead && !manaExhausted){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = 20-opponent.magicResistance;
                mana -= 15;
                if(mana <= 0) manaExhausted = true;
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
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't attack.");
        }else if(mana <= 15){
            System.out.println(name + " doesn't have enough mana. " + name + " can't attack the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
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
            this.magicAttack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.magicAttack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Archer opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.magicAttack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.magicAttack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Cleric opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.magicAttack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.attack(this);
        }else {
            this.magicAttack(opponent);
            opponent.attack(this);
        }
    }
    public void duel(Sorceress opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.attackSpeed > opponent.attackSpeed) {
            this.magicAttack(opponent);
        }
        else if (opponent.attackSpeed > this.attackSpeed) {
            opponent.magicAttack(this);
        }else {
            this.magicAttack(opponent);
            opponent.magicAttack(this);
        }
    }
    public void slow(Warrior opponent){
        if(!dead && !manaExhausted && mana >= 30){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Slow " + opponent.name + "!");
                mana -= 30;
                if(mana <= 0) manaExhausted = true;
                opponent.attackSpeed -= 20;
                if(opponent.attackSpeed < 0) opponent.attackSpeed = 0;
                System.out.println(opponent.name + "'s remaining Attack Speed is " + opponent.attackSpeed);
                System.out.println("-------------------------------------");
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't slow " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't slow the target.");
        }else if(mana <= 30){
            System.out.println(name + " doesn't have enough mana. " + name + " can't slow the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void slow(Archer opponent){
        if(!dead && !manaExhausted && mana >= 30){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Slow " + opponent.name + "!");
                mana -= 30;
                if(mana <= 0) manaExhausted = true;
                opponent.attackSpeed -= 20;
                if(opponent.attackSpeed < 0) opponent.attackSpeed = 0;
                System.out.println(opponent.name + "'s remaining Attack Speed is " + opponent.attackSpeed);
                System.out.println("-------------------------------------");
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't slow " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't slow the target.");
        }else if(mana <= 30){
            System.out.println(name + " doesn't have enough mana. " + name + " can't slow the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void slow(Cleric opponent){
        if(!dead && !manaExhausted && mana >= 30){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Slow " + opponent.name + "!");
                mana -= 30;
                if(mana <= 0) manaExhausted = true;
                opponent.attackSpeed -= 20;
                if(opponent.attackSpeed < 0) opponent.attackSpeed = 0;
                System.out.println(opponent.name + "'s remaining Attack Speed is " + opponent.attackSpeed);
                System.out.println("-------------------------------------");
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't slow " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't slow the target.");
        }else if(mana <= 30){
            System.out.println(name + " doesn't have enough mana. " + name + " can't slow the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
    public void slow(Sorceress opponent){
        if(!dead && !manaExhausted && mana >= 30){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Slow " + opponent.name + "!");
                mana -= 30;
                if(mana <= 0) manaExhausted = true;
                opponent.attackSpeed -= 20;
                if(opponent.attackSpeed < 0) opponent.attackSpeed = 0;
                System.out.println(opponent.name + "'s remaining Attack Speed is " + opponent.attackSpeed);
                System.out.println("-------------------------------------");
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't slow " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else if(dead){
            System.out.println(name + " is dead. " + name + " can't slow the target.");
        }else if(mana <= 30){
            System.out.println(name + " doesn't have enough mana. " + name + " can't slow the target.");
        }else{
            System.out.println(name + " is in Mana Exhausted State. " + name + " can't attack.");
        }
    }
}
