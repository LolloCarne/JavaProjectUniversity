package com.DTO;

import java.util.ArrayList;
import com.Manager.TorneoManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Torneo {
    
    public ArrayList<Utente> partecipantiTorneo= new ArrayList<>();
    TorneoManager manager = new TorneoManager();
    Partita partitaTorneo;
    String codice;
    int partiteDaGiocare =3;

    //per creare le partite all'interno del torneo
    public Torneo(){
        manager.creaTorneo(this);
    }

    //per creare le partite all'interno del torneo passando già le partite
    public Torneo(int partite){
        this.partiteDaGiocare = partite;
        manager.creaTorneo(this);
    }

    //per aggiungere un partecipante al torneo 
    public void addPartecipante(Utente u) throws Error{
        TorneoManager manager = new TorneoManager();
        partecipantiTorneo.add(u);
    }

    //metodi getter e setter
    public ArrayList<Utente> getPartecipanti(){
        return this.partecipantiTorneo;
    }
    public int getPartiteDaGiocare(){
        return this.partiteDaGiocare;
    }
    public Partita getPartitaTorneo(){
        return this.partitaTorneo;
    }
    public void setPartiteDaGiocare(int n){
        partiteDaGiocare=n;
    }

    public void vincitoreTorneo(int n,ArrayList<Utente> p){
        
        String vincitore="";
        int max=0;
        for( Utente x : p){
            if(x.getPartiteVinte()>max){
                vincitore=x.getNick();
                max=x.getPartiteVinte();
            }
        }
        if(n ==0){
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("TORNEO FINITO");
                alert.setHeaderText(null);
                alert.setContentText("COMPLIMENTI " + vincitore + " HAI SPACCATO");
                alert.showAndWait(); 
        }
    }


}
