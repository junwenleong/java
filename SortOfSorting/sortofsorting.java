import java.util.*;

class Student {
	String name; // class attributes
	public Student(String name) { //constructor method for class
		this.name = name;
	}
}

class NameComparator implements Comparator<Student> {
	public int compare(Student a, Student b) { //a over b
		return a.name.substring(0,2).compareTo(b.name.substring(0,2));			
	}			
}

public class sortofsorting {
	public static void main(String[] args) {
		FastIO fio = new FastIO();
		Scanner sc = new Scanner(System.in);
		//int n = fio.nextInt(); //number of cases
		for (int caseno = 1; caseno <= 500; caseno++) { //each case
			int nofnames = fio.nextInt(); //number of names in each case
			if (nofnames == 0) {
				break;
			}
			Student[] casearr = new Student[nofnames];
			for (int idx = 0; idx < nofnames; idx++) { 
				Student entry = new Student(fio.next());
				casearr[idx] = entry;
			}	
			Arrays.sort(casearr, new NameComparator());
			for (Student item : casearr) { //print each name on a new line
				System.out.println(item.name);
			}
			System.out.println();
		}
		fio.close();
	}
}

