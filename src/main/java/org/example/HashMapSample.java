package org.example;

import java.util.Objects;

public class HashMapSample<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;


    Node<K, V>[] table;
    private int size;

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public HashMapSample() {
        table = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
    }

    public V get(K key){
        Node<K,V> node = getNode(hash(key), key);
        return node == null ? null : node.value;
    }

    public V put(K key, V value) {
        return putValue(hash(key), key, value);
    }

    public V remove(Object key) {
        Node<K, V> node = removeNode(hash(key), key);
        return node == null ? null : node.value;
    }

    private int hash(Object key) {
        if (key == null) return 0;
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    private V putValue(int hash, K key, V value) {
        int index = (table.length - 1) & hash;
        Node<K, V> first = table[index];

        for (Node<K, V> node = first; node != null; node = node.next) {
            if (node.hash == hash &&
                    (node.key == key || (key != null && key.equals(node.key)))) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        table[index] = new Node<>(hash, key, value, first);
        size++;

        if (size >= DEFAULT_LOAD_FACTOR * table.length) {
            resize();
        }

        return null;
    }

    private Node<K, V> removeNode(int hash, Object key) {
        int index = (table.length - 1) & hash;
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        while (node != null) {
            if (node.hash == hash &&
                    (node.key == key || (key != null && key.equals(node.key)))) {

                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public Node<K,V> getNode(int hash, Object key){
        int index = (table.length - 1) & hash;
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.hash == hash &&
                    (Objects.equals(key, node.key))) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[oldTable.length << 1];

        for (Node<K, V> head : oldTable) {
            while (head != null) {
                Node<K, V> next = head.next;
                int newIndex = (table.length - 1) & head.hash;
                head.next = table[newIndex];
                table[newIndex] = head;
                head = next;
            }
        }
    }
}
