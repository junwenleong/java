import java.util.*;

public class lostmap {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt();
        ArrayList < IntegerTriple > EdgeList = new ArrayList < IntegerTriple >();
        int nedges = 0;

        for (int a = 0; a < n; a++ ) {
            for (int b = 0; b < n; b++){
                int c = fio.nextInt();  
                if (b > a) {
                IntegerTriple newedge = new IntegerTriple(c, a, b);
                nedges++;
                EdgeList.add(newedge);  
                }            
            }
        }
        Collections.sort(EdgeList);      

        UnionFind UF = new UnionFind(n); // all V are disjoint sets at the beginning
        for (int i = 0; i < nedges; i++) { // process all edges, O(E)
          IntegerTriple e = EdgeList.get(i);
          int u = e.second(), v = e.third(), w = e.first(); // note that we have re-ordered the integer triple
          if (!UF.isSameSet(u, v)) { // if no cycle
            fio.println((u+1) + " " + (v+1));
            UF.unionSet(u, v); // link these two vertices
          }
        }
        fio.close();
    }   
    
}

class IntegerTriple implements Comparable<IntegerTriple> {
  public Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

    public int compareTo(IntegerTriple o) {
            if (!this.first().equals(o.first()))
              return this.first() - o.first();
            else if (!this.second().equals(o.second()))
              return this.second() - o.second();
            else
              return this.third() - o.third();
          }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}

// Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
  public ArrayList<Integer> p, rank, setSize;
  public int numSets;

  public UnionFind(int N) {
    p = new ArrayList<Integer>(N);
    rank = new ArrayList<Integer>(N);
    setSize = new ArrayList<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }

  public int findSet(int i) { 
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret; 
    } 
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
      numSets--; 
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
      else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y)+1); } 
    } 
  }

}
