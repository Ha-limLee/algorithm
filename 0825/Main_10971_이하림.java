import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ 10971 외판원 순회 2
class Main {
	static Util ut = new Util();
	static class Util {
		int toInt(String s) {
			return Integer.parseInt(s);
		}
		int min = Integer.MAX_VALUE;
		void _dfs(int first, int curr, int sum, int count, int[][] mat, boolean[] visited) {
			if (visited[curr])
				return;
			
			visited[curr] = true;
			int N = mat.length;
			for (int i = 0; i < N; i++)
				if (mat[curr][i] != 0)
					_dfs(first, i, sum + mat[curr][i], count + 1, mat, visited);
			visited[curr] = false;
			
			if (count == N - 1)
				if (mat[curr][first] != 0)
					min = Math.min(min, sum + mat[curr][first]);
		}
		int dfs(int first, int curr, int[][] mat) {
			int N = mat.length;
			boolean[] visited = new boolean[N];
			_dfs(first, curr, 0, 0, mat, visited);
			return this.min;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = ut.toInt(br.readLine());
		int[][] mat = new int[N][];
		for (int i = 0; i < N; i++)
			mat[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
		int answer = ut.dfs(0, 0, mat);
		System.out.print(answer);
	}
}
