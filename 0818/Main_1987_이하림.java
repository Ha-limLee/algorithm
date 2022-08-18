import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 1987 알파벳
class Main {
	// 상 하 좌 우
	int[][] delta = {
			{-1, 0}, {1, 0},
			{0, -1}, {0, 1} };
{
	boolean[] visited = new boolean['Z' + 1];
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int R = toInt(st.nextToken()), C = toInt(st.nextToken());
	char[][] board = new char[R][];
	for (int i = 0; i < R; i++)
		board[i] = br.readLine().toCharArray();
	if (R == 1 && C == 1)
		System.out.print(1);
	else {
		int ret = solve(0, 0, 0, board, visited);
		System.out.print(ret);
	}
}

boolean inbound(int r, int c, char[][] board) {
	int R = board.length, C = board[0].length;
	if (0 <= r && r < R && 0 <= c && c < C)
		return true;
	return false;
}

int solve(int beginR, int beginC, int count, char[][] board, boolean[] visited) {
	if (!inbound(beginR, beginC, board))
		return count;
	char state = board[beginR][beginC];
	if (visited[state])
		return count;
	
	visited[state] = true;
	int maxCount = count;
	for (int[] d : delta) {
		int nextR = beginR + d[0], nextC = beginC + d[1];
		int ret = solve(nextR, nextC, count + 1, board, visited);
		maxCount = Math.max(maxCount, ret);
	}
	visited[state] = false;
	return maxCount;
}

int toInt(String s) {
	return Integer.parseInt(s);
}

Main() throws IOException { }

public static void main(String[] args) throws IOException {
	new Main();
}}
