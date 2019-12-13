import java.util.*;

public class weakvertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        boolean terminate = false;
        while (!terminate) {
            int n = fio.nextInt(); //no. of vertices
            if (n == -1) {
                terminate = true;
                break;
            }
            int[][] adjMatrix = new int[n][n]; 
            for (int i = 0;i<n;i++) { //rows
                for (int j = 0;j<n;j++) { //cols
                    int inpt = fio.nextInt();
                    adjMatrix[i][j] = inpt;
                }
            }
            Boolean[] weak = new Boolean[n]; //weak vertices stored here
            Arrays.fill(weak, Boolean.TRUE);
            for (int a = 0; a < n; a++) { //each vertice/row
                for (int b = a + 1; b < n; b++) {
                    if (a == b || adjMatrix[a][b] == 0) {continue;} //checks for a-b connection
                    for (int c = b + 1 ; c < n ; c++) {
                        if (adjMatrix[a][c] == 1 && adjMatrix[b][c] == 1) { //check for a-c and b-c
                        weak[a] = false;
                        weak[b] = false;
                        weak[c] = false;} 
                    }
                }
            }
            for (int ia = 0;ia < n;ia++) {
                if (weak[ia]) {fio.print(ia + " ");}
            } 
            fio.println("");
        }
        fio.close();
    }   
}
