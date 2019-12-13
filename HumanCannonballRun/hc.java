import java.util.*;
import java.lang.Math;

public class hc {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        double startx = fio.nextDouble();
        double starty = fio.nextDouble();
        anode startnode = new anode(startx,starty);
        double endx = fio.nextDouble();
        double endy = fio.nextDouble();
        anode endnode = new anode(endx,endy);
        int n = fio.nextInt();
        double[][] adjmat = new double[n+2][n+2];
        HashMap<Integer,anode> allnodes = new HashMap<Integer,anode>();
        allnodes.put(0,startnode);
        allnodes.put(n+1,endnode);
        for (int x = 1; x <= n; x++) {
            double cannonx = fio.nextDouble();
            double cannony = fio.nextDouble();  
            anode thiscannon = new anode(cannonx,cannony);
            allnodes.put(x,thiscannon);
        }
        for (int i = 0; i <= n+1;i++) { //initialise diagonals to 0
            adjmat[i][i] = 0;
        }
        for (int i = 1; i <= n+1;i++) { //start node row
            double thisx = allnodes.get(i).x;
            double thisy = allnodes.get(i).y;
            double time = Math.sqrt(Math.pow(thisx-startx,2) + Math.pow(thisy-starty,2))/5; 
            adjmat[0][i] = time;
        }
        for (int i = 0; i <= n;i++) { //end node row
            double thisx = allnodes.get(i).x;
            double thisy = allnodes.get(i).y;
            double time = Math.sqrt(Math.pow(thisx-endx,2) + Math.pow(thisy-endy,2))/5; 
            adjmat[n+1][i] = time;
        }


        for (int i = 1; i <= n; i++ ) {
            for (int j = 0; j <= n+1; j++) {
                if (i != j) {
                    double fromx = allnodes.get(i).x;
                    double fromy = allnodes.get(i).y;
                    double tox = allnodes.get(j).x;
                    double toy = allnodes.get(j).y;
                    double walktime = Math.sqrt(Math.pow(fromx-tox,2) + Math.pow(fromy-toy,2))/5; 
                    double makeupdistance = Math.abs(Math.sqrt(Math.pow(fromx-tox,2) + Math.pow(fromy-toy,2))-50);
                    double makeuptime = makeupdistance/5;
                    double fullcannontime = makeuptime + 2;
                    adjmat[i][j] = Math.min(walktime,fullcannontime);
                }
            }
        }
        //System.out.println(Arrays.deepToString(adjmat));
        Double[] distance = new Double[n+2];

        for (int i = 1; i <= n + 1; i++) { 
            distance[i] = Double.MAX_VALUE; 
        }  
        distance[0] = 0.0;

        PriorityQueue < IntegerPair > pq = new PriorityQueue < IntegerPair > ();
        pq.offer(new IntegerPair(0, 0));
    
        while (!pq.isEmpty()) { // main loop
            IntegerPair top = pq.poll(); // greedy: pick shortest unvisited vertex
            //System.out.println(top.weight);
            //System.out.println(top.first);
            //System.out.println(distance[top.first]);
            if (top.weight==distance[top.first]) {
                for (int vert = 0; vert <= n+1; vert++) {
                    if (vert != top.first) {
                        //System.out.println(top.first + " to " + vert);
                        //System.out.println(adjmat[top.first][vert]);
                        //System.out.println(top.first);
                        //System.out.println(distance[vert]);
                        //System.out.println(distance[top.first]);
                        if (distance[vert] > distance[top.first] + adjmat[top.first][vert]) {
                            distance[vert] = distance[top.first] + adjmat[top.first][vert];
                            pq.offer(new IntegerPair(vert,distance[vert]));
                        }
                    }
                }
            }
        }      
        double ans = distance[n+1];
        fio.print(ans);
        fio.close();
    }
}

class anode {
    double x;
    double y;

    public anode(double a, double b) {
        this.x=a;
        this.y=b;
    }
}

class IntegerPair implements Comparable<IntegerPair> {
  Integer first;
  double weight;

  public IntegerPair(Integer f, double s) {
    first = f;
    weight = s;
  }

  public int compareTo(IntegerPair o) {
    if (this.weight > o.weight) return 1;
    if (this.weight == o.weight) return 0;
    else return -1;
  }

}
