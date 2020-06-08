package com.leetcode.learn.tree.red_black;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * key-value形式的红黑树的简单实现,该实现并没有借助NIL哨兵节点
 * 主要方法:
 * =====public=====
 * {@link RedBlackTree#RedBlackTree()}  构建红黑树对象，使用自然比较器
 * {@link RedBlackTree#RedBlackTree(Comparator)} 构建红黑树对象，使用自定义比较器
 * {@link RedBlackTree#put(Object, Object)}  存放k-v键值对，将按照key的大小排序
 * {@link RedBlackTree#remove(Object)}  根据key尝试查找并尝试删除对应的键值对
 * {@link RedBlackTree#toInorderTraversalString()} 中序遍历红黑树（顺序输出）
 * {@link RedBlackTree#minKey()} 获取最小的key
 * {@link RedBlackTree#maxKey()} 获取最大的key
 * {@link RedBlackTree#get(Object)} 根据key获取value
 * =====private=====
 * {@link RedBlackTree#binaryTreePut(RedBlackNode, RedBlackNode)}  使用二叉排序树的方式尝试插入节点
 * {@link RedBlackTree#rebalanceAfterPut(RedBlackNode)}  插入节点之后进行重平衡
 * {@link RedBlackTree#searchRemoveNode(Object, RedBlackNode)}  使用二叉排序树的方式尝试寻找真正需要被删除的节点
 * {@link RedBlackTree#removeNode(RedBlackNode)}  使用二叉排序的方式删除节点,并对部分简单情况进行重平衡
 * {@link RedBlackTree#rebalanceAfterRemove(RedBlackNode, RedBlackNode)}  对复杂情况进行重平衡
 * {@link RedBlackTree#rotateLeft(RedBlackNode)}  左旋
 * {@link RedBlackTree#rotateRight(RedBlackNode)}  右旋
 * {@link RedBlackTree#rotateLeftAndRight(RedBlackNode)}  左-右双旋
 * {@link RedBlackTree#rotateRightAndLeft(RedBlackNode)}  右-左双旋
 */
public class RedBlackTree<K, V> {

    // 自定义比较器
    private Comparator<? super K> cmp;

    // 树节点个数
    private int size;

    // 红黑树根节点
    private RedBlackNode<K, V> root;

    private static final class RedBlackNode<K, V> {
        // 节点颜色 true为红，false为黑
        boolean red;
        // 节点的关键字
        K key;
        // 节点的值
        V value;
        // 左子节点
        RedBlackNode<K, V> left;
        // 右子节点
        RedBlackNode<K, V> right;
        // 父节点
        RedBlackNode<K, V> parent;

        public RedBlackNode(boolean red, K key, V value, RedBlackNode<K, V> left, RedBlackNode<K, V> right) {
            this.red = red;
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public RedBlackNode(boolean red, K key, V value) {
            this.red = red;
            this.key = key;
            this.value = value;
        }

        public RedBlackNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public RedBlackNode() {
        }

        public RedBlackNode(RedBlackNode<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    // 自然顺序比较
    public RedBlackTree() {
    }

    // 指定比较器
    public RedBlackTree(Comparator<? super K> cmp) {
        this.cmp = cmp;
    }

    public V get(K Key) {
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        RedBlackNode<K, V> kvRedBlackNode = searchRemoveNode(Key, root);
        if (kvRedBlackNode != null) {
            return kvRedBlackNode.value;
        }
        return null;
    }

    // 判断键是否异常
    private void checkKey(K key) {
        if (key == null) {
            throw new NullPointerException("key为null");
        }
    }

    // 比较
    private int compare(K k1, K k2) {
        if (cmp != null) {
            return cmp.compare(k1, k2);
        } else {
            return ((Comparable<K>) k1).compareTo(k2);
        }
    }

    private RedBlackNode<K,V> findMin(RedBlackNode<K,V> root) {
        if(root.left == null){
            return root;
        }
        return findMin(root.left);
    }

    public K minKey() {
        if (root == null) {
            return null;
        }
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        return findMin(root).key;
    }

    private RedBlackNode<K, V> findMax(RedBlackNode<K, V> root) {
        /*如果该节点没有右子节点，那么该节点就是最小的节点，返回*/
        if (root.right == null) {
            return root;
        }
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        return findMax(root.right);
    }

    public K maxKey() {
        if (root == null) {
            return null;
        }
        /*如果该节点存在左子节点，那么继续向左递归查找*/
        return findMax(root).key;
    }

    private RedBlackNode<K, V> getLeft(RedBlackNode<K, V> parent) {
        return parent == null ? null : parent.left;
    }

    private RedBlackNode<K, V> getRight(RedBlackNode<K, V> parent) {
        return parent == null ? null : parent.right;
    }

    List<RedBlackNode<K, V>> str = new ArrayList<>();

    private void inorderTraversal(RedBlackNode<K, V> root) {

        RedBlackNode<K, V> left = getLeft(root);
        if (left != null) {
            //如果左子节点不为null,则继续递归遍历该左子节点
            inorderTraversal(left);
        }
        //添加数据节点
        str.add(root);
        //获取节点的右子节点
        RedBlackNode<K, V> right = getRight(root);
        if (right != null) {
            //如果右子节点不为null,则继续递归遍历该右子节点
            inorderTraversal(right);
        }
    }

    public String toInorderTraversalString() {
        //如果是空树,直接返回空
        if (root == null) {
            return null;
        }
        //从根节点开始递归
        inorderTraversal(root);
        //获取遍历结果
        String s = str.toString();
        str.clear();
        return s;
    }

    // 右旋
    private void rotateRight(RedBlackNode<K, V> k1) {
        // 获取基点的父节点
        RedBlackNode<K, V> parent = k1.parent;
        //获取k2,k2是k1的左子节点
        RedBlackNode<K, V> k2 = k1.left;
        // k1的左子树为k2的右子树
        k1.left = k2.right;
        //如果k2的右子树不是null树，则更新右子树的父节点
        if (k2.right != null) {
            k2.right.parent = k1;
        }
        //k1成为k2的右子节点
        k2.right = k1;
        //更新k1的父节点的引用
        k1.parent = k2;

        if(parent == null){
            // 如果k1之前是根节点,更新根节点
            this.root = k2;
        }else if(k1 == parent.left){
            // 如果基点是它父节点的左子节点,更新其为k2
            parent.left = k2;
        }else {
            parent.right = k2;
        }

        // 更新k2的父节点
        k2.parent = parent;
    }

    // 左旋
    private void rotateLeft(RedBlackNode<K, V> k1) {
        // 获取基点的父节点
        RedBlackNode<K, V> parent = k1.parent;
        //获取k2,k2是k1的右子节点
        RedBlackNode<K, V> k2 = k1.right;
        // k1的右子树为k2的左子树
        k1.right = k2.left;
        //如果k2的左子树不是null树，则更新右子树的父节点
        if (k2.left != null) {
            k2.left.parent = k1;
        }
        //k1成为k2的右子节点
        k2.left = k1;
        //更新k1的父节点的引用
        k1.parent = k2;

        if(parent == null){
            // 如果k1之前是根节点,更新根节点
            this.root = k2;
        }else if(k1 == parent.left){
            // 如果基点是它父节点的左子节点,更新其为k2
            parent.left = k2;
        }else {
            parent.right = k2;
        }
        // 更新k2的父节点
        k2.parent = parent;

    }

    // 左 - 右旋
    private void rotateLeftAndRight(RedBlackNode<K, V> k1) {
        // 先对k1的左孩子节点左旋
        rotateLeft(k1.left);
        // 再对k1右旋
        rotateRight(k1);
    }

    private void rotateRightAndLeft(RedBlackNode<K, V> k1){
        // 先对k1的右孩子节点进行右旋
        rotateRight(k1.right);
        // 再对k1进行左旋
        rotateLeft(k1);
    }


    // 按键值对插入节点
    // 1、按照二叉搜索树插入节点
    // 2、重新着色
    public void put(K key, V value){
        // 检查key是否为空
        checkKey(key);
        // 插入
        // 默认插入进去的节点为红色,无左右子节点
        RedBlackNode<K, V> kvRedBlackNode = new RedBlackNode<>(true, key, value, null, null);
        // 取出原始的节点个数
        int oldSize = size;
        // 使用二叉搜索树插入节点
        root = binaryTreePut(kvRedBlackNode, root);
        // 如果size发生了变化，说明确实插入了节点，此时开始重新着色
        if(size > oldSize){
            rebalanceAfterPut(kvRedBlackNode);
        }
    }

    // 二叉排序树的方式插入节点，同时记录父节点
    private RedBlackNode<K,V> binaryTreePut(RedBlackNode<K,V> kvRedBlackNode, RedBlackNode<K,V> root) {
        if(root == null){
            // 当为空树时
            size++;
            return kvRedBlackNode;
        }

        int i = compare(kvRedBlackNode.key, root.key);

        if(i > 0){
            // 递归将节点插入root的右子树,root不断更新，所以节点的父节点也不断再变
            kvRedBlackNode.parent = root;
            root.right = binaryTreePut(kvRedBlackNode, root.right);
        }else if(i < 0){
            // 递归将节点插入root的左子树
            kvRedBlackNode.parent = root;
            root.left = binaryTreePut(kvRedBlackNode, root.left);
        }else {
            // 已经存在相同key的节点，只需要将value替换就行
            root.value = kvRedBlackNode.value;
        }
        return root;
    }

    /**
     * 添加节点之后再平衡，需要分多种情况讨论:
     * 1)	新插入节点N作为根基点，简称“新根”；
     * 2)	新插入节点N的父节点为黑色，简称“父黑”；
     * 3)	新插入节点N的父节点为红色，叔节点为黑色，简称“父红叔黑”；
     * 4)	新插入节点N的父节点为红色，叔节点为红色，简称“父红叔红”；
    */
    private void rebalanceAfterPut(RedBlackNode<K,V> newNode) {
        // 获取父节点
        RedBlackNode<K, V> parent = newNode.parent;
        if(parent == null){
            // 如果父节点为空，说明此时只有一个节点在插入,直接将节点涂黑
            newNode.red = false;
            return;
        }else if(! parent.red){
            // 父黑情况无需调整
            return;
        }

        // 父节点为红色的情况
        // 获取祖父节点
        RedBlackNode<K, V> grandParent = parent.parent;
        // 获取叔叔节点
        RedBlackNode<K, V> uncle = parent == grandParent.left ? grandParent.right : grandParent.left;
        if(uncle != null && uncle.red){
            // 父红叔红的情况
            // 父节点叔叔节点全部染成黑，祖父节点染红，让祖父节点去和曾祖父节点以上的节点递归更改颜色
            parent.red = false;
            uncle.red = false;
            grandParent.red = true;
            // 对曾祖父节点进行递归
            rebalanceAfterPut(grandParent);
        }else {
            // 父红叔黑
            if(grandParent.left == parent && parent.left == newNode){
                // LL型，全在左子树操作，右旋
                // 父节点涂黑，祖父节点变红
                parent.red = false;
                grandParent.red = true;
                // 以祖父节点为基点进行右旋
                rotateRight(grandParent);
            }else if(grandParent.left == parent && parent.right == newNode){
                // LR型 父节点是祖父的左子节点，子节点是父节点的右子节点
                // 先按父节点左旋，再按子节点右旋
                // 子节点涂黑，祖父节点涂红
                newNode.red = false;
                grandParent.red = true;
                rotateLeftAndRight(grandParent);
            }else if(grandParent.right == parent && parent.left == newNode){
                // RL型
                newNode.red = false;
                grandParent.red = true;
                /*右-左双旋*/
                rotateRightAndLeft(grandParent);
            }else {
                // RR型
                /*父节点涂黑，祖父节点涂红*/
                parent.red = false;
                grandParent.red = true;
                /*以祖父节点为基点左旋*/
                rotateLeft(grandParent);
            }
        }
    }

    // 按照键来删
    public V remove(K key){
        checkKey(key);
        // 查找需要删除的节点
        RedBlackNode<K, V> removeNode = searchRemoveNode(key, root);
        if(removeNode == null){
            return null;
        }

        V value = removeNode.value;
        RedBlackNode<K, V> n = removeNode(removeNode);
        if(n != null){
            // 被删除节点N是黑色节点且需要进行进一步的重平衡
            rebalanceAfterRemove(n.right, n.parent);
        }
        // 返回被删除的元素值
        return value;
    }

    /**
     * 用于删除节点并且进行部分重平衡：
     * 对于“删红”，“删黑子红”，“删黑子黑-删根”这三种简单的情况进行了判断和重平衡
     * @param n 要么没有子节点，要么只有一个子节点
     * @return 还需要进一步被重平衡操作的节点，或者null-表示不需要进一步重平衡操作
     */
    private RedBlackNode<K,V> removeNode(RedBlackNode<K,V> n) {
        // 左右子节点，父亲节点
        RedBlackNode<K, V> left = n.left;
        RedBlackNode<K, V> right = n.right;
        RedBlackNode<K, V> parent = n.parent;
        RedBlackNode<K, V> child = null;

        if(parent != null){
            if(parent.left == n){
                // 如果n是父节点的左子节点
                if(left == null && right == null){
                    // 即将n删掉
                    parent.left = null;
                    child = null;
                }else if (left != null) {
                    //如果n的左子节点不为null
                    parent.left = left;
                    left.parent = parent;
                    child = left;
                }else {
                    // 如果n的右子节点不为null
                    parent.left = right;
                    right.parent = parent;
                    child = right;
                }
            }
            if(parent.right == n){
                // 如果n是父节点的右子节点
                if (left == null && right == null) {
                    //如果n的左右子节点都为null
                    parent.right = null;
                    child = null;
                }
                else if (left != null) {
                    //如果n的左子节点不为null
                    parent.right = left;
                    left.parent = parent;
                    child = left;
                }
                else {
                    //如果n的右子节点不为null
                    parent.right = right;
                    right.parent = parent;
                    child = right;
                }
            }
        }else {
            // 如果父节点等于null
            if (left == null && right == null) {
                //如果n的左右子节点都为null
                root = null;
                child = null;
            }
            else if (left != null) {
                //如果n的左子节点不为null
                left.parent = null;
                root = left;
                child = left;
            }
            else {
                //如果n的右子节点不为null
                right.parent = null;
                root = right;
                child = right;
            }
        }

        if(n.red){
            // 被删除节点的颜色为红色
            // 删除N后不会影响红黑树的平衡性，与其他节点的颜色无关
            return null;
        }else if(child != null && child.red){
            // 被删除的节点为黑色，子节点为红色
            child.red = false;
            return null;
        }else {
            if(n.parent == null){
                // 被删除节点N是根节点
                return null;
            }
            //将n的子节点(null或左子节点或右子节点)设置为右子节点,方便后面调整的时候取出来
            n.right = child;
            return n;
        }

    }

    /**
     *
     * @param c 需要进行平衡的节点
     * @param p 需要进行平衡的节点的父节点
     */
    private void rebalanceAfterRemove(RedBlackNode<K,V> c, RedBlackNode<K,V> p) {
        // 获取兄弟节点
        RedBlackNode<K, V> brother;
        if(c == p.left){
            // 如果c是左子节点，则brother是右兄弟
            brother = p.right;
            if(brother != null && brother.red){
                // 如果兄弟节点是红色
                // 删兄红
                // 以父节点p作为左旋基点
                rotateLeft(p);
                // 兄弟节点涂黑，父节点变红
                brother.red = false;
                p.red = true;
                // 当成新兄弟
                brother = p.right;
            }
            if(brother != null){
                // 如果被删除节点N的兄弟节点为黑色，简称删兄黑
                if(!(brother.right == null && brother.left == null)){
                    if (!(brother.right != null && !brother.right.red && brother.left != null && !brother.left.red)) {
                        // 都为黑
                        if(brother.right == null || !brother.right.red){
                            // 兄弟节点在右边，且兄右子节点为黑色
                            if (brother.left != null) {
                                brother.left.red = false;
                            }
                            brother.red = true;
                            /*以兄弟节点B为基点右旋*/
                            rotateRight(brother);
                            /*将BL当成新B，B当成新BR，这样就转换成了情况2。*/
                            brother = p.right;
                        }
                        /*3.3.1.2	兄弟节点在右边，且兄右子节点为红色；*/
                        if (brother.right != null && brother.right.red) {
                            /*交换P和B的颜色*/
                            boolean color = p.red;
                            p.red = brother.red;
                            brother.red = color;
                            /*BR涂黑*/
                            brother.right.red = false;
                            /*以父节点P为基点左旋*/
                            rotateLeft(p);
                            return;
                        }
                    }
                }
            }
        }else {
            // 如果c是右子节点,那么brother就是左兄弟
            brother = p.left;
            if (brother != null && brother.red) {
                /*以父节点P为基点右旋，然后B涂黑P涂红（交换颜色），最后将BR看成新的兄弟节点newB；
                 * 将情况二转换为情况三*/
                /*以父节点P为基点右旋*/
                rotateRight(p);
                /*然后B涂黑P涂红（交换颜色）*/
                brother.red = false;
                p.red = true;
                /*最后将BR看成新的兄弟节点newB；将情况二转换为情况三*/
                brother = p.left;
            }
            if(brother != null){
                if (!(brother.right == null && brother.left == null)) {
                    if (!(brother.right != null && !brother.right.red && brother.left != null && !brother.left.red)) {
                        if (brother.left == null || !brother.left.red) {
                            if (brother.right != null) {
                                brother.right.red = false;
                            }
                            brother.red = true;
                            /*以兄弟节点B为基点左旋*/
                            rotateLeft(brother);
                            /*将BR当成新B，B当成新BL，这样就转换成了情况4。*/
                            brother = p.left;
                        }
                        if (brother.left != null && brother.left.red) {
                            /*交换P和B的颜色*/
                            boolean color = p.red;
                            p.red = brother.red;
                            brother.red = color;
                            /*BL涂黑*/
                            brother.left.red = false;
                            /*以父节点P为基点右旋*/
                            rotateRight(p);
                            return;
                        }
                    }
                }
            }
        }

        if(brother == null){
            /*如果父节点还有父节点，那么进行递归，否则没有意义*/
            if (p.parent != null && !p.red) {
                rebalanceAfterRemove(p, p.parent);
            }
            /*3.3.2.2	父节点P是红色；*/
            else {
                p.red = false;
            }
        }else {
            //都为null
            if (brother.left == null && brother.right == null) {
                if (p.parent != null && !p.red) {
                /*将兄弟节点B涂红，将父节点P设为新的C节点，将U设为新B节点，将G设为新P节点，
                回到删黑子黑的情况，即向上递归进行处理，直到C成为根节点或者达到平衡。*/
                    brother.red = true;
                    rebalanceAfterRemove(p, p.parent);
                }
                /*3.3.2.2	父节点P是红色；*/
                else {
                    brother.red = true;
                    p.red = false;
                }
            }else if(brother.left != null && brother.right != null && !brother.left.red && !brother.right.red){
                ///*如果父节点还有父节点，那么进行递归，否则没有意义*/
                if (p.parent != null && !p.red) {
                /*将兄弟节点B涂红，将父节点P设为新的C节点，将U设为新B节点，将G设为新P节点，
                回到删黑子黑的情况，即向上递归进行处理，直到C成为根节点或者达到平衡。*/
                    brother.red = true;
                    rebalanceAfterRemove(p, p.parent);
                }else {
                    brother.red = true;
                    p.red = false;
                }
            }
        }

    }

    // 寻找需要真正被删除的Node节点
    private RedBlackNode<K,V> searchRemoveNode(K key, RedBlackNode<K,V> root) {
        if(root == null){
            return null;
        }

        // 开始比较
        int i = compare(key, root.key);
        if(i > 0){
            // 在右子树
            return searchRemoveNode(key, root.right);
        }else if(i < 0){
            // 在左子树
            return searchRemoveNode(key, root.left);
        }else {
            // 说明查到了，开始做好删除的准备
            size --;
            if(root.left != null && root.right != null){
                // 如果两个子节点都不为空，则直接将所有子节点中最小的与当前根节点值进行交换，再删除最小节点即可
                // 在右子树里面查找最小的
                RedBlackNode<K, V> min = findMin(root.right);
                // 开始交换最小值和root的键和值
                K tempKey = min.key;
                min.key = root.key;
                root.key = tempKey;

                V tempValue = min.value;
                min.value = root.value;
                root.value = tempValue;
                return min;
            }else {
                // 如果一个子节点不为null，则返回该子节点；或者两个子节点都为null，则返回该节点
                return root;
            }
        }
    }

}
