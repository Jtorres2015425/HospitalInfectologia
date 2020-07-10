
package org.josetorres.sistema;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import org.josetorres.controller.menuPrincipalController;
import org.josetorres.db.Conexiones;

/**
 *
 * @author programacion
 */
public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/josetorres/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    @Override
public void start(Stage escenarioPrincipal){
    this.escenarioPrincipal = escenarioPrincipal;
    escenarioPrincipal.setTitle("Hospital de Infectologia");
    menuPrincipal();
    escenarioPrincipal.show();
    }
public void menuPrincipal(){
   try{
        PreparedStatement procedimiento = Conexiones.getInstancia().getConexion().prepareCall("{call sp_Buscar_Medicos(?)}");
        procedimiento.setInt(1,1);
        ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                System.out.println(registro.getInt("codigoMedico"));
                System.out.println(registro.getString("licenciaMedica"));
                
            }
    }catch(SQLException e){
        e.printStackTrace();
   }    
    try{
        menuPrincipalController menuPrincipal =(menuPrincipalController)cambiarEscena("menuPrincipalView.fxml",600,400);
        menuPrincipal.setEscenarioPrincipal(this);
    }catch(Exception e){
        e.printStackTrace();
    }
    
    /**
     *
     * @param fxml
     * @param ancho
     * @param alto
     * @return
     * @throws Exception
     */
}
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
    Initializable resultado = null;
    FXMLLoader cargadorFXML = new FXMLLoader();
    InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
    cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
    cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
    escena = new Scene((AnchorPane)cargadorFXML.load(archivo), ancho, alto);
    escenarioPrincipal.setScene(escena);
    escenarioPrincipal.sizeToScene();
    resultado = (Initializable)cargadorFXML.getController();
        return resultado;    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
