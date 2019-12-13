import java.util.*;

public class trainpassengers {
	public static void main(String[] args) {
		boolean possibility = true;
		FastIO fio = new FastIO();
		int capacity = fio.nextInt();
		int stations = fio.nextInt();
		int passengers = 0;
		for (int stationno = 0; stationno < stations; stationno++) { //iterate through lines/stations			
			passengers = passengers - fio.nextInt();
			if (passengers < 0 || passengers > capacity) { // passengers < 0 or exceeding capacity
				possibility = false;
			}
			passengers = passengers + fio.nextInt();
			int stay = fio.nextInt();
			if (passengers < 0 || passengers > capacity) { // passengers < 0 or exceeding capacity
				possibility = false;
			}
			if (stay != 0 && passengers < capacity) { //people staying at station and train not fully filled
				possibility = false;
			}
			if (stationno == (stations-1) && stay != 0) { //last station must not have anyone waiting
				possibility = false;
			}
		}
		if (passengers != 0) {
			possibility = false;
		}
		if (possibility) {
			fio.println("possible");
		} else {
			fio.println("impossible");
		}
		fio.close();

	}
}
