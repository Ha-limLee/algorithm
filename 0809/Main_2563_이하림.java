import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 2563
class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	final int N = toInt(br.readLine()); // 색종이 수
	final int WIDTH = 10; // 색종이의 한 변의 크기, 색종이는 정사각형
	final int BOARD_WIDTH = 100;
	final boolean[][] board = new boolean[BOARD_WIDTH][];
	
	{ // board 초기화
		for (int i = 0; i < BOARD_WIDTH; i++)
			board[i] = new boolean[BOARD_WIDTH];
	}
	
	{ // N개의 색종이 입력 받기
		StringTokenizer st;
		int count = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = toInt(st.nextToken());
			int y = toInt(st.nextToken());
			for (int i = 0; i < WIDTH; i++) {
				for (int j = 0; j < WIDTH; j++) {
					if (!board[x + i][y + j]) {
						board[x + i][y + j] = true;
						count += 1;
					}
				}
			}
		}
		System.out.print(count);
	}
	
	int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	Main() throws IOException { }
	
	public static void main(String[] args) throws IOException {
		new Main();
	}
}
