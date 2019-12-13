import java.util.*;

public class tenkindsofpeople{
    public static int[][] map = new int[1001][1001];
    public static int[][] componentarr = new int[1001][1001];
    public static QueueArr pq = new QueueArr();

    public static void bfs(int startr, int startc, int type,int nrow,int ncol) {
        Tuple start = new Tuple(startr,startc);
        int componentno = startr * nrow + startc; //unique component number for each bfs sequence/component
        componentarr[startr][startc] = componentno;
        pq.offer(start);
        while (!pq.empty()) {
            Tuple currcell = pq.poll();
            int a = currcell.x;
            int b = currcell.y;
            int anext = a + 1;
            int aprev = a - 1;
            int bnext = b + 1;
            int bprev = b - 1;  
            if (anext <= nrow && map[anext][b]== type) {
                if (componentarr[anext][b] == 0) {
                    Tuple nextcell = new Tuple(anext,b);
                    pq.offer(nextcell);
                    componentarr[anext][b] = componentno;
                }
            } 
            if (aprev > 0 && map[aprev][b]== type) {
                if (componentarr[aprev][b] == 0) {
                    Tuple nextcell = new Tuple(aprev,b);
                    pq.offer(nextcell);
                    componentarr[aprev][b] = componentno;
                }
            } 
            if (bnext <= ncol && map[a][bnext]== type) {
                if (componentarr[a][bnext] == 0) {
                    Tuple nextcell = new Tuple(a,bnext);
                    pq.offer(nextcell);
                    componentarr[a][bnext] = componentno;
                }
            } 
            if (bprev > 0 && map[a][bprev]== type) {
                if (componentarr[a][bprev] == 0) {
                    Tuple nextcell = new Tuple(a,bprev);
                    pq.offer(nextcell);
                    componentarr[a][bprev] = componentno;
                }
            } 
        }
    }

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int rows = fio.nextInt();
        int cols = fio.nextInt();
        for (int i = 1; i <= rows; i++) {
            String[] inpt = fio.next().split("");
            for (int k = 1; k <= cols; k++) {
                String a = inpt[k-1];
                map[i][k] = Integer.parseInt(a);
            }
        }
        for (int i = 1; i <= rows; i++) {
            for (int k = 1; k <= cols; k++) {
                if (componentarr[i][k] == 0) //not assigned to any component/ not visited
                    bfs(i,k,map[i][k],rows,cols);
            }
        }

        int n = fio.nextInt();
        for (int x = 0; x < n; x++) {
            int r1 = fio.nextInt();
            int c1 = fio.nextInt();
            int r2 = fio.nextInt();
            int c2 = fio.nextInt();
            //System.out.println("componentarr[r1][c1]" + componentarr[r1][c1] + "   " + "componentarr[r2][c2]" + componentarr[r2][c2]);
            if (componentarr[r1][c1] == componentarr[r2][c2]) {
                if (map[r1][c1] == 0) {fio.println("binary");}
                else {fio.println("decimal");}
            } else {fio.println("neither");}
        }
        fio.close();
    }
}

class QueueArr{
    public Tuple[] arr;
    public int front, back;
    public int capacity;
    public final int INITSIZE = 1;

    public QueueArr() {
        arr = new Tuple[INITSIZE]; // create array of tuples
        front = 0; // the queue is empty
        back = 0;
        capacity = INITSIZE;
    }

    public boolean empty() { 
        return (front == back); 
    }

    public Tuple peek() {
        if (empty()) return null;
        else return arr[front];
    }

    public Tuple poll() {
        if (empty()) return null;
        Tuple item = arr[front];
        front = (front + 1) % capacity;
        return item;
    }

    public void offer(Tuple item) {
        if (((back+1)%capacity) == front) // array is full
          enlargeArr();
        arr[back] = item;
        back = (back + 1) % capacity;
    }

    public boolean enlargeArr() {
        int newSize = capacity * 2;
        Tuple[] temp = new Tuple[newSize];
        if (temp == null) { // i.e. no memory allocated to array of E objects
          System.out.println("Not enough memory");
      System.exit(1);
    }
        for (int j=0; j < capacity; j++) {
            // copy the front (1st) element, 2nd element, ...,  in the 
            // original array to the 1st (index 0), 2nd (index 1), ...,
            // position in the enlarged array
            temp[j] = arr[(front+j) % capacity];
        }
        front = 0; 
        back = capacity - 1;
        arr = temp;
        capacity = newSize;
        return true;
    }
}

class Tuple<X, Y> { 
  public final int x; 
  public final int y; 
  public Tuple(int x, int y) { 
    this.x = x; 
    this.y = y; 
  } 
} 
