
public class PlayerChar extends Character {
	protected String name;
	protected String characterClass;
	protected boolean magic;
	protected int manaPoints;
	protected int initialHP;
	
	public PlayerChar(String name, String characterClass, boolean mag, int hp, int armor, int attack, int damage) {
		this.name = name;
		this.characterClass = characterClass;
		magic = mag;
		if (magic) {
			manaPoints = 30;
		} else {
			manaPoints = 0;
		}
		setHitPoints(hp);
		initialHP = getHitPoints();
		setArmor(armor);
		setAttack(attack);
		setDamage(damage);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCharClass() {
		return characterClass;
	}
	
	public void setCharClass(String charClass) {
		this.characterClass = charClass;
	}
	
	public boolean getMagic() {
		return magic;
	}
	
	public void setMagic(boolean mag) {
		magic = mag;
	}
	
	public int getManaPoints() {
		return manaPoints;
	}
	
	public void setManaPoints(int num) {
		manaPoints = num;
	}
	
	public int getInitialHP() {
		return initialHP;
	}
}
