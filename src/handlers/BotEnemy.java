package handlers;

import java.util.Random;

public class BotEnemy {


	public void makeDecision() {

		float decision = randomState() * 100;// / 100;
		System.out.println(decision);

		if (decision < 60f) {

			System.out.println("poruszanie" + decision);

		} else {

			System.out.println("strzal" + decision);
		}

	}

	public float randomState() {
		Random rand = new Random();
		return rand.nextFloat();
	}

	public void stayIdle() {
		System.out.println("IDLE");
	}

	public void turnUp() {
		System.out.println("GORA");
	}

	public void turnDown() {
		System.out.println("DOL");
	}

	public void turnLeft() {
		System.out.println("LEWO");
	}

	public void turnRight() {
		System.out.println("PRAWO");
	}

	public void shoot() {
		System.out.println("Shoot");
	}
}
