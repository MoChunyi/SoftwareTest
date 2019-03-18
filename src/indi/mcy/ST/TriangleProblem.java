package indi.mcy.ST;

import java.util.Scanner;

public class TriangleProblem {
	
	public static void main(String[] args) {
		System.out.println("Enter an number: ");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		if (triangleProblem(input)) {
			System.out.println("Can take out "+ input);
		} else {
			System.out.println("Can not take out " + input);
		}
		
	}
	
	public static boolean triangleProblem(int input) {
		int theAllMoney = 50 + 20 + 2 * 5 + 3 * 1;
		if (input > theAllMoney) return false;
		int rest;
		if (input / 50 > 1) return false;
		rest = input % 50;
		if (rest / 20 > 1) return false;
		rest = rest % 20;
		if (rest > 13) return false;
		if (rest % 5 > 3) return false;
		return true;
	}
}


