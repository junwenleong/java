import java.util.*;

public class t9spelling {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int nocases = sc.nextInt();
		sc.nextLine();
		String[] arr = new String[123];
		String finaloutpt = "";
		arr[32] = "0";
		arr[97] = "2";
		arr[98] = "22";
		arr[99] = "222";
		arr[100] = "3";
		arr[101] = "33";
		arr[102] = "333";
		arr[103] = "4";
		arr[104] = "44";
		arr[105] = "444";
		arr[106] = "5";
		arr[107] = "55";
		arr[108] = "555";
		arr[109] = "6";
		arr[110] = "66";
		arr[111] = "666";
		arr[112] = "7";
		arr[113] = "77";
		arr[114] = "777";
		arr[115] = "7777";
		arr[116] = "8";
		arr[117] = "88";
		arr[118] = "888";
		arr[119] = "9";
		arr[120] = "99";
		arr[121] = "999";
		arr[122] = "9999";

		for (int casenr = 1; casenr <= nocases; casenr++) { //iterate through cases, keeping track of case number
			String inpt = sc.nextLine();
			String outpt = "";
			for (int i = 0; i < inpt.length(); i++) { //iterate through characters of case string, i means index
				if (outpt.length() > 0) {
					if ((arr[inpt.charAt(i)].charAt(0)) == (outpt.charAt(outpt.length()-1))) {
						outpt += " ";
					}							
				}
				outpt += arr[inpt.charAt(i)];			
			}
			finaloutpt += "Case #";
			finaloutpt += Integer.toString(casenr);
			finaloutpt += ": ";
			finaloutpt += outpt;
			finaloutpt += "\n";		
		}
		System.out.println(finaloutpt);

	}
}
