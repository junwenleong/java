import java.util.*;


class Quest implements Comparable<Quest>{
    public int energycost;      
    public int reward;  
    public int c;   
    public Quest(int a, int b,int c) {
        this.energycost = a; 
        this.reward = b;
        this.c = c;
    }
    public int compareTo(Quest y) {
        int a = this.energycost-y.energycost; 
        if (a != 0) {
            return a;
        } else {
            int b = this.reward - y.reward; 
            if (b!=0) {
                return b;
            } else {return this.c-y.c;}
        }
    }
}

public class kattissquest {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int ncommands = fio.nextInt();
        TreeMap<Quest,Integer> ts = new TreeMap<>();
        while (ncommands > 0) {
            String command = fio.next();
            if (command.equals("add")) {
                int energy = fio.nextInt();
                int gold = fio.nextInt();
                ts.put(new Quest(energy,gold,ncommands),energy);
            } else {
                long goldearned=0L;
                int energyhave = fio.nextInt();
                while(ts.floorKey(new Quest(energyhave,999999,1)) != null) {
                    Quest x = ts.floorKey(new Quest(energyhave,999999,1));
                    ts.remove(x);
                    energyhave -= x.energycost;
                    goldearned += x.reward;
                }
                fio.println(goldearned);
            }
            ncommands--;
        }
        fio.close();

    }
}   
