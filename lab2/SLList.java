public class SLList {
    /** PLEASE DON'T MESS UP FIRST! */
    public IntNode sentinel;
    public int size;

    /** Nested class. */
    private class IntNode {
        public int item;
        private IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    /** Creates an empty SLList. */
    public  SLList(){
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x){
        //first = new IntNode(x, null);
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Creates a list from an array. */
    public SLList(int[] array){
        sentinel = new IntNode(63, null);
        size = 0;

        for(int i = array.length - 1; i >= 0; i--){
            this.addFirst(array[i]);
        }
    }

    /** Return the first item. */
    public int getFirst(){
        return sentinel.next.item;
    }

    /** Add x as the first item. */
    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the size of the List. */
    public int size(){
        return size;
    }

    /** Adds an item to the end of this list. */
    public void addLast(int x){
        size += 1;

        IntNode ptr = sentinel;
        while(ptr.next != null){
            ptr = ptr.next;
        }

        ptr.next = new IntNode(x,null);
    }

    /** Returns the last item int the list. */
    public int getLast(){
        IntNode ptr;
        ptr = sentinel;
        while(ptr.next != null){
            ptr = ptr.next;
        }

        return ptr.item;
    }

    /** Delete the last item in the list. */
    public void deleteFirst(){
        if(sentinel.next == null){
            System.out.println("There is no item in this list now.");
            return;
        }

        sentinel.next = sentinel.next.next;
        size -= 1;
    }

    /** Adds two numbers together if there are 2 in this list. */
    public void addAdjacent(){
        IntNode p = sentinel.next;
        while(p.next != null){
            IntNode j = p;
            while(j.next != null){
                /** If exits*/
                if(j.next.item == p.item){
                    j.next = j.next.next;
                    p.item *= 2;
                    size -= 1;
                    j = p;
                    continue;
                }
                j = j.next;
            }
            p = p.next;
        }
    }

    public static void main(String[] args){
        /** Creates a list from a array. */
        int[] array = new int[]{2,4,8,16,7,7,8,9,10};
        SLList L = new SLList(array);
        L.addAdjacent();

        System.out.println("The size of this list should be 6, which you give : " + L.size());
    }
}