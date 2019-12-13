import java.util.*;

class ListNode {
    public String item;
    public ListNode next;
    public ListNode last;
    public ListNode(String val) {
    this(val, null); }
    public ListNode(String val, ListNode n) { 
        item = val; 
        next = n;
        last = null; }
    public ListNode getNext() { return next; }
    public String getItem() { return item; } //get the item of the ListNode
    public void setNext(ListNode n) {
        if (last != null) {
            this.last.setNext(n);
        } else {
            this.next = n; 
        }                  
        this.last = n;
    }   
}

public class joinstrings{
    public static ListNode getnode(LinkedList<ListNode> a, String b) {  
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getItem() == b) {
                return a.get(i); }
        }
        return null;
    }

    public static void main(String[] args) {
        
        FastIO fio = new FastIO();
        int nwords = fio.nextInt();
        ListNode[] mynodes = new ListNode[nwords+1];
        for (int x = 1; x <= nwords; x++) { //store the n nodes words into the list "mynodes"
            String newword = fio.next();
            mynodes[x] = new ListNode(newword);  
        }
        ListNode firstwordnode = null;
        int noperations = nwords - 1;
        for (int step = 0; step < noperations; step++) {
            int frontidx = fio.nextInt();
            int backidx = fio.nextInt();
            ListNode frontnode = mynodes[frontidx];
            ListNode backnode = mynodes[backidx]; 
            if (step == noperations-1) {
                firstwordnode = frontnode;
            }
            //while (frontnode.getNext() != null) { // this step is too slow if n is big, introduce tail/end attribute to quickly access the last node of the chunk
            //          frontnode = frontnode.getNext();
            //}
            frontnode.setNext(backnode);
        }   
        if (noperations == 0) {
            fio.print(mynodes[1].item);
            } else {
        for (ListNode currnode = firstwordnode; currnode != null; currnode = currnode.getNext()) {
            fio.print(currnode.item);
        }   
        }   
        fio.close();
    }
}
