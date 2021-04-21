package com.ning.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackLeetCode {
    public static void main(String[] args) {
        System.out.println(bracketMatch(new String("([)]")));
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
}
