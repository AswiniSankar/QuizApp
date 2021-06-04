package com.java.practise;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class PlayQuiz {
	static Connection con = null;

	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			Class.forName("com.mysql.jdbc.Driver");

			String db_url = "jdbc:mysql://localhost:3306/QUIZ";
			String user = "onebill";
			String password = "onebill";
			con = DriverManager.getConnection(db_url, user, password);

			int choice, score = 0;
			int temp = 1;
			System.out.println(".....WELCOME TO THE QUIZ WORLD...");
			while (temp == 1) {
				System.out.println("**********************************************");
				System.out.println("1.play 2.quit");
				choice = s.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Please enter your id,name,");
					int id = s.nextInt();
					s.nextLine();
					String name = s.nextLine();
					score = 0;
					String q1 = "select * from questionpaper";
					stmt = con.createStatement();
					rs = stmt.executeQuery(q1);
					while (rs.next()) {
						int sno = rs.getInt("S_No");
						String qns = rs.getString("qns");
						String op1 = rs.getString("option1");
						String op2 = rs.getString("option2");
						String op3 = rs.getString("option3");
						String op4 = rs.getString("option4");
						int ans = rs.getInt("answer");
						System.out
								.println(sno + ") " + qns + " \n1." + op1 + "\n2." + op2 + "\n3." + op3 + "\n4." + op4 + "\n");
						System.out.println("choose the answer from tha option");
						int option = s.nextInt();
						if (option == ans) {
							score += 2;
						} else {
							score -= 1;
						}

					}
					System.out.println("--------Weldone!! Your Score is " + score + "--------");

					String q2 = "insert into userentry values(?,?,?,?,?)";
					pstmt = con.prepareStatement(q2);
					pstmt.setInt(1, id);
					pstmt.setString(2, name);
					Time t = Time.valueOf(LocalTime.now());
					Date d = Date.valueOf(LocalDate.now());
					pstmt.setDate(3, d);
					pstmt.setTime(4, t);
					pstmt.setInt(5, score);
					pstmt.executeUpdate();
					System.out.println("Thank you For playing....");

					break;
				case 2:
					System.exit(0);
				default:
					System.out.println("invalid");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (stmt != null)
					stmt.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

}
