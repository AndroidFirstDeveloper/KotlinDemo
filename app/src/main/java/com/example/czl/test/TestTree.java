package com.example.czl.test;

import java.util.ArrayList;
import java.util.List;

public class TestTree {

    static class TreeNode {
        private String value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(String value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode rooth = new TreeNode("h", null, null);
    TreeNode rooti = new TreeNode("i", null, null);
    TreeNode rootd = new TreeNode("d", rooth, rooti);
    TreeNode roote = new TreeNode("e", null, null);
    TreeNode rootb = new TreeNode("b", rootd, roote);
    TreeNode rootf = new TreeNode("f", null, null);
    TreeNode rootg = new TreeNode("g", null, null);
    TreeNode rootc = new TreeNode("c", rootf, rootg);
    TreeNode roota = new TreeNode("a", rootb, rootc);


    public void test(){
        List<List<String>> result= levelView2(roota);
        for(List<String> list:result){
            for(String ss:list){
                System.out.print(ss);
            }
        }
    }
    //深度优先遍历
    private List<List<String>> levelView2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<String>> ans = new ArrayList<>();
        levelView22(root, ans, 1);
        return ans;
    }

    private void levelView22(TreeNode root, List<List<String>> ans, int i) {
        if (i > ans.size()) {  //集合大小不够，需要新增一个子集合
            ans.add(new ArrayList<>());
        }
        ans.get(i - 1).add(root.value);
        if (root.left != null) {
            levelView22(root.left, ans, i + 1);
        }
        if (root.right != null) {
            levelView22(root.right, ans, i + 1);
        }
    }
}
