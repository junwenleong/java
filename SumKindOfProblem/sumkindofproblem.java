import java.util.*;

public class sumkindofproblem {
	public static void main(String[] args) {
		FastIO fio = new FastIO();
		int datasets = fio.nextInt();
		for (int x = datasets; x > 0; x--) { //for each line/dataset
			int k = fio.nextInt(); // k is dataset number
			int limit =fio.nextInt();// number of times 
			int s1 = 0; int s2 = 0; int s3 = 0;
			for (int num = 1; num <= limit; num++) {
				s1 += num;
			fio.println(Integer.toString(s1));	
			}
		} 
		fio.close();
	}
}
