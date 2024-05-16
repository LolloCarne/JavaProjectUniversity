package com.example;
import java.util.ArrayList;
import java.util.Random;

import com.DTO.Carta;
import com.DTO.CartaSpacca;
import com.DTO.Utente;
import com.Enum.Spacca;

public class MazzoSpacca{
    //classe che serve solo per usare le carte spacca
    private static final String [] lettere= {"S","P","A","C","C","A"};
    ArrayList <CartaSpacca> mazzo;

    public MazzoSpacca(int numeroGiocatori){
        this.mazzo= new ArrayList<>();

        for(String i : lettere){
            mazzo.add(new CartaSpacca(i));
        }
    }

    public CartaSpacca getRandomCard(){

        Random r = new Random();
        //return mazzo.remove(r.nextInt(this.mazzo.size()));
        return mazzo.get(r.nextInt(this.mazzo.size()));
        
    }

    public CartaSpacca getRightCard(Utente u){
        return mazzo.get(u.carteSpacca.size());
    }

    public void removeCartaPescata(CartaSpacca c){
        //da chiamare dopo il metodo getRandomCart se la carta che il giocatore ha pescato non la ha gia
        mazzo.remove(c);
    }

}



