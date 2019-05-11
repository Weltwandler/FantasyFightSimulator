import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		GameLogic game = new GameLogic();
		game.characterCreation();
		game.enemySelection();
		game.playersTurn = random.nextBoolean();
		if (game.playersTurn) {
			System.out.println(game.playerChar.getName() + " goes first!");
		} else {
			System.out.println(game.enemy.getMonsterType() + " goes first!");
		}
		while (!game.gameOver) {
			game.gameTurn();
		}
	}

}
