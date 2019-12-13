import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PeaSoup {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // number of restaurants
		while (n > 0) {
			int k = sc.nextInt(); // number of menu items
			sc.nextLine();
			String nme = sc.nextLine(); // restaurant name
			boolean mark1 = false;
			boolean mark2 = false;
			while (k > 0) {
				String item = sc.nextLine() ;
				if (item.equals("pea soup")) {
					mark1 = true;
				}
				if (item.equals("pancakes")) {
					mark2 = true;
				}
				k--;
			}
			if (mark1 && mark2) {
				System.out.println(nme);
				return;	
			}
		
			n--;
			
		}
		System.out.println("Anywhere is fine I guess");

	}
}
