import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static final BufferedReader br
		= new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw
		= new BufferedWriter(new OutputStreamWriter(System.out));
	static final int MAX_N = 15; // 1부터 시쟉하는 배열
	static int[][] arr = new int[MAX_N + 1][];
	static {
		for (int i = 0; i <= MAX_N; i++)
			arr[i] = new int[MAX_N + 1];
	}
	
	static int runTC() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				arr[i][j] = arr[i][j] + arr[i][j - 1]
								+ arr[i - 1][j] - arr[i - 1][j - 1];
		
		int max = Integer.MIN_VALUE;
		for (int i = M; i <= N; i++) {
			for (int j = M; j <= N; j++) {
				int curr = arr[i][j] - arr[i][j - M]
							- arr[i - M][j] + arr[i - M][j - M];
				max = Math.max(max, curr);
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		// run test case
		for (int t = 1; t <= T; t++) {
			int ret = runTC();
			bw.write("#" + t);
			bw.write(" " + ret + "\n");
		}
		bw.flush();
	}
}
