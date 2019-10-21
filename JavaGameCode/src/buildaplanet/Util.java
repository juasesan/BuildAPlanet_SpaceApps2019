/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildaplanet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Josue
 */
public class Util {
    
    public static void dialogoMaterial(Pane panel,String titulo, Node cuerpo, JFXButton boton){
        JFXDialog dialogo;
        JFXDialogLayout contenido = new JFXDialogLayout();
        Text t = new Text(titulo);
        t.setFont(new Font(15));
        contenido.setHeading(t);
        contenido.setBody(cuerpo);
        dialogo = new JFXDialog((StackPane) panel, contenido, JFXDialog.DialogTransition.CENTER);
        contenido.setActions(boton);
        dialogo.show();
    }
    
}
