package com.leetcode.learn.datastructure;

/**
 * 二叉堆（最大堆）
 * 即优先级队列的主要数据结构
 * 包括上浮swim,下沉sink,插入节点insert ，删除最大值delMax
 */
public class MaxPQ<Key extends Comparable<Key>>{

    // 存储元素的数组
    private Key[] pq;

    // 当前 Priority Queue 中的元素个数
    private int N = 0;

    public MaxPQ(int cap) {
        // 索引 0 不⽤，所以多分配⼀个空间
        pq = (Key[]) new Comparable[cap + 1];
    }

    /* 返回当前队列中最⼤元素 */
    public Key max() {
        return pq[1];
    }

    // node的父节点索引
    public int parent(int node_index){
        return node_index / 2;
    }
    // node的左孩子节点索引
    public int left(int node_index){
        return 2 * node_index;
    }
    // node的右孩子节点索引
    public int right(int node_index){
        return 2 * node_index + 1;
    }

    /* 交换数组的两个元素 */
    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否⽐ pq[j] ⼩？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swim(int k) {
        // 如果浮到堆顶，就不能再上浮了
        while (k > 1 && less(parent(k), k)) {
        // 如果第 k 个元素⽐上层⼤
        // 将 k 换上去
            exch(parent(k), k);
            k = parent(k);
        }
    }

    private void sink(int k) {
        // 如果沉到堆底，就沉不下去了
        while (left(k) <= N) {
            // 先假设左边节点较⼤
            int older = left(k);
            // 如果右边节点存在，⽐⼀下⼤⼩
            if (right(k) <= N && less(older, right(k))) {
                older = right(k);
            }
            // 结点 k ⽐俩孩⼦都⼤，就不必下沉了
            if (less(older, k)) {
                break;
            }
            // 否则，不符合最⼤堆的结构，下沉 k 结点
            exch(k, older);
            k = older;
        }
    }

    public void insert(Key e) {
        N++;
        // 先把新元素加到最后
        pq[N] = e;
        // 然后让它上浮到正确的位置
        swim(N);
    }

    public Key delMax() {
        // 最⼤堆的堆顶就是最⼤元素
        Key max = pq[1];
        // 把这个最⼤元素换到最后，删除之
        exch(1, N);
        pq[N] = null;
        N--;
        // 让 pq[1] 下沉到正确位置
        sink(1);
        return max;
    }

}
