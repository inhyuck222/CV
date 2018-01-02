import java.util.Arrays;
import java.util.Scanner;

public class Main {
	Scanner sc;
	int[][] mapTopDown, mapLeftRight;
	int[] slope;
	int slopeIndex;
	boolean[][] isSlope;
	boolean isOkay;
	int n;
	int sHeight, sLength, result, elseLength;
	int tempRow, tempCol;
	int before;

	public int checkMap(int[][] map) {
		int tempLength;
		int ans = 0;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (col + 1 >= n) {
					break;
				}
				tempLength = 0;
				if (map[row][col] - map[row][col + 1] > 1 || map[row][col] - map[row][col + 1] < -1) {
					isOkay = false;
					break;
				} else if (map[row][col] - map[row][col + 1] == 1) {
					before = map[row][col + 1];
					tempCol = col + 1;
					while (tempLength < sLength) {
						try {
							if (before == map[row][tempCol]) {
								isOkay = true;
								isSlope[row][tempCol] = true;
							} else {
								isOkay = false;
							}
							before = map[row][tempCol];
							tempCol++;
							tempLength++;
						} catch (ArrayIndexOutOfBoundsException e) {
							isOkay = false;
							break;
						}
					}
				} else if (map[row][col] - map[row][col + 1] == -1) {
					before = map[row][col];
					tempCol = col;
					while (tempLength < sLength) {
						try {
							if (before == map[row][tempCol]) {
								if (isSlope[row][tempCol] == false) {
									isOkay = true;
									isSlope[row][tempCol] = true;
								} else {
									isOkay = false;
									break;
								}
							} else {
								isOkay = false;
								break;
							}
							before = map[row][tempCol];
							tempCol--;
							tempLength++;
						} catch (ArrayIndexOutOfBoundsException e) {
							isOkay = false;
							break;
						}
					}
				} else if (map[row][col] - map[row][col + 1] == 0) {
					isOkay = true;
				}
				if (isOkay == false) {
					break;
				}
			}
			//System.out.println(isOkay);
			if (isOkay) {
				ans++;
			}
		}
		return ans;
	}

	public void solve() {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		sLength = sc.nextInt();
		sHeight = 1;
		mapTopDown = new int[n][n];
		mapLeftRight = new int[n][n];
		isSlope = new boolean[n][n];
		slope = new int[sLength];
		slopeIndex = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mapLeftRight[i][j] = sc.nextInt();
				mapTopDown[j][i] = mapLeftRight[i][j];
			}
		}
		/*
		System.out.println();

		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(mapTopDown[i]));
		}

		System.out.println(checkMap(mapTopDown));
		for (int i = 0; i < n; i++) {
			Arrays.fill(isSlope[i], false);
			System.out.println(Arrays.toString(mapLeftRight[i]));
		}
		System.out.println(checkMap(mapLeftRight));
		*/
		result += checkMap(mapTopDown);
		for (int i = 0; i < n; i++) {
			Arrays.fill(isSlope[i], false);
		}
		result += checkMap(mapLeftRight);
		System.out.println(result);

	}

	public static void main(String[] args) {
		Main m = new Main();
		m.solve();
	}

}
