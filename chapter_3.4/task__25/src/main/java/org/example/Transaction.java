package org.example;

import java.util.Date;

public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;
    private Integer hash = null;
    
    public Transaction(String who, Date when, double amount){
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    @Override
    public int hashCode(){
        if(this.hash == null){
            int hash2 = 17;
            hash2 = 31 * hash2 + who.hashCode();
            hash2 = 31 * hash2 + when.hashCode();
            hash2 = 31 * hash2 + ((Double)amount).hashCode();
            this.hash = hash2;
        }
        return this.hash;
    }
}
