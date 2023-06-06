
                            /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.generadorficherosxlsmaven;

/**
 *
 * @author ncholbic
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GeneradorFicherosXlsMaven {

    public void escribirXLS(){
        Workbook libro = new XSSFWorkbook();
        final String nombreArchivo = "PrimerArchivo.xlsx";
        //Esto son las pestañas
        Sheet hoja = libro.createSheet("Hoja 1");
        
        //Insertamos fila
        Row primeraFila = hoja.createRow(0);
        //Insertamos celda
        
//        primeraFila.createCell(0).setCellValue("Probando esto");
//        primeraFila.createCell(1).setCellValue("Funcionará....");
//        primeraFila.createCell(2).setCellValue("Quiero comer....");
        Cell primeraCelda = primeraFila.createCell(0);   
        primeraCelda.setCellValue("Yo voy en la primera celda y primera fila");
        Cell segundaCelda = primeraFila.createCell(1);
        segundaCelda.setCellValue("Probando esta mierda");
        Cell terceraCelda = primeraFila.createCell(2);
        terceraCelda.setCellValue("Probando esta muy aburrida");
        hoja.autoSizeColumn(0); //Para ajustar el tamaño de las columnas al texto
        
    
        //Para hacer la ruta y averiguar la ruta actual
//        File directorioActual = new File(".");
//        String ubicacion = directorioActual.getAbsolutePath();
//        String ubicacionArchivoSalida = ubicacion.substring(0, ubicacion.length() - 1) + nombreArchivo;
        FileOutputStream fichero;
        try {
            fichero = new FileOutputStream(nombreArchivo, false);
            libro.write(fichero);
            libro.close();
            System.out.println("Libro guardado correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println("Error de filenotfound");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }
    }
}
