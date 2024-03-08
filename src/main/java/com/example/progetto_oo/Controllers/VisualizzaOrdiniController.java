package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class
VisualizzaOrdiniController {

    public MenuItem sceltoUtente;
    public MenuItem sceltoPeriodo;
    public AnchorPane CercaPerUtente;
    public AnchorPane CercaPerData;
    public AnchorPane patternVisualizzaOrdinal;

    public void sceltaVisualizza(ActionEvent event) {
        if(event.getSource() == sceltoUtente){
            CercaPerData.setVisible(false);
            CercaPerUtente.setVisible(true);
        }else if(event.getSource() == sceltoPeriodo){
            CercaPerUtente.setVisible(false);
            CercaPerData.setVisible(true);
        }
    }
}
