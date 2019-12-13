import java.util.*;
import java.io.*;

//main class + method
public class gcpc{
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt(); // n is number of teams
        int m = fio.nextInt(); // m is number of events
        BST mytree = new BST();
        HashMap<Integer,Team> insertedteams = new HashMap<Integer,Team>(); //HashMap of <teamid(int),team(Team object)>Basically to get back team 
        Team team1 = new Team(1,0);
        team1.solvedqns = 0;
        mytree.root = new BSTVertex(team1);
        insertedteams.put(1,team1);
        while (m > 0) {
            //System.out.println("tree: " + mytree.treeToString(mytree.root)); //to visualise tree: left branch will appear in () right branch will appear in []
            //System.out.println(" ");
            int thisteamid = fio.nextInt();
            int thispenalty = fio.nextInt();
            if (!insertedteams.containsKey(thisteamid)) {
                Team x = new Team(thisteamid,thispenalty);
                mytree.insert(x);
                insertedteams.put(thisteamid,x);
            } else {
                Team x = insertedteams.get(thisteamid);
                mytree.delete(x);
                x.solvedqns++;
                x.totalpenalty += thispenalty;
                mytree.insert(x);
                insertedteams.replace(thisteamid,x);
            }
            //System.out.println("root is " + mytree.root.key.teamid);
            System.out.println(mytree.rank(insertedteams.get(1))); //swap with fio.println
            m--; 
        }
        fio.close();
    }
}

class BST {
    public static BSTVertex root;

    static String treeToString(BSTVertex root)  { //to visualise tree: left branch will appear in () right branch will appear in []
    String str = "";
    // bases case  
    if (root == null)  
      return str;  
  
    // push the root data as character  
    str += String.valueOf(root.key.teamid);
  
    // if leaf node, then return  
    if (root.left == null && root.right == null)  
      return str;  
  
    // for left subtree  
    str += ('(');   
    str += treeToString(root.left);  
    str += (')');  
  
    // only if right child is present to  
    // avoid extra parenthesis  
    str += ('[');  
    str += treeToString(root.right);  
    str += (']');     
    return str;
    }

    public static int getbalfac(BSTVertex T) {
        if (T == null) {return 0;}
        return getHeight(T.left)-getHeight(T.right);
    }

    private static void setLeftChild(BSTVertex parent, BSTVertex child) {
        parent.left = child;
        if (child != null)
        {
            child.parent = parent;
        }
    }

    private static void setRightChild(BSTVertex parent, BSTVertex child) {
        parent.right = child;
        if (child != null) 
        {
            child.parent = parent;
        }
    }

    private static BSTVertex rotateLeft(BSTVertex T) { //BSTVertex to void
        BSTVertex w = T.right;
        
        if (T == root) {root = w;}
        BSTVertex t2 = w.left;
        BSTVertex parent = T.parent;
        
        if (parent != null) {
            if (parent.key.compareTo(w.key) < 0) { //set correct child side for T.parent
                setRightChild(parent, w);
            }
            else {
                setLeftChild(parent, w);
            }
        }

        setRightChild(T, t2); //Do rotation
        setLeftChild(w, T);
        if (w == root) {w.parent = null;}
        T.size = 1 + getSize(T.left)+getSize(T.right);
        w.size = 1 + getSize(w.left)+getSize(w.right);
        T.height = Math.max(getHeight(T.left),getHeight(T.right)) + 1;
        w.height = Math.max(getHeight(w.left),getHeight(w.right)) + 1;
        return w;
    }

    private static BSTVertex rotateRight(BSTVertex T) { //BSTVertex to void
        BSTVertex w = T.left;

        if (T == root) {root = w;}
        BSTVertex t2 = w.right;
        BSTVertex parent = T.parent;
        
        if (parent != null) {
            if (parent.key.compareTo(w.key) < 0) { //set correct child side for T.parent
                setRightChild(parent, w);
            }
            else {
                setLeftChild(parent, w);
            }
        }

        setLeftChild(T, t2); //Do rotation
        setRightChild(w, T);
        
        if (w == root) {w.parent = null;}
        T.size = 1 + getSize(T.left)+getSize(T.right);
        w.size = 1 + getSize(w.left)+getSize(w.right);
        T.height = Math.max(getHeight(T.left),getHeight(T.right)) + 1;
        w.height = Math.max(getHeight(w.left),getHeight(w.right)) + 1;
        return w;
    }

    public BST() { root = null; }

    public static BSTVertex search(Team v) {
        BSTVertex res = search(root, v);
        return res == null ? null : res; }

    private static BSTVertex search(BSTVertex T, Team v) {
        if (T == null)  return null;                     // not found
        else if (T.key == v) return T;                        // found
        else if (T.key.compareTo(v) < 0)  return search(T.right, v);//T.key < v       // search to the right
        else return search(T.left, v); }     // search to the left

    public static BSTVertex findMin() 
    {
        return findMin(root);
    }
    private static BSTVertex findMin(BSTVertex T) {
        if (T.left == null) 
        {
            return T; // this is the min
        }
        else
        {
            return findMin(T.left);
        }
    }
    
    public static BSTVertex successor(Team v) {
        BSTVertex vPos = search(root, v);
        if (vPos == null)
        {
            return null;
        }
        else
        {
            return successor(vPos);
        }
    }

    private static BSTVertex successor(BSTVertex T) {
        if (T.right != null) // this subtree has right subtree
        {
            return findMin(T.right);  // the successor is the minimum of right subtree
        }
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur == par.right)) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            if (par == null) 
            {
                return null;
            }
            else
            { return par; }    // this is the successor of T
        }                 
    }

    public static int rank(Team v) {
        return rank(root,v);}

    private static int rank(BSTVertex T,Team v) {
        if (T == null) {
            return 0;
        }
        if (T.key == v)  {return getSize(T.left) + 1;}
        else if (T.key.compareTo(v) > 0) {return rank(T.left,v);} //T.key > v
        else {return getSize(T.left) + 1 + rank(T.right,v);}
    }

    public static void insert(Team v) { root = insert(root, v); }

    private static BSTVertex insert(BSTVertex T, Team v) {
        if (T == null) {
            return new BSTVertex(v);  // insertion point is found
        }
        if (T.key.compareTo(v) < 0) {                //T.key<v // search to the right
            T.right = insert(T.right, v);
            T.right.parent = T;
        }
        else {                                                 // search to the left
            T.left = insert(T.left, v);
            T.left.parent = T;
        }
        
        int balfac = getbalfac(T); 
        int lbalfac = getbalfac(T.left); 
        int rbalfac = getbalfac(T.right); 
        // Left Left Case
        if (balfac > 1 && 0 <= lbalfac && lbalfac <= 1) //v<T.left.key
        {
            return rotateRight(T);
        }
        // Right Right Case
        if (balfac < -1 && -1 <= rbalfac && rbalfac <= 0)
        {  
            return rotateLeft(T);        //v > T.right.key
        }
        // Left Right Case
        if (balfac > 1 && lbalfac == -1) //v > T.left.key
        {
            T.left= rotateLeft(T.left);
            return rotateRight(T);
        }
        // Right Left Case
        if (balfac < -1 && rbalfac == -1) //v < T.right.key
        {
            T.right = rotateRight(T.right);
            return rotateLeft(T);
        }
        T.height = 1 + Math.max(getHeight(T.left),getHeight(T.right));
        T.size = 1 + getSize(T.left)+getSize(T.right);
        return T;
    }


    public static void delete(Team v) { root = delete(root, v); }

    private static BSTVertex delete(BSTVertex T, Team v) {
        if (T == null)  return T;              // cannot find the item to be deleted

        if (T.key.compareTo(v) < 0)     //T.key < v                               // search to the right
            T.right = delete(T.right, v);
        else if (T.key.compareTo(v) > 0)       //T.key > v                         // search to the left
            T.left = delete(T.left, v);
        else {                                            // this is the node to be deleted
            if (T.left == null && T.right == null) {                  // this is a leaf
                T = null; }  else if (T.left == null && T.right != null) {                // simply erase this node // only one child at right                                  
                T.right.parent = T.parent;
                T = T.right;                                                 // bypass T
            }
            else if (T.left != null && T.right == null) {    // only one child at left
                T.left.parent = T.parent;
                T = T.left;     }                           // bypass T             
            else {                                 // has two children, find successor
                BSTVertex successorV = successor(v);
                T.key = successorV.key;         // replace this key with the successor's key
                T.right = delete(T.right, successorV.key);      // delete the old successorV
            }
        }
        if (T !=  null) {T.size = 1 + getSize(T.left)+getSize(T.right);
        T.height = 1 + Math.max(getHeight(T.left),getHeight(T.right)); }                                             

        return T;                                          // return the updated BST
    }

    private static int getHeight(BSTVertex T) {
        if (T == null) return 0;
        else return T.height;
    }
    private static int getSize(BSTVertex T) {
        if (T == null) return 0;
        else return T.size;
    }
}

class BSTVertex {
    public BSTVertex(Team v) { key = v; parent = left = right = null; height = 1; size=1;}
    public BSTVertex parent, left, right;
    public Team key;
    public int height,size;
}

class Team implements Comparable<Team>{
    public int solvedqns;
    public int totalpenalty;
    public int teamid;
    public Team(int x,int y) {
        this.teamid = x;
        this.solvedqns = 1;
        this.totalpenalty = y;
    }
    public int compareTo(Team y) { 
        if (y.solvedqns > this.solvedqns) {return 1;}
        else if (y.solvedqns == this.solvedqns) {
            if (this.totalpenalty > y.totalpenalty) {return 1;}
            else if (this.totalpenalty == y.totalpenalty) {return this.teamid-y.teamid;} 
            else {return -1;} 
        }
        else {return -1;}
    }
}
