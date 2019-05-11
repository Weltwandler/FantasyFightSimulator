
public class Enemy extends Character {
	protected String monsterType;
	
	public Enemy(String type, int hp, int armor, int attack, int damage) {
		monsterType = type;
		setHitPoints(hp);
		setArmor(armor);
		setAttack(attack);
		setDamage(damage);
	}
	
	public String getMonsterType() {
		return monsterType;
	}
	
	public void setMonsterType(String type) {
		monsterType = type;
	}
	
}
