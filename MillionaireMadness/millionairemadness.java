import java.util.*;


class Pile implements Comparable<Pile> {
  Integer x, y, height;

  public Pile(Integer a, Integer b, Integer c) {
    this.x = a;
    this.y = b;
    this.height = c;
  }

  public int compareTo(Pile o) {
     return this.height - o.height;
  }
}

public class millionairemadness{
    public static int[][] map = new int[1001][1001];
    public static int[][] visited = new int[1001][1001];
    
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int length = fio.nextInt();
        int width = fio.nextInt();
        int coinx = length - 1;
        int coiny = width - 1;
        PriorityQueue<Pile> pq = new PriorityQueue<Pile>(); //holds ladders needed, sorted shortest
        Pile start = new Pile(0,0,0);
        pq.offer(start);
        for (int x = 0; x < length; x++ ) {
            for (int y = 0; y < width; y++){
                int a = fio.nextInt();
                map[x][y] = a;}
        }

        while (!pq.isEmpty()) {
            Pile thispile = pq.poll();
            int thisx = thispile.x;
            int thisy = thispile.y;
            int xnext = thisx + 1;
            int xprev = thisx - 1;
            int ynext = thisy + 1;
            int yprev = thisy - 1;  
            visited[thisx][thisy] = 1;
            
            if( thisx == coinx && thisy == coiny ) {
                fio.print(thispile.height); // highest priority ladder, which is shortest one in PQ
                break;
            }
            if (xnext < length && visited[xnext][thisy] == 0) {
                int h = thispile.height;
                int nexth = map[xnext][thisy] - map[thisx][thisy];
                if (nexth > h) {h = nexth;} //no need >=
                Pile newpile = new Pile(xnext, thisy, h);
                pq.offer(newpile);
            } 
            if (xprev >= 0 && visited[xprev][thisy] == 0) {
                int h = thispile.height;
                int nexth = map[xprev][thisy] - map[thisx][thisy];
                if (nexth > h) {h = nexth;}
                Pile newpile = new Pile(xprev, thisy, h);
                pq.offer(newpile);
            } 
            if (ynext < width && visited[thisx][ynext] == 0) {      
                int h = thispile.height;
                int nexth = map[thisx][ynext] - map[thisx][thisy];
                if (nexth > h) {h = nexth;}
                Pile newpile = new Pile(thisx, ynext, h);
                pq.offer(newpile);
            } 
            if (yprev >= 0 && visited[thisx][yprev] == 0) {
                int h = thispile.height;
                int nexth = map[thisx][yprev] - map[thisx][thisy];
                if (nexth > h) {h = nexth;}
                Pile newpile = new Pile(thisx, yprev, h);
                pq.offer(newpile);
            }
        }
        fio.close();
    }
}
