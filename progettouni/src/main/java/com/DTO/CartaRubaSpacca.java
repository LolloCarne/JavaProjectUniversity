package com.DTO;
import java.util.ArrayList;

public class CartaRubaSpacca extends Carta {
    String nick;

    public CartaRubaSpacca(String nick){
        super();
        this.nick = nick;
    }

    public void rubaSpacca(){
        Utente u = new Utente(nick);
        ArrayList <CartaSpacca> carte = new ArrayList();
        carte= u.getCarteSpacca();

        for(CartaSpacca s: carte){
            System.out.println(s);
        }


    }

}
