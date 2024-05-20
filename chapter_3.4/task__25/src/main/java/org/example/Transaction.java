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
            int hash = 17;
            hash = 31 * hash + who.hashCode();
            hash = 31 * hash + when.hashCode();
            hash = 31 * hash + ((Double)amount).hashCode();
            this.hash = hash;
        }
        return this.hash;
    }
}
