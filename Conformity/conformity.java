import java.util.*;

public class conformity{
    public static void main(String[] args){
        FastIO fio = new FastIO();
        HashMap<HashSet, Integer> mymap = new HashMap<HashSet, Integer>();
        for (int n = fio.nextInt(); n>0;n--) {
            int combi1 = fio.nextInt();
            int combi2 = fio.nextInt();
            int combi3 = fio.nextInt();
            int combi4 = fio.nextInt();
            int combi5 = fio.nextInt();
            HashSet<Integer> h = new HashSet<Integer>(); 
            h.add(combi1);
            h.add(combi2);
            h.add(combi3);
            h.add(combi4);
            h.add(combi5);
            if (mymap.containsKey(h)) {
                mymap.put(h,mymap.get(h)+1);
            } else {
                mymap.put(h,1);
            }
        }
        int nomax = 0;
        int currmax = Collections.max(mymap.values());

        for (HashSet key : mymap.keySet()) {
           if (mymap.get(key) == currmax) {
            nomax++;
           }
        } 
        fio.print(nomax*currmax);
        fio.close();
    }
}
