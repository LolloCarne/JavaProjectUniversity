package com.DTO;
import java.util.ArrayList;

public class CartaRubaSpacca extends Carta {
    String nick;

    public CartaRubaSpacca(String nick){
        super();
        this.nick = nick;
    }

    public CartaRubaSpacca(){}

    public void rubaSpacca(){
        Utente u = new Utente(nick);
        ArrayList <CartaSpacca> carte= u.carteSpacca;

        for(CartaSpacca s: carte){
            System.out.println(s);
        }
    }

}
