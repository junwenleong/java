import java.util.*;

// Barebones Union-Find Disjoint Sets implementation, 
// using both path compression and union by rank heuristics

class UnionFind {                                              
  public static int[] p;
  public UnionFind(int N) {
    p = new int[N];
    int i = 0;
    while (i < N) {
      p[i] = i;
      i++;}
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else {
      p[i] = findSet(p[i]);
      return p[i]; }}

  public void unionSet(int i, int j) {  //remove union by rank heuristic
      int x = findSet(i); 
      int y = findSet(j);
      //p[y] = x;
      p[x] = y; }
}

public class ladice{
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int nitems = fio.nextInt();
        int ndrawers = fio.nextInt();
        int[] used = new int[10*ndrawers]; //0 is empty, 1 is filled
        UnionFind drawers = new UnionFind(10*ndrawers); //initialise the n drawers
        while (nitems > 0) { 
            boolean stored = false;
            int drawera = fio.nextInt();
            int drawerb = fio.nextInt();
            if (used[drawera] != 1 ){ //case 1
                used[drawera] = 1;
                drawers.unionSet(drawera, drawerb); 
                stored = true; }
            else if (used[drawerb] != 1 ) { //case 2
                used[drawerb] = 1;
                drawers.unionSet(drawerb, drawera);
                stored = true; }
            else if (used[drawers.findSet(drawera)] != 1) { //case 3
                    used[drawers.findSet(drawera)] = 1;
                    drawers.unionSet(drawera, drawerb);
                    stored = true; }
            else if (used[drawers.findSet(drawerb)] != 1) { //case 4
                    used[drawers.findSet(drawerb)] = 1;
                    drawers.unionSet(drawerb, drawera);
                    stored = true;}
            if (stored) {
                fio.println("LADICA");
            } else {fio.println("SMECE");}
            nitems--;
        }
        fio.flush();
        fio.close();
    }
}
