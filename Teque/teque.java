import java.util.*;
//circular array

public class teque{
    static int[] front;
    static int[] back;
    static int frontstart = 0; //first filled space
    static int frontend = 0; //last filled space
    static int backstart = 0; //first filled space
    static int backend = 0; //last filled space 
    static int frontsize = 0;
    static int backsize = 0;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt(); // n is number of operations
        front = new int[n];
        back = new int[n];
        while (n > 0) {

            String command = fio.next();
            int num = fio.nextInt();
            if (command.equals("get")) {
                if (frontsize+backsize == 1) {
                    fio.println(back[backstart]);
                } else {
                fio.println(get(num)); }
            } else if (command.equals("push_front")) {
                push_front(num);
            } else if (command.equals("push_middle")) {
                push_middle(num);
            } else if (command.equals("push_back")){ 
                push_back(num); 
            } 
            //System.out.println("front is " + Arrays.toString(front));
            //System.out.println("back is " + Arrays.toString(back));
            n--;
        }
    fio.close();    
    }

    public static void push_front(int x) {
        if (frontsize == 0 & backsize != 0)  {//empty, not empty
            front[frontstart] = x;
            frontsize++;
        } else if (frontsize == 0 && backsize == 0) { //empty, empty
            back[backstart] = x;
            backsize++;
        /*} else if (frontsize != 0 && backsize == 0) { //not empty, empty
        frontstart--;
        if (frontstart < 0) {
        frontstart = front.length+frontstart;}
        front[frontstart] = x;
        frontsize++;*/
        } else { // not empty, not empty:
            if (backsize != frontsize) { //back is 1 bigger than front
                frontstart--;
                if (frontstart < 0) {
                frontstart = front.length+frontstart;}
                front[frontstart] = x;
                frontsize++;
            } else { //back same size as front. put at start of front, then end of element of front to first element of back
                backsize++;
                frontstart--;
                if (frontstart < 0) {
                frontstart = front.length+frontstart;}
                front[frontstart] = x;
                backstart--;
                if (backstart < 0) {
                backstart = back.length+backstart;}
                back[backstart] = front[frontend];
                frontend--; //maybe here
                if (frontend < 0) {
                frontend = front.length+frontend;}
            }
        }
    }

    private static void push_middle(int x) { //
        if (backsize == 0)  {//empty, empty
            back[backstart] = x;
            backsize++;
        } else { // back not empty:
            if (backsize != frontsize) { //move back's first elem to end of front
                if (frontsize == 0) { //empty, not empty
                    front[frontstart] = back[backstart];
                    frontsize++;
                    back[backstart] = x; 
                } else { //not empty, not empty diff size
                frontend++;
                if (frontend >= front.length) {
                        frontend -= front.length;
                    }
                front[frontend] = back[backstart];
                back[backstart] = x;
                frontsize++;}
            } else { //same size, both not empty
                backstart--;
                if (backstart < 0) {
                backstart = back.length+backstart;}
                back[backstart] = x;
                backsize++;}
            }   
    }

    private static void push_back(int x) { 
        if (backsize == 0)  {// empty, empty
            back[backstart] = x;
            backsize++;
            //System.out.println("doing this!");
        } else { //back not empty:
            if (frontsize == 0) { //empty, not empty
                frontsize++;
                front[0] = back[backstart];
                backstart = (backstart + 1) % back.length;
                backend = (backend + 1) % back.length;
                back[backend] = x;
                //System.out.println("doing this!!");
            } else { // not empty, not empty 
                if (backsize == frontsize) { //samesize
                    backend = (backend + 1) % back.length;
                    backsize++;
                    back[backend] = x; 
                    //System.out.println("doing this!!!");
                } else { //back is 1 larger
                    frontsize++;
                    frontend++;
                    if (frontend >= front.length) {
                        frontend -= front.length;
                    }
                    front[frontend] = back[backstart];
                    backstart = (backstart + 1) % back.length;
                    backend = (backend + 1) % back.length;
                    back[backend] = x;
                    //System.out.println("doing this!!!!");
                }    
            }
        }
    }

    private static int get(int idx) { 
        if (idx < frontsize) { // item is in front array
            idx += frontstart;
            if (idx > front.length-1) {
                idx -= front.length;}
            return front[idx];  
        } else { //item is in back array
            idx -= frontsize;
            idx += backstart;
            if (idx > back.length-1) {
                idx -= back.length;}
            return back[idx];   
        }
     }   

    /*private static void balance() {
        if (frontsize > backsize) { //move 1 from front to back
            if (backsize == 0) {
            back[backstart] = front[frontend];
            frontend--;
            if (frontend < 0) {
                frontend = front.length+frontend;}
            frontsize--;
            backsize++;
        } else {
            backstart--;
            if (backstart < 0) {
                backstart = back.length+backstart;}
            back[backstart] = front[frontend];
            frontend--;
            if (frontend < 0) {
                frontend = front.length+frontend;}
            frontsize--;
            backsize++; }
        }
        if (backsize - frontsize > 1) { //move 1 from back to front
            if (frontsize == 0) {
                front[frontend] = back[backstart];
                backstart++;
                frontsize++;
                backsize++;
                //System.out.println("doing this!!!!!!!!!!!!!!!!!!");
            } else {
            front[frontend+1] = back[backstart];
            frontend++;
            backstart++;
            frontsize++;
            backsize--; }
        }
    }*/
}
