package com.leetcode.learn.unionfind;

/**
 * 并查集类
 * 数组实现森林
 */

public class UnionFind{
    // 连通分量个数
    private int count;
    // 存储⼀棵树
    private int[] parent;
    // 记录树节点的重量，用于平衡头重脚轻的树
    private int[] size;

    // 并查集节点数
    public UnionFind(int n){
        // 初始连通分量是节点数，即所有节点都不连通
        this.count = n;
        parent = new int[n];
        // size[n] = m 表⽰，以节点 n 为根的那棵树，总共有 m 个节点。
        size = new int[n];

        for(int i = 0; i < n; i++){
            // 初始所有节点的父节点都是自己
            parent[i] = i;
            // 初始所有节点是单节点树其重量都是1
            size[i] = 1;
        }
    }

    // 两个节点连通
    public void union(int p, int q){
        // 寻找两个节点的根节点
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if(rootP == rootQ){
            // 表面PQ两节点根节点相同，已经连通
            return;
        }

        // 开始连通
        // 节点数少的树接到节点数多的树下更平衡
        if(size[rootP] > size[rootQ]){
            // 让某一个节点的根节点的父节点指向另一个节点表示连通
            parent[rootQ] = rootP;
            // 更新rootP的树的节点个数
            size[rootP] += size[rootQ];
        }else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        // 连通树减一
        count--;
    }

    // 判断两个节点是否连通
    public boolean connected(int p, int q){
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        return rootP == rootQ;
    }

    // 返回连通分量个数
    public int count(){
        return count;
    }

    // 返回x的根节点
    private int findRoot(int x){
        // 采用路径压缩
        parent[x] = parent[parent[x]];
        x= parent[x];

        // 根节点的 parent[x] == x,复杂度为O(N)
        /*
        while (parent[x] != x) {
            x = parent[x];
        }
        */
        return x;
    }
}

