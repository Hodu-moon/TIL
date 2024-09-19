package org.example.hashmap.node;

/**
 * HashNode.
 *
 * @author woonseok
 * @Date 2024-09-13
 * @since 1.0
 **/
public class HashNode<K, V> {

    public HashNode<K, V> next;

    K key;
    V value;

    public HashNode(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
