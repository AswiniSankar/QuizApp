package com.java.practise;

import java.util.Scanner;

class Quiz {
	String[] qns = { "Who is the first women president of India?", "What is Tmail nadu's state animal?" };
 
	void displayQuiz(int i) {

		System.out.println(qns[i]);

	}
}

class Option {
	String[] op = { "1.Indira Gandhi\n2.Pradepa Patel\n3.Sarojini Naidu\n4.Mayavadhi",
			"1.Tiger\n2.Lion\n3.Nilgiri Tar\n4.Deer" };

	void displayOption(int i) {
		System.out.println(op[i]);
	}
}

class Answer {
	int[] ans = { 2, 3 };

	boolean check(int val, int i) {
		if (val == ans[i])
			return true;
		else
			return false;
	}

}

class QuizApp {

	public static void main(String[] args) {

		Quiz q = new Quiz();
		Option op = new Option();
		Answer a = new Answer();
		int score = 0, ans;
		System.out.println("Welcome to the QUIZZZZ....");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		while (true) {
			score = 0;
			System.out.println("1.to play 2.quit \n enter your option");
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				for (int i = 0; i < 2; i++) {
					q.displayQuiz(i);
					op.displayOption(i);
					System.out.println("Enter Your Option");
					ans = s.nextInt();
					if (a.check(ans, i) == true) {
						score += 2;
					}

				}
				System.out.println("your score is..." + score);

				break;
			case 2:
				System.exit(score);
			default:
				System.out.println("invalid choice");
				break;
			}
		}
	}

}
