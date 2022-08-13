import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 17406 배열 돌리기 4

class Main {{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	// 배열 A의 크기, N x M
	final int N = toInt(st.nextToken()), M = toInt(st.nextToken());
	// 회전 연산의 개수
	final int K = toInt(st.nextToken());
	// 원본 배열
	final int[][] A = new int[N][];
	
	// A 초기화
	for (int i = 0; i < N; i++) {
		A[i] = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < M; j++)
			A[i][j] = toInt(st.nextToken());
	}
	
	// K 개의 회전연산
	int[][] ops = new int[K][];
	// 회전연산 초기화
	for (int i = 0; i < K; i++) {
		st = new StringTokenizer(br.readLine());
		int r = toInt(st.nextToken()) - 1;
		int c = toInt(st.nextToken()) - 1;
		int s = toInt(st.nextToken());
		ops[i] = new int[] {r, c, s};
	}
	
	int[] out = {Integer.MAX_VALUE};
	for (int i = 0; i < K; i++)
		unsafePerm(i, 0, K, 0, A, ops, out);
	
	System.out.print(out[0]);
}

void unsafePerm(int curr, int count, int K, int mask, int[][] A, int[][] ops, int[] out) {
	int bit = 1 << curr;
	if ((mask & bit) != 0)
		return;
	
	mask |= bit;
	int r = ops[curr][0];
	int c = ops[curr][1];
	int s = ops[curr][2];
	
	unsafeRotateCW(A, r, c, s);
	
	count += 1;

	if (K == count) {
		out[0] = Math.min(out[0], arrValue(A));
	} else {
		for (int i = 0; i < K; i++)
			unsafePerm(i, count, K, mask, A, ops, out);
	}
	
	unsafeRotateCCW(A, r, c, s);
}

int toInt(String s) {
	return Integer.parseInt(s);
}

// 배열의 값
int arrValue(int[][] arr) {
	int minSum = Integer.MAX_VALUE;
	for (int i = 0; i < arr.length; i++) {
		int sum = 0;
		for (int j = 0; j < arr[i].length; j++) {
			sum += arr[i][j];
		}
		minSum = Math.min(minSum, sum);
	}
	return minSum;
}

//반시계 방향으로 돌리기
void unsafeRotateCCW(int[][] arr, int r, int c, int s) {
	if (s == 0)
		return;
	
	int beginR = r - s, beginC = c - s;
	int endR = r + s, endC = c + s;

	int temp = arr[beginR][beginC];
	
	for (int j = beginC; j < endC; j++)
		arr[beginR][j] = arr[beginR][j + 1];
	
	for (int i = beginR; i < endR; i++)
		arr[i][endC] = arr[i + 1][endC];
	
	for (int j = endC; j > beginC; j--)
		arr[endR][j] = arr[endR][j - 1];
	
	for (int i = endR; i > beginR; i--)
		arr[i][beginC] = arr[i - 1][beginC];
	
	arr[beginR + 1][beginC] = temp;
	
	unsafeRotateCCW(arr, r, c, s - 1);
}

//시계 방향으로 돌리기
void unsafeRotateCW(int[][] arr, int r, int c, int s) {
	if (s == 0)
		return;
	
	int beginR = r - s, beginC = c - s;
	int endR = r + s, endC = c + s;
	
	int temp = arr[beginR][beginC];
	
	for (int i = beginR; i < endR; i++)
		arr[i][beginC] = arr[i + 1][beginC];
	
	for (int j = beginC; j < endC; j++)
		arr[endR][j] = arr[endR][j + 1];
	
	for (int i = endR; i > beginR; i--)
		arr[i][endC] = arr[i - 1][endC];
	
	for (int j = endC; j > beginC; j--)
		arr[beginR][j] = arr[beginR][j - 1];
	
	arr[beginR][beginC + 1] = temp;
	
	unsafeRotateCW(arr, r, c, s - 1);
}

Main() throws IOException { }

public static void main(String[] args) throws IOException {
	new Main();
}}
