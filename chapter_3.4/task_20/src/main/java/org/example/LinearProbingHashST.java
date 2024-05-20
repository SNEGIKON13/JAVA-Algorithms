package org.example;

import java.util.ArrayList;
import java.util.Optional;

public class LinearProbingHashST<K, V>{
    private int pairs = 0;
    private int size = 31;
    private ArrayList<K> keys;
    private ArrayList<V> vals;

    public LinearProbingHashST(){
        keys = new ArrayList<>(size);
        vals = new ArrayList<>(size);
    }

    public LinearProbingHashST(int size){
        keys = new ArrayList<>(size);
        vals = new ArrayList<>(size);
        this.size = size;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % size;
    }
    
    private void resize(int capacity) {
        LinearProbingHashST<K, V> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < size; i++) {
            if (Optional.ofNullable(keys.get(i)).isPresent()) {
                temp.put(keys.get(i), vals.get(i));
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        size = capacity;
    }

    public void put(K key, V val){
        if(pairs >= size/2){
            resize(2*size);
        }
        int i;
        for(i = hash(key); keys.get(i) != null; i = (i +1) % size){
            if(keys.get(i).equals(key)){
                vals.set(i, val);
                return;
            }
        }
        keys.set(i, key);
        vals.set(i, val);
        ++pairs;
    }

    public V get(K key){
        for(int i = hash(key); keys.get(i) != null; i = (i + 1) % size){
            if(keys.get(i).equals(key)){
                return vals.get(i);
            }
        }
        return null;
    }

    public double averageHit(){
        if(pairs > 0){
            double steps = 0;
            for(int i = 0; i < size; i++){
                if(Optional.ofNullable(keys.get(i)).isPresent()){
                    for(int j = hash(keys.get(i)); keys.get(j) != keys.get(i); j = (j + 1) % size){
                        ++steps;
                    }
                }
            }
            return steps/pairs;
        }
        return 0;
    }
}
