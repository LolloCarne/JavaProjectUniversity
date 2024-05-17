package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.DTO.Carta;
import com.DTO.CartaBlocco;
import com.DTO.CartaJollyNumero;
import com.DTO.CartaJollySeme;
import com.DTO.CartaPescaDueCarte;
import com.DTO.CartaRubaSpacca;
import com.Enum.Seme;

public class Mazzo {
    
    Stack<Carta> mazzo;
    ArrayList<Carta> pescate;
    String path="";
    

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
                String str ="";
                switch(s){
                    case CUCCHIAI : str= "cucchiai";
                    case FORCHETTE : str= "forchette";
                    case COLTELLI : str= "coltelli";
                }
                
                
            }
        }
        
        this.mazzo.add(new CartaRubaSpacca( "/com/example/img/rubaSpacca.png"));
        this.mazzo.add(new CartaRubaSpacca( "/com/example/img/rubaSpacca.png"));
        this.mazzo.add(new CartaRubaSpacca( "/com/example/img/rubaSpacca.png"));

        this.mazzo.add(new CartaJollyNumero("/com/example/img/jollySeme.png"));
        this.mazzo.add(new CartaJollyNumero("/com/example/img/jollySeme.png"));

        this.mazzo.add(new CartaJollySeme("/com/example/img/jollySeme.png"));
        this.mazzo.add(new CartaJollySeme("/com/example/img/jollySeme.png"));

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
