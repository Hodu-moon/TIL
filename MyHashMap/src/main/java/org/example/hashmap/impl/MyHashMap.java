package org.example.hashmap.impl;

import com.nhnacademy.hashmap.IMap;
import com.nhnacademy.hashmap.node.HashNode;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MyHashMap.
 *
 * @author woonseok
 * @Date 2024-09-13
 * @since 1.0
 **/
public class MyHashMap<K, V> implements IMap<K, V> {

    static final int LENGTH = 31;
    //Todo 1. HashNode를 이용하여 구현합니다.

    static AtomicInteger aInt;

    MyHashMap(){
        array = new HashNode[LENGTH];
        aInt = new AtomicInteger(0);
    }

    HashNode<K, V>[] array;

    @Override
    public V put(K key, V value) {
        int keyHash = key.hashCode() % LENGTH;


        HashNode<K, V> newNode = new HashNode<>(key, value);
        if(array[keyHash] == null) {
            array[keyHash] = newNode;
        }else {
            HashNode<K, V> node = array[keyHash];

            while (node != null) {
                if (node.getKey().equals(key) && node.getValue().equals(value)) {
                    throw new IllegalArgumentException();
                }
                if (node.next == null) {
                    node.next = newNode;
                    break;
                }
                node = node.next;
            }
        }


        aInt.getAndIncrement();

        return value;
    }



    @Override
    public void clear() {
        Arrays.fill(array,null);
        aInt.getAndSet(0);
    }

    @Override
    public boolean containsKey(K key) {
        int keyHash = key.hashCode() % LENGTH;

        if(array[keyHash] == null){
            return false;
        }else{
            for(HashNode<K, V> node = array[keyHash] ; node != null ;node = node.next){
                if(node.getKey().equals(key)){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(HashNode<K, V> node : array){
            if(node != null){
                for(HashNode<K, V> node2 = node ; node2 != null ;node2 = node2.next){
                    if(node2.getValue().equals(value)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {

        if(!containsKey(key)){
            throw new IllegalArgumentException();
        }

        int key_hash = key.hashCode() % LENGTH;

        HashNode<K, V> findNode = array[key_hash];
        while(findNode != null){

            if(findNode.getKey().equals(key)){
                return findNode.getValue();
            }


            findNode = findNode.next;
        }

        return null;

    }

    @Override
    public boolean isEmpty() {
        return aInt.get() == 0;
    }



    @Override
    public void remove(K key) {
        if(!containsKey(key)){
            throw new IllegalArgumentException();
        }
        int keyHash = key.hashCode() % LENGTH;

        if(array[keyHash].getKey().equals(key)){
            array[keyHash] = null;
            aInt.getAndDecrement();
        }else{
            HashNode<K, V> prev = array[keyHash];
            HashNode<K, V> node = array[keyHash].next;
            while(node != null){

                if(node.getKey().equals(key)){
                    prev.next = node.next;
                    aInt.getAndDecrement();
                    break;
                }
                prev = node;
                node = node.next;
            }
        }
    }

    @Override
    public int size() {
        return aInt.get();
    }
}
