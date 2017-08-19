#### 1. Two Sum

```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] index=new int[2];
    	int differ=0;
    	for (int i = 0; i < nums.length; i++) {
			differ=target-nums[i];
			for (int j = i+1; j < nums.length; j++) {
				if(differ==nums[j]){
					index[0]=i;
					index[1]=j;
					break;
				}
			}
		}
    	return index;
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] index = new int[2];
        //Arrays.sort(nums);
        int begin = 0;
        int end = nums.length - 1;
        while (begin < end) {
            int sum = nums[begin] + nums[end];
            if (sum == target) {
                index[0] = begin;
                index[1] = end;
                break;
            } else {
                if (sum < target)
                    begin++;
                else
                    end--;
            }
        }
        return index;
    }
}
```

#### 2. Reverse Linked List

```java
public class Solution {
    public static ListNode ReverseList(ListNode head) {
        //空结点时，直接返回null
        if (head == null) 
            return null;
        //只有一个结点时，直接返回单个结点
        else if (head.next == null) 
            return head;
        //链表结点超过1个时，方法通用
        else {
            ListNode p = head.next;
            ListNode pre = head;
            //首结点的指针域需要特殊处理，置空。
            head.next = null;
            //p是当前结点，pre是p的前驱，q是pre的后继。小心操作，以防断链
            while (p != null) {
                ListNode q = p.next;//一定要保存后继结点
                p.next = pre;//修改指针
                pre = p;//更新前驱结点
                p = q;//继续...
            }
            return pre;//结束标记是p为null，于是返回p的前驱pre作为链表。
        }
    }
}
```