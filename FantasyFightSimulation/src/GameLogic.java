import java.util.Scanner;
import java.util.Random;

public class GameLogic {
	boolean playersTurn;
	Magic currentSpell;
	PlayerChar playerChar;
	Enemy enemy;
	int actionSelection;
	int round;
	public boolean gameOver = false;
	int currentHP;
	int currentAttack;
	int currentArmor;
	int currentDamage;
	
	Scanner scanner = new Scanner(System.in);
	Random random = new Random();
	
	public void characterCreation() {
		System.out.println("Please enter a name for your character: ");
		String name = scanner.next();
		while (true) {
			System.out.println("Please select a class. Available choices are:\n1. Warrior\n2. Rogue\n3. Wizard");
			int choice = scanner.nextInt();
			if (choice == 1) {
				playerChar = new PlayerChar(name, "Warrior", false, 30, 15, 5, 7);
				break;
			} else if (choice == 2) {
				playerChar = new PlayerChar(name, "Rogue", false, 25, 12, 5, 10);
				break;
			} else if (choice == 3) {
				playerChar = new PlayerChar(name, "Wizard", true, 20, 10, 3, 5);
				break;
			} else {
				System.out.println("Invalid Selection. Please choose again.");
			}
		}
	}
	
	public void enemySelection() {
		System.out.println("And what enemy would you like to fight today? ");
		while (true) {
			System.out.println("Options are:\n1. Orc\n2. Goblin\n3. Dragon\n4. Bandit\n5. A random enemy");
			int choice = scanner.nextInt();
			if (choice == 5) {
				choice = random.nextInt(4) + 1;
			}
			if (choice == 1) {
				enemy = new Enemy("Orc", 30, 14, 5, 7);
				System.out.println("You will fight an " + enemy.getMonsterType());
				break;
			} else if (choice == 2) {
				enemy = new Enemy("Goblin", 20, 12, 2, 4);
				System.out.println("You will fight a " + enemy.getMonsterType());
				break;
			} else if (choice == 3) {
				enemy = new Enemy("Dragon", 50, 17, 0, 10);
				System.out.println("You will fight a " + enemy.getMonsterType() + "! Be careful!");
				break;
			} else if (choice == 4) {
				enemy = new Enemy("Bandit", 25, 13, 3, 5);
				System.out.println("You will fight a " + enemy.getMonsterType());
				break;
			} else {
				System.out.println("Invalid Selection. Please choose again.");
			}
				
		}
		
	}
	
	public void actionSelect() {
		while (true) {
			System.out.println("Please select an action.");
			System.out.println("Options are:\n1. Cautious Attack\n2. Normal Attack\n3. Aggressive Attack");
			if (playerChar.getMagic()) {
				System.out.println("4. Cast Spell");
			}
			int choice = scanner.nextInt();
			if (choice < 1 || choice > 4 || (choice == 4 && playerChar.getMagic() == false)) {
				System.out.println("Invalid Selection. Please choose again");
			} else {
				actionSelection = choice;
				break;
			}
		}
	}
	
	public void displayStats() {
		System.out.println("Your current Hit Points are " + playerChar.getHitPoints());
		if (playerChar.getMagic()) {
			System.out.println("Your current Mana points are " + playerChar.getManaPoints());
		}
		System.out.println("The " + enemy.getMonsterType() + "'s hit points are " + enemy.getHitPoints() + "\n");
	}
	
	public void victoryCheck() {
		if (playerChar.getHitPoints() < 1) {
			gameOver = true;
			System.out.println(playerChar.getName() + " was defeated by the " + enemy.getMonsterType() + ". Better luck next time!");
		}
		if (enemy.getHitPoints() < 1) {
			gameOver = true;
			System.out.println(playerChar.getName() + " has defeated the " + enemy.getMonsterType() + ". Victory is yours!");
		}
	}
	
	public int diceRollTwenty() {
		return random.nextInt(20) + 1;
	}
	
	public int diceRollFive() {
		return random.nextInt(5) - 2;
	}
	
	public void attackCautious() {
		int attackRoll = diceRollTwenty() + currentAttack + 4;
		if (attackRoll >= currentArmor) {
			int attackDamage = currentDamage + diceRollFive() -1;
			System.out.println("The attack hits for " + attackDamage + " damage.\n");
			currentHP -= attackDamage; 
		} else {
			System.out.println("The attack fails.\n");
		}
		
	}
	public void attackNormal() {
		int attackRoll = diceRollTwenty() + currentAttack;
		if (attackRoll >= currentArmor) {
			int attackDamage = currentDamage + diceRollFive();
			System.out.println("The attack hits for " + attackDamage + " damage.");
			currentHP -= attackDamage;
		} else {
			System.out.println("The attack fails.");
		}
	}
	
	public void attackReckless() {
		int attackRoll = diceRollTwenty() + currentAttack - 2;
		if (attackRoll >= currentArmor) {
			int attackDamage = currentDamage + diceRollFive() + 1;
			System.out.println("The attack hits for " + attackDamage + " damage.");
			currentHP -= attackDamage;
		} else {
			System.out.println("The attack fails.");
		}
	}
	
	public void castSpell() {
		while (true) {
			System.out.println("Please choose a spell:\n1. Fireball\n2. Lightning\n3. Healing Hands");
			int spellSelect = scanner.nextInt();
			if (spellSelect < 1 || spellSelect > 3) {
				System.out.println("Invalid selection. Please chose again.");
				continue;
			} else if (spellSelect == 1) {
				currentSpell = new Magic("Fireball", 10, false, 10 + diceRollFive());
				break;
			} else if (spellSelect == 2) {
				currentSpell = new Magic("Lightning", 5, false, 5 + diceRollFive() + diceRollFive());
				break;
			} else if (spellSelect == 3) {
				currentSpell = new Magic("Healing Hands", 5, true, 10);
				break;
			}
		}
			System.out.println("Selected spell: " + currentSpell.name);
			if (currentSpell.heal == true) {
				playerChar.setHitPoints(playerChar.getHitPoints() + currentSpell.effect);
				if (playerChar.getHitPoints() > playerChar.getInitialHP()) {
					System.out.println(currentSpell.name + " heals you for " + (currentSpell.effect - (playerChar.getHitPoints() - playerChar.getInitialHP())) + " hit points");
					playerChar.setHitPoints(playerChar.getInitialHP());
				} else {
					System.out.println(currentSpell.name + " heals you for " + currentSpell.effect + " hit points");
				}
			} else {
				System.out.println(currentSpell.name + " hits " + enemy.getMonsterType() + " for " + currentSpell.effect + " damage.");
				currentHP -= currentSpell.effect;
			}
			playerChar.setManaPoints(playerChar.getManaPoints() - currentSpell.cost);
				
	}
	
	public void gameTurn() {
		if (playersTurn) {
			currentHP = enemy.getHitPoints();
			currentAttack = playerChar.getAttack();
			currentArmor = enemy.getArmor();
			currentDamage = playerChar.getDamage();
			
			actionSelect();
			if (actionSelection == 1) {
				attackCautious();
			} else if (actionSelection == 2) {
				attackNormal();
			} else if (actionSelection == 3) {
				attackReckless();
			} else if (actionSelection == 4) {
				castSpell();
			} else {
				System.out.println("Invalid action selected!");
			}
			
			enemy.setHitPoints(currentHP);
			
			victoryCheck();
			if (!gameOver) {
				displayStats();
			}
			
			playersTurn = false;
		} else {
			currentHP = playerChar.getHitPoints();
			currentAttack = enemy.getAttack();
			currentArmor = playerChar.getArmor();
			currentDamage = enemy.getDamage();
			
			System.out.println(enemy.getMonsterType() + " attacks!\n");
			
			attackNormal();
			
			playerChar.setHitPoints(currentHP);
			
			victoryCheck();
			if (!gameOver) {
				displayStats();
			}
			
			playersTurn = true;
		}
	}
}
