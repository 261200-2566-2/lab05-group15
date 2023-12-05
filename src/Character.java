public interface Character {
    void recovery();
    void godRevive();
    void updateStat();
    void levelUp(int level);
    void equip(Sword sword);
    void unEquip(Sword sword);
    void equip(Shield shield);
    void unEquip(Shield shield);
    void showStat();
    void upgrade(Sword sword, int level);
    void upgrade(Shield shield, int level);
}
