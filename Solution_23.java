import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_23 {
    // 方法1.分治法
    /*
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len < 1)    return null;
        if(len == 1)   return lists[0];
        while(len > 1) {
            for (int i=0; i<len/2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[len-1-i]);
            }
            len = (len + 1) / 2;
        }
        return lists[0];

    }
    private ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }
            else{
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }
        return head.next;

    }*/
    //2.最小优先队列
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len < 1)    return null;
        if(len == 1)   return lists[0];
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        /*
        PriorityQueue <ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        }) ;
        */
        PriorityQueue <ListNode> pq = new PriorityQueue<>((ListNode o1, ListNode o2) -> o1.val - o2.val);
        for(ListNode list :lists){
            if(list == null){
                continue;
            }
            pq.add(list);
        }
        while(!pq.isEmpty()){
            ListNode nextNode = pq.poll();
            cur.next = nextNode;
            cur = cur.next;
            if(nextNode.next != null){
                pq.add(nextNode.next);
            }
        }
        return head;


    }
}
