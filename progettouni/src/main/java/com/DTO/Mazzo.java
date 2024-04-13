package com.DTO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Mazzo {
    
    Stack<Carta> mazzo;
    ArrayList<Carta> pescate;

    public Mazzo(){
        mazzo= new Stack<>();
        pescate= new ArrayList<>();
    }

    public Carta pesca(){

        Carta pescata= this.mazzo.pop();
        pescate.add(pescata);
        return pescata;
    }
}
