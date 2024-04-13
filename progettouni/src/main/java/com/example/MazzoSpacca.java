package com.example;
import java.util.ArrayList;

import com.DTO.Carta;
import com.DTO.CartaSpacca;
import com.Enum.Spacca;

public class MazzoSpacca{
    //classe che serve solo per usare le carte spacca
    private static final String [] lettere= {"S","P","A","C","C","A"};
    ArrayList <CartaSpacca> mazzo;

    public MazzoSpacca(int numeroGiocatori){
        this.mazzo= new ArrayList<>();
        for(int i=0; i<numeroGiocatori; i++){
                for(String x : lettere){
                    this.mazzo.add(new CartaSpacca(x));
                }
                
        }
    }

}



