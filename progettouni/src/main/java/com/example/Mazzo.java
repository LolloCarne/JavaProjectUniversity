package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.DTO.Carta;
import com.DTO.CartaBlocco;
import com.DTO.CartaJollyNumero;
import com.DTO.CartaJollySeme;
import com.DTO.CartaPerdiSpacca;
import com.DTO.CartaPescaDueCarte;
import com.Enum.Seme;

public class Mazzo {
    
    Stack<Carta> mazzo;
    ArrayList<Carta> pescate;

    public Mazzo(){
        mazzo= new Stack<>();
        pescate= new ArrayList<>();
        makeMazzo();
        mescola();
    }

    public Carta pesca(){

        Carta pescata= this.mazzo.pop();
        pescate.add(pescata);
        return pescata;
    }

    public void mescola(){
        Collections.shuffle(mazzo); // Utilizziamo il metodo shuffle della classe Collections per mescolare il mazzo
    }
    private void makeMazzo(){

         for(Seme s : Seme.values()){
            for(int i =0; i<7;i++){
                this.mazzo.add(new Carta(s,i));
            }
        }
        
        this.mazzo.add(new CartaBlocco());
        this.mazzo.add(new CartaBlocco());
        this.mazzo.add(new CartaBlocco());

        this.mazzo.add(new CartaPescaDueCarte());
        this.mazzo.add(new CartaPescaDueCarte());
        this.mazzo.add(new CartaPescaDueCarte());

        this.mazzo.add(new CartaJollyNumero());
        this.mazzo.add(new CartaJollyNumero());

        this.mazzo.add(new CartaJollySeme());
        this.mazzo.add(new CartaJollySeme());

        this.mazzo.add(new CartaPerdiSpacca());
        this.mazzo.add(new CartaPerdiSpacca());
        this.mazzo.add(new CartaPerdiSpacca());
        this.mazzo.add(new CartaPerdiSpacca());
    }


    public Carta getCartaDiGioco(){

        for(int i=0; i<this.mazzo.size();i++){
            if(this.mazzo.get(i).getClass()==Carta.class){
                this.pescate.add(this.mazzo.remove(i));
                return this.pescate.get(this.pescate.size()-1);
            }
        }
        return null;
    }



}
