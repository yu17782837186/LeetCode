package com.ning.array;

public class RecursionLeetCode {
     class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    /**
     time:2021/4/27  104. 二叉树的最大深度
     给定一个二叉树，找出其最大深度。
     二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     说明: 叶子节点是指没有子节点的节点。
     递归:时间复杂度o(n) 空间复杂度0(height) height表示二叉树的高度
     */
    public int maxDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }else {
            int left = maxDepth(node.left);
            int right = maxDepth(node.right);
            return Math.max(left,right)+1;
        }
    }
}
