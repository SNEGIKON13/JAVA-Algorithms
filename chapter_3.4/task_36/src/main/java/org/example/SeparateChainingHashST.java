package org.example;

import java.util.ArrayList;
import java.util.Random;

public class SeparateChainingHashST<K, V> {
    private int size = 0;
    private ArrayList<SequentialSearchST<K, V>> list;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int size){
        this.size = size;
        list = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            list.add(new SequentialSearchST<>());
        }
    }

    private int hash(K key){
        return (31 * key.hashCode()) % size;
    }

    public V get(K key){
        return list.get(hash(key)).get(key);
    }

    public void put(K key, V val){
        list.get(hash(key)).put(key, val);
    }

    public int minSizeChain(){
        if(size > 0){
            int chainSize = list.get(0).size();
            for(int i = 1; i < size; i++){
                if(chainSize > list.get(i).size()){
                    chainSize = list.get(i).size();
                }
            }
            return chainSize;
        }
        return 0;
    }

    public int maxSizeChain(){
        if(size > 0){
            int chainSize = list.get(0).size();
            for(int i = 1; i < size; i++){
                if(chainSize < list.get(i).size()){
                    chainSize = list.get(i).size();
                }
            }
            return chainSize;
        }
        return 0;
    }

    public static void main(String[] args){
        SeparateChainingHashST<Integer, Integer> hashST = new SeparateChainingHashST<>(100);
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            hashST.put(random.nextInt(10000),i);
        }
        System.out.println(hashST.minSizeChain());
        System.out.println(hashST.maxSizeChain());
    }
}
