import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.lang.Object.*;

class Runner {
	String name; // class attributes
	Double timea;
	Double timeb;

	public Runner(String name, Double timea, Double timeb) { //constructor method for class
		this.name = name;
		this.timea = timea;
		this.timeb = timeb;
	}

	public Double Gettimea() {
		return this.timea;
	}

	public Double Gettimeb() {
		return this.timeb;
	}

}



class LegbComparator implements Comparator<Runner> {
	public int compare(Runner x, Runner y) { 
		if (x.timeb > y.timeb) {
			return 1;
		} else if (x.timeb == y.timeb) {
			return 0;
		} else {
			return -1;
		}
	}
	
}

class LegaComparator implements Comparator<Runner> {
	public int compare(Runner x, Runner y) { 
		if (x.timea > y.timea) {
			return 1;
		} else if (x.timea == y.timea) {
			return 0;
		} else {
			return -1;
		}
	}
	
}


public class bestrelayteam {

	public static void main(String[] args) {
		LegaComparator comptimea = new LegaComparator(); // initialise comparator for other legs
		LegbComparator comptimeb = new LegbComparator();
		FastIO fio = new FastIO();
		int runners = fio.nextInt(); // number of runners
		LinkedList<Runner> timings = new LinkedList<>();
		Runner[] sortedb = new Runner[runners];
		for (int runnerno = 0; runnerno < runners; runnerno++) { // runnerno is index, starting from 0 so rmb
			Runner adding = new Runner(fio.next(), fio.nextDouble(), fio.nextDouble());
			timings.add(runnerno, adding);

		}
		timings.sort(comptimeb); // timings array is sorted by other legs timing
		for (int runne = 0; runne < runners; runne++) {
			sortedb[runne] = timings.get(runne);
		}

		LinkedList<Runner> teambest = new LinkedList<>();
		Double timebest = sortedb[runners-1].Gettimea() * 4; // slowest other legs guy time possible (x4)

		for (Runner d : sortedb) {
			LinkedList<Runner> currteam = new LinkedList<>();
			Double currtime = d.Gettimea();			// start off with first lap time
			currteam.add(d);				// add first lap guy
			for (Runner e : sortedb) {
				if (currteam.size() == 4) { // team filled
					break;
				}
				if (d.equals(e)) { // if runners are same time
					continue;
				}
				currteam.add(e);						
				currtime += e.Gettimeb();				
			}
			if (currtime < timebest) {
				teambest = currteam;				
				timebest = currtime;
			}
		}

		fio.println(timebest); // print total time
		for (Runner i : teambest) {
			fio.println(i.name); // print each member's name
		}
		fio.close();
	}
}




