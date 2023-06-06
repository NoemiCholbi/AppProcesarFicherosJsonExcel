package Main;


import Interfaz.ProcesarExcel;
import java.net.URISyntaxException;

//import com.fasterxml.jackson.databind.ObjectMapper;

//import Interfaz.PantallaSeleccionFichero;
import Interfaz.PrimeraPantalla;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/* @param excelFile <code>"EntradaEjemploAPX.xlsx"</code>*/

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, FileNotFoundException, ParseException {
        //Facade escritor = new Facade();
//	       //ObjectMapper mapper = new ObjectMapper();
//	       Apx apx = Facade.rellenarDatos(); 
        PrimeraPantalla pp = new PrimeraPantalla();
        //PantallaSeleccionFichero psf = new PantallaSeleccionFichero();
        pp.setVisible(true);
        //String fichero = "EntradaEjemploAPX.xlsx";
//       File excelFile = new File("C:\\Users\\ncholbic\\Documents\\NetBeansProjects\\GeneradorFicherosXlsMaven\\EntradaEjemploAPX.xlsx");
//        //FileInputStream inputStream = new FileInputStream(new File(fichero));
//        ProcesarExcel le = new ProcesarExcel();
//        le.leerExcel(excelFile);

    }

//	      

}
