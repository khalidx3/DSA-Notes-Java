public class LinkedList{
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        //create new node
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head = tail = newNode;
            return;
        }
        //newNode next = head
        newNode.next = head;
        head = newNode;
    }
    public void addLast(int data){
        Node newNode = new Node(data);
        size++;

         if(head==null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }
    public void printll(){
        if(head == null){
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }
    public void add(int idx, int data){
        if(idx==0){
            addFirst(data);
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i =0;
        while(i<idx-1){
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next=newNode;
    }
    public int removeFirst(){
        if(size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val = head.data;
            head=tail=null;
            size =0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }
    public int removeLast(){
        if(size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val = head.data;
            head=tail=null;
            size =0;
            return val;
        }
        //prev = size-2
        Node prev = head;
        for (int i = 0; i < size-2; i++) {
            prev= prev.next;
            
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }
    public int itrSearch(int key){
        Node temp = head;
        int i =0;
        while(temp!=null){
            if(temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        return -1;
    }
    public int helper(Node head, int key){
        if(head==null){
            return -1;
        }
        if(head.data==key){
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx==-1){
            return -1;
        }
        return idx +1;
    }
    public int recSearch(int key){
        return helper(head, key);
    }
    public void reverse(){
        Node prev = null;
        Node curr = tail =head;
        Node next;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;

    }
    public void deleteNthfromEnd(int n){
        //calculate size
        int sz = 0;
        Node temp = head;
        while(temp!=null){
            temp=temp.next;
            sz++;
        }
        if(n==sz){
            head = head.next; //remove first
            return;
        }
        //sz-4
        int i = 1;
        int iToFind = sz-n;
        Node prev = head;
        while(i<iToFind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        return;
    }
    public boolean checkPalindrome(){
        if(head==null||head.next==null){
            return true;
        }
        //step 1 - find mid
        Node midNode = findMid(head);

        //step 2 - reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while(curr!=  null){
            next = curr.next;
            curr.next = prev;
            prev=curr;
            curr = next;
        }
        Node right = prev; //right half head
        Node left = head;

        //step 3 - check left half & rigth half
        while(right!=null){
            if(left.data!=right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
    public Node findMid(Node head){
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next; //+1
            fast = fast.next.next; //+2
        }
        return slow; //slow is my midNode
    }
    public static boolean isCycle(){
    Node slow = head;
    Node fast = head;
    while(fast!=null && fast.next!=null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow==fast){
            return true;
        }

    }
    return false;
    }
    public static void removeCycle(){
        //detect cycle
        Node slow = head;
        Node fast = head;

        boolean cycle = false;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                cycle = true;
                break;
            }
        }
        if(cycle == false){
            return;
        }
        //find meeting point
        slow = head;
        Node prev = null; //last node
        while(slow!=fast){
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        //remove cycle -> last.next=null
        prev.next = null;
    }
    private Node getMid(Node head){
        Node slow = head;
        Node fast = head.next;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; //mid node
    }
    private Node merge(Node head1, Node head2){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;

        while(head1!=null && head2!=null){
            if(head1.data<=head2.data){
                temp.next=head1;
                head1 = head1.next;
                temp = temp.next;
            }else{
                temp.next = head2;
                head2=head2.next;
                temp =temp.next;
            }
        }
        while(head1!=null){
            temp.next=head1;
            head1=head1.next;
            temp = temp.next;
        }
        while(head2!=null){
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
        }
        return mergedLL.next;
    }
    public Node mergeSort(Node head){
        if(head == null || head.next== null){
            return head;
        }
        //find mid 
        Node mid = getMid(head);

        //left and rigth Ms
        Node righthead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRigth = mergeSort(righthead); 

        //merge 
        return merge(newLeft,newRigth);
    }
    
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addFirst(1);
        // ll.addFirst(2);
        ll.addLast(3);
        ll.addLast(2);
        ll.printll();
        ll.head = ll.mergeSort(ll.head);
        ll.printll();
        // ll.add(2, 10);
        // ll.printll();
        // ll.removeFirst();
        // ll.printll();
        // System.out.println(ll.size);

        // ll.removeLast();
        // ll.printll();
        // System.out.println(ll.size);

        // System.out.println(ll.recSearch(4));
        // System.out.println(ll.recSearch(10));

        // ll.reverse();
        // ll.printll();

        // ll.deleteNthfromEnd(2);
        // ll.printll();
        // System.out.println(ll.checkPalindrome());

        //cycle 
        // head = new Node(1);
        // Node temp = new Node(2);
        // head.next = temp;
        // head.next.next = new Node(3);
        // head.next.next.next = temp;
        // //1-2-3-1
        

        // System.out.println(isCycle());
        // removeCycle();
        // System.out.println(isCycle());
    
    }

}