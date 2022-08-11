import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder out = new StringBuilder();
	final int MAX_SIZE = 9;
	final int TARGET = 100;
	int[] shortys = new int[MAX_SIZE];
	{
		for (int i = 0; i < MAX_SIZE; i++)
			shortys[i] = toint(br.readLine());
		int[] comb = {0, 0, 1, 1, 1, 1, 1, 1, 1};
		do {
			int sum = 0;
			for (int i = 0; i < MAX_SIZE; i++) {
				if (comb[i] == 1) {
					sum += shortys[i];
				}
			}
			if (sum == TARGET) {
				for (int i = 0; i < MAX_SIZE; i++)
					if (comb[i] == 1)
						out.append(shortys[i])
							.append("\n");
				System.out.print(out.toString());
				break;
			}
		} while (np(comb));
	}
	
	boolean np(int arr[]) {
		int n = arr.length;
		int i = n - 1;
		
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if (i == 0) return false;
		
		int j = n - 1;
		
		while (arr[i - 1] >= arr[j]) j--;
		
		swap(arr, i - 1, j);
		
		int k = n - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}
		return true;
	}
	
	void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	int toint(String s) {
		return Integer.parseInt(s);
	}
	
	
	Main() throws IOException { }
	
	public static void main(String[] args) throws IOException {
		new Main();
	}
}
