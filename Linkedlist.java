import java.util.*;
public class Linkedlist {
    //24. 两两交换链表中的节点
    //https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(pre.next != null && pre.next.next != null){
            //找出两个要交换的节点
            ListNode first = pre.next;
            ListNode second = pre.next.next;
            //交换
            pre.next = second;
            first.next = second.next;
            second.next = first;
            //更新起点
            pre = first;

        }

        return dummy.next;
    }
    // 25.k个一组节点翻转
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode tmp = head;
        while (true) {
            int count = 0;
            //k个一组
            while (tmp != null && count < k) {
                stack.add(tmp);
                tmp = tmp.next;
                count++;
            }
            //不是k不要反转
            if (count != k) {
                p.next = head;
                break;
            }
            //k组反转
            while (!stack.isEmpty()){
                p.next = stack.pop();
                p = p.next;
            }
            //更新
            p.next = tmp;
            head = tmp;
        }
        return dummy.next;
    }
}
