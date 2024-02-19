package com.cym.javalearning.java.collection;

public class MyHashMap<K,V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Node<K,V>[] table;
    float loadFactor;
    int threshold;
    int MAX_CAPACITY = 1<<30;
    class Node<K,V>{
        private K key;
        private V value;
        private int hash;
        private Node<K,V> next;

        public Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNext() {
            return next;
        }
    }

    class TreeNode<K,V> extends Node<K,V>{
        TreeNode<K,V> parent;
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;
        boolean red;

        public TreeNode(K key, V value, int hash, Node<K, V> next) {
            super(key, value, hash, next);
        }
    }

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(float loadFactor) {
        this.loadFactor = loadFactor;
    }

    public MyHashMap(int initialCapacity, float loadFactor){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illeagal initialCapacity");
        }
        if (initialCapacity > MAX_CAPACITY) {
            initialCapacity = MAX_CAPACITY;
        }
        if (loadFactor < 0||Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illeagal loadFactor");
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0?1:(n>=MAX_CAPACITY?MAX_CAPACITY:n+1);
    }

    public V put(K key, V value){
        return putVal(hash(key), key, value, false);
    }

    private int hash(K key) {
        int h;
        return key == null?0:(h=key.hashCode())^(h >>> 16);
    }

    private V putVal(int hash, K key, V value, boolean onlyIfAbsent){
        Node<K, V>[] tab;int length;Node<K, V> node;Node<K, V> targetNode;
        if ((tab = table) == null||tab.length == 0) {
            tab = resize();
        }
        length = tab.length;
        if ((node = tab[(length-1)&hash]) == null) {
            tab[(length-1)&hash] = new Node<>(key, value, hash, null);
        }
        if (node.key == key
                ||(key != null && node.key.equals(key))) {
            targetNode = node;
        }else if (node instanceof TreeNode){

        }else{
            for (int nodeCount = 0; ; nodeCount++) {
                if (node.next == null) {
                    node.next = new Node<>(key, value, hash, null);
                }
            }
        }

    }

    private Node<K,V>[] resize() {
    }
}
