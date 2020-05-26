package com.leetcode.learn.datastructure;

import java.util.HashMap;

/**
 * LRU算法
 * 哈希表+双向链表实现
 * 需要删除节点必须要知道前驱和后继，所以选双向链表
 * 每一步都是O(1)复杂度
 */
public class LRU {

    // 双向链表节点类
    private static class Node{
        public int key, val;

        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 双向链表类
    private static class DoubleList{
        // 在链表头部添加节点 x，时间 O(1)
        public void addFirst(Node x){}

        // 删除链表中的 x 节点（x ⼀定存在）
        // 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
        public void remove(Node x){}

        // 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
        public Node removeLast(){
            return null;
        }
        // 返回链表⻓度，时间 O(1)
        public int size(){return 0;}

    }

    public static class LRUCacheByOwn{
        // 新建hash表，key -> Node(key,val)
        private HashMap<Integer, Node> map;

        // 新建双向链表,模拟缓存大小
        private DoubleList cache;

        // 最大容量
        private int cap;

        public LRUCacheByOwn(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        // 按照key读取
        public int get(int key){
            // 若缓存中没有则没查到
            if(!map.containsKey(key)){
                return -1;
            }
            int val = map.get(key).val;

            // 将此节点提前
            put(key, val);
            return val;
        }

        // 将节点放入缓存
        public void put(int key, int val){
            Node node = new Node(key, val);

            if(map.containsKey(key)){
                // 更新操作
                // 删除旧的节点，新的插到头部
                cache.remove(map.get(key));
                // 并且将该节点放在链表首位
                cache.addFirst(node);
                // 更新hash表
                map.put(key, node);
            }else {
                if(cache.size() == cap){
                    // 如果容量已满，链表和hash表中都删除最后一个元素
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                // 直接将新数据添加到头部
                cache.addFirst(node);
                // 添加hash
                map.put(key, node);
            }
        }

    }


}
