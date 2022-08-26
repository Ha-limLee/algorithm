import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;

// BOJ 10026 적록색약
class Main {
	static Util ut = new Util();
	static class Util {
		int[][] delta = {
				{-1, 0}, {0, 1},
				{1, 0}, {0, -1}
		};
		char[][] copy(char[][] o) {
			return Arrays.stream(o).map(x -> x.clone()).toArray(char[][]::new);
		}
		void mark(int i, int j, char[][] map, Predicate<Character> p) {
			if (!inbound(i, j, map) || map[i][j] == 'v' || !p.test(map[i][j]))
				return;
			map[i][j] = 'v'; // v로 방문표시
			for (int[] d: delta) {
				mark(i + d[0], j + d[1], map, p);
			}
		}
		boolean inbound(int i, int j, char[][] map) {
			int N = map.length;
			if (0 <= i && i < N && 0 <= j && j < N)
				return true;
			return false;
		}
		// type == "mix" -> 적록색약
		int solve(char[][] map, String type) {
			int N = map.length;
			int count = 0;
			/*
			 * map의 절반
			 * x에 해당하는 부분
			 * xxxx
			 * xxxo
			 * xxoo
			 * xooo
			 */
			for (int c = 0; c < N; c++) {
				int i = 0, j = c;
				while (inbound(i, j, map)) {
					char color = map[i][j];
					if (color == 'v') {
						i += 1; j -= 1;
						continue;
					}
					count += 1;
					if (type.equals("mix")) {
						if (color == 'R' || color == 'G')
							mark(i, j, map, x -> x == 'R' || x == 'G');
						else
							mark(i, j, map, x -> x == color);
					} else {
						mark(i, j, map, x -> x == color);
					}
					i += 1; j -= 1;
				}
			}
			/*
			 * map의 나머지 절반
			 * o에 해당하는 부분
			 * xxxx
			 * xxxo
			 * xxoo
			 * xooo
			 */
			for (int r = 1; r < N; r++) {
				int i = r, j = N - 1;
				while (inbound(i, j, map)) {
					char color = map[i][j];
					if (color == 'v') {
						i += 1; j -= 1;
						continue;
					}
					count += 1;
					if (type.equals("mix")) {
						if (color == 'R' || color == 'G')
							mark(i, j, map, x -> x == 'R' || x == 'G');
						else
							mark(i, j, map, x -> x == color);
					} else {
						mark(i, j, map, x -> x == color);
					}
					i += 1; j -= 1;
				}
			}
			return count;
		}
	}
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		int c1 = ut.solve(ut.copy(map), "");
		int c2 = ut.solve(map, "mix");
		System.out.print(c1 + " " + c2);
	}
}
