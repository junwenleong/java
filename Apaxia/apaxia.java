import java.util.*;

public class apaxia {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		StringBuilder inpt = new StringBuilder(sc.nextLine());
		for (int index = 1; index < inpt.length();) {
			if ((inpt.charAt(index)) == (inpt.charAt(index-1))) {
				inpt.deleteCharAt(index);
				index--;
		}
			index++;

			}

		System.out.println(inpt);
	}
}
