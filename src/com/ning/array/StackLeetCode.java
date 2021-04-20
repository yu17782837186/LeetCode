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
}
