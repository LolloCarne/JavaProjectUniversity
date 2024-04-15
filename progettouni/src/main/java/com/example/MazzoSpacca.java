package com.example;
import java.util.ArrayList;
import java.util.Random;

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

    public CartaSpacca getRandomCard(){

        Random r = new Random();
        //return mazzo.remove(r.nextInt(this.mazzo.size()));
        return mazzo.get(r.nextInt(this.mazzo.size()));
        
    }

    public void removeCartaPescata(CartaSpacca c){
        //da chiamare dopo il metodo getRandomCart se la carta che il giocatore ha pescato non la ha gia
        mazzo.remove(c);
    }

}



