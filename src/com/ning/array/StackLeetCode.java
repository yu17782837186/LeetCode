package com.ning.array;

import java.util.*;

public class StackLeetCode {
    public static void main(String[] args) {
//        System.out.println(bracketMatch(new String("([)]")));

//        System.out.println(compareString(new String("ab#c"),new String("ad#c")));

        System.out.println(removeDuplicate(new String("abbaca")));
    }
    /**
     time:2021/4/20  20. 有效的括号
     给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     有效字符串需满足：
     左括号必须用相同类型的右括号闭合。
     左括号必须以正确的顺序闭合。
     栈 时间复杂度：o(n) n是字符串的长度 空间复杂度：o(n+字符串数量) n为栈中字符串的数量 字符串数量是哈希表使用的空间
     */
    public static boolean bracketMatch(String s) {
        int len = s.length();
        if(len < 2) {
            return false;
        }
        Map<Character,Character> map = new HashMap<>();//将需要匹配的右括号作为Key value为左括号
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < len;i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {//字符串里出现右括号(根据map是否包含Key判断)
                if(stack.isEmpty() || stack.peek() != map.get(c)) {//如果对应出现右括号的map的value不等于栈顶说明不匹配 直接return
                    return false;
                }
                stack.pop();//对应出现右括号的map的value匹配栈顶 则出栈
            }else {//如果字符串里面没有出现右括号 直接push
                stack.push(c);
            }
        }
        return stack.isEmpty();//返回栈是否为空
    }
    /**
     time:2021/4/21  155. 最小栈
     设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     push(x) —— 将元素 x 推入栈中。
     pop() —— 删除栈顶的元素。
     top() —— 获取栈顶元素。
     getMin() —— 检索栈中的最小元素。
     最小栈 时间复杂度：o(1) 空间复杂度o(n) n为连续插入n个元素
     */
    class minStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        public void setMinStack() {
            minStack.push(Integer.MAX_VALUE);
        }
        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(minStack.peek(),val));
        }
        public void pop() {
            stack.pop();
            minStack.pop();
        }
        public int top() {
            return stack.peek();
        }
        public int getMin() {
            return minStack.peek();
        }
    }
    /**
     time:2021/4/22  225. 用队列实现栈
     请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。
     实现 MyStack 类：
     void push(int x) 将元素 x 压入栈顶。
     int pop() 移除并返回栈顶元素。
     int top() 返回栈顶元素。
     boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     注意：
     你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
     你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     队列实现栈 时间复杂度：o(1) 空间复杂度o(n) n为队列中的元素
     */
    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }
    /**
     time:2021/4/23 232. 用栈实现队列
     请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     实现 MyQueue 类：
     void push(int x) 将元素 x 推到队列的末尾
     int pop() 从队列的开头移除并返回元素
     int peek() 返回队列开头的元素
     boolean empty() 如果队列为空，返回 true ；否则，返回 false
     说明：
     你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     队列实现栈 时间复杂度：o(1) 空间复杂度o(n) n为栈内的元素
     */
    class MyQueue{
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }
        //入队列
        public void push(int val) {
            stack1.push(val);//入队列
        }
        //模拟出队列
        public int pop() {
            if(stack2.isEmpty()) {//栈2为空
                while(!stack1.isEmpty()) {//栈1不为空 栈1出栈 入栈2
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();//栈2出栈即为出队列的情况
        }
        //返回栈顶元素
        public int peek() {
            if(stack2.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }
        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
    /**
     time:2021/4/24 496. 下一个更大元素 I
     给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[] res = new int[len1];
        if (len1 < 1) {
            return res;
        }

        for (int i = 0; i < len1; i++) {
            int curVal = nums1[i];
            int j = 0;
            while (j < len2 && nums2[j] != curVal) {
                j++;
            }

            // 此时 nums[j] = nums[i]
            j++;
            while (j < len2 && nums2[j] < curVal) {
                j++;
            }

            if (j == len2) {
                res[i] = -1;
                continue;
            }
            res[i] = nums2[j];
        }
        return res;
    }
    /**
     time:2021/4/24 496. 下一个更大元素 I
     给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     */
    public static boolean compareString(String s,String t) {
        return getPureString(s).equals(getPureString(t));
    }
    private static String getPureString(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < len;i++) {
            char ch = s.charAt(i);
            if(ch != '#') {
                sb.append(ch);
            }else {
                if(sb.length() > 0) {
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
        return sb.toString();
    }
    /**
     time:2021/4/25 1047. 删除字符串中的所有相邻重复项
     给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     在 S 上反复执行重复项删除操作，直到无法继续删除。
     在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     栈：时间复杂度:o(n) 空间复杂度:o(1)
     */
    public static String removeDuplicate(String s) {
        StringBuilder sb = new StringBuilder();//StringBuilder具有栈的作用
        int top = -1;//栈顶
        for(int i = 0;i < s.length();i++) {//便利字符串
            char ch = s.charAt(i);
            if(top >= 0 && sb.charAt(top) == ch) {//栈顶元素与当前字符相等 就删除 并且top--
                sb.deleteCharAt(top);
                top--;
            }else {//如果栈顶元素与当前字符不等  入栈 top++
                sb.append(ch);
                top++;

            }
        }
        return sb.toString();
    }
}
