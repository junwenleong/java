import java.util.*;

//if (ncards == 1) {
            //  fio.println(1);
            //  continue;
            //}

public class cardtrick {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int nocases = fio.nextInt(); //number of cases
        for (int x = nocases; x > 0; x--) { //through each case
            int ncards = fio.nextInt();
            if (ncards == 1) {
                fio.println(1);
                continue;
            }
            int[] cardstack = new int[ncards];
            int currindex = 1;
            cardstack[currindex] = 1;
            for (int currcard = 2; currcard <= ncards; currcard++) { //iterate through each card up till nth card
                for (int stepstaken = 0; stepstaken <= currcard; stepstaken++) {
                    currindex = (currindex + 1) % ncards;
                    while (cardstack[(currindex)] != 0) { //keep increasing index by 1 until find a "0"
                        currindex = (currindex + 1) % ncards;
                    }
                }
                cardstack[currindex] = currcard; //put in the card at found index.
            }
            String msg = "";
            for (int item : cardstack) {
                msg = msg + Integer.toString(item) + " ";
            }
            fio.println(msg);
            
        }
        fio.close();

    }
    
}
