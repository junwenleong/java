import java.util.*;

public class islands{
    public static String[][] map = new String[9999][9999];
    public static int[][] visited = new int[9999][9999];
    public static void dfs(int a, int b, int nrow, int ncol) {
        visited[a][b] = 1;
        int anext = a + 1;
        int aprev = a - 1;
        int bnext = b + 1;
        int bprev = b - 1;
        if (anext < nrow) {
        if ((map[anext][b]).equals("C") || (map[anext][b]).equals("L")) {
            if (visited[anext][b] == 0) {dfs(anext,b,nrow,ncol);}
        } }
        if (aprev >= 0) {
        if ((map[aprev][b]).equals("C") || (map[aprev][b]).equals("L")) {
            if (visited[aprev][b] == 0) {dfs(aprev,b,nrow,ncol);}
        } }
        if (bnext < ncol) {
        if ((map[a][bnext]).equals("C") || (map[a][bnext]).equals("L")) {
            if (visited[a][bnext] == 0) {dfs(a,bnext,nrow,ncol);}
        } }
        if (bprev >= 0) {
        if ((map[a][bprev]).equals("C") || (map[a][bprev]).equals("L")) {
            if (visited[a][bprev] == 0) {dfs(a,bprev,nrow,ncol);}
        } }
    }
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int r = fio.nextInt();
        int c = fio.nextInt();
        int ans = 0;
        for (int i = 0; i < r; i++) {
            String[] inpt = fio.next().split("");
            for (int k = 0; k < c; k++) {
                String letter = inpt[k];
                map[i][k] = letter;
                
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                String currcell = map[i][j];
                if (currcell.equals("L") && visited[i][j] == 0) {
                    dfs(i,j,r,c);
                    ans++;
                }
            }
        }
        fio.print(ans);
        fio.close();
    }
}
