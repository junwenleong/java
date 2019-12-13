import java.util.*;

class Station {
    public Boolean used;
    public int timeunused;
    public int timewhenfree;
    public Boolean locked;
    public Station(int z) {
        this.used = true;
        this.timeunused = 0;
        this.timewhenfree = z;
    }
    public Boolean getused() {
        return this.used;
    }
    public void setused(Boolean x) {
        this.used = x;
    }
    
    public int gettimeunused() {
        return this.timeunused;
    }

    public void settimeunused(int x) {
        this.timeunused = x;
    }

    public int gettimewhenfree() {
        return this.timewhenfree;
    }
    
    public void settimewhenfree(int x) {
        this.timewhenfree = x;
    }
}

class StationComparator implements Comparator<Station> {
    public int compare(Station a, Station b) { //a over b
        return (b.gettimeunused() - a.gettimeunused());
    }           
} 

class Researcher {
    public int s;       
    public int d;       
    public Researcher(int a, int b) {
        this.s = a; //time start
        this.d = b; //time done 
    }
}

class ResearcherComp implements Comparator<Researcher> {
    public int compare(Researcher x, Researcher y) {
        return x.s - y.s;
    }
}

public class workstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int num = fio.nextInt(); //no. of researchers
        if (num == 1) {
            fio.print(0);
            fio.close();
            return;
        }
        int m = fio.nextInt(); //no. of minutes before locked
        int saves = 0;
        PriorityQueue<Integer> mylist = new PriorityQueue<>();
        PriorityQueue<Researcher> queue = new PriorityQueue<>(num, new ResearcherComp());   

        for (int i = 0; i < num; i++) {
            int a = fio.nextInt();
            int stayduration = fio.nextInt();
            int b = a + stayduration; //when researcher leaves      
            queue.offer(new Researcher(a, b));}

        while (!queue.isEmpty()) { //start assigning researchers to stations
            Researcher rsrchr = queue.poll();
            boolean assigned = false;
            int count = mylist.size();
            while (count>0 && assigned ==false && mylist.peek()-rsrchr.s <= m ) { //continue if its not within range
            count--;    
            int curr = mylist.poll();
            if (curr >= rsrchr.s) {
                assigned = true; //used unexpired workstation
                break;} 
            }
            mylist.offer(rsrchr.d + m); 
            if(assigned == true) {
                saves++;} else {;}           
        }
        fio.print(saves);
        fio.flush();
        fio.close(); 
    }
}
