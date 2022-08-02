// SWEA 1954. 달팽이 숫자
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static final BufferedReader br = new BufferedReader(
										new InputStreamReader(System.in));
	static final StringBuilder answer = new StringBuilder();
	static final int MAX_SIZE = 10; // 달팽이의 크기는 1 이상 10 이하
	// 달팽이를 그릴 판
	static final int[][] board = new int[MAX_SIZE][];
	static { // 그림판 초기화
		for (int i = 0; i < MAX_SIZE; i++)
			board[i] = new int[MAX_SIZE];
	}
	/**
	 * @param t 테스트 케이스 번호
	 * @param N 전체 달팽이 크기
	 * @param width 달팽이 둘레의 한 변의 길이
	 * @param beginRow 달팽이 둘레 시작 행
	 * @param beginCol 달팽이 둘레 시작 열
	 * @param beginValue 달행이 둘레 시작 번호
	 */
	static void solve(int t, int N, int width,
						int beginRow, int beginCol, int beginValue) {
		if (width <= 1) {
			if (width == 1)
				board[beginRow][beginCol] = beginValue;
			
			// flush
			answer.append("#")
					.append(t)
					.append("\n");
			for (int i = 0; i < N; i++) {
				StringBuilder row = new StringBuilder();
				for (int j = 0; j < N; j++) {
					row.append(board[i][j])
						.append(" ");
				}
				// trim
				row.deleteCharAt(row.length() - 1);
				answer.append(row.toString())
					.append("\n");
			}
			return;
		}
		// 북쪽
		for (int i = 0; i < width - 1; i++) {
			board[beginRow][beginCol + i] = beginValue++;
		}
		// 동쪽
		for (int i = 0; i < width - 1; i++) {
			board[beginRow + i][beginCol + width - 1] = beginValue++;
		}
		// 남쪽
		for (int i = 0; i < width - 1; i++) {
			board[beginRow + width - 1][beginCol + width - 1 - i] = beginValue++;
		}
		// 서쪽
		for (int i = 0; i < width - 1; i++) {
			board[beginRow + width - 1 - i][beginCol] = beginValue++;
		}
		solve(t, N, width - 2, beginRow + 1, beginCol + 1, beginValue);
	}
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) { // 테스트 케이스 실행
			int N = Integer.parseInt(br.readLine());
			solve(t, N, N, 0, 0, 1);
		}
		System.out.print(answer.toString());
	}
}
