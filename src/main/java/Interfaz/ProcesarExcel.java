/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Modelo.Apx;
import Modelo.Aso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.ERROR;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author ncholbic
 */
public class ProcesarExcel {
    
    List<Apx> listaApx ;
    List<Aso> listaAso;
    List<String> temporal;
    /**
     * Explanation of the method by which we read the excel file we pass as
     * parameter if exists, in this example we print the content in the console.Explicación del método con el que leemos el fichero excel que pasamos
 como parámetro si existe, en este ejemplo mostramos el contenido por la
 consola.<h3>Example (Ejemplo)</h3>
     * <pre>
     * JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
     * javaPoiUtils.readExcelFile(new File("/home/xules/codigoxules/apachepoi/PaisesIdiomasMonedas.xls"));
     * </pre>
     *     
     * @param excelFile <code>String</code> excel File we are going to read.
     * @param tipoFichero
     * Fichero excel que vamos a leer.
     * 
     * 
     */

    public void leerExcel(File excelFile, String tipoFichero) {
        listaApx = new ArrayList<>();
        listaAso = new ArrayList<>();
        
        FileInputStream fichero = null;
        try {
            fichero = new FileInputStream(excelFile);
            InputStream excelStream = null;
            try {
                excelStream = fichero;                // Representación del más alto nivel de la hoja excel.
                Workbook hssfWorkbook = new XSSFWorkbook(excelStream);                 // Elegimos la hoja que se pasa por parámetro.
                Sheet sheet = hssfWorkbook.getSheetAt(0);
                // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
                Row row;               // Inicializo el objeto que leerá el valor de la celda
                Cell cell;                // Obtengo el número de filas ocupadas en la hoja
                int rows = sheet.getLastRowNum();
                String cellValue;
                // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos
                for (int r = 0; r <= rows; r++) {
                    row = sheet.getRow(r);                    
                    temporal = new ArrayList<>();
                    if (row == null) {
                        break;
                    } else {
                        System.out.print("Row: " + r + " -> ");
                        for (int c = 0; c < row.getLastCellNum(); c++) {                          
                            if (row.getCell(c) != null){
                                cellValue = switch(row.getCell(c).getCellType()){
                               case STRING -> row.getCell(c).getStringCellValue();
                               case NUMERIC -> "" + row.getCell(c).getNumericCellValue();
                               case BOOLEAN -> "" + row.getCell(c).getBooleanCellValue();
                               case BLANK -> "BLANK";
                               case FORMULA -> "FORMULA";
                               case ERROR -> "ERROR";
                               default -> "";
                               };          
                                temporal.add(cellValue);                                                          
                                System.out.print("[Column " + c + ": " + cellValue + "] ");
                                //El switch anterior equivale a un switch normal, que sería:
//                               switch(row.getCell(c).getCellType()){
//                                   case STRING: 
//                                      cellValue = row.getCell(c).getStringCellValue();
//                                       break;
//                                   case NUMERIC:
//                                       cellValue = "" + row.getCell(c).getNumericCellValue();
//                                       break;
//                                   case BOOLEAN:
//                                       cellValue = "" + row.getCell(c).getBooleanCellValue();
//                                       break;
//                                   case BLANK:
//                                       cellValue = "BLANK";
//                                       break;
//                                   case FORMULA:
//                                       cellValue = "FORMULA";
//                                        break;
//                                   case ERROR:
//                                       cellValue = "ERROR";
//                                   default:
//                                       cellValue = "";
//                                       break;
//                               }                                 
                            }                            
                        } System.out.println();                        
                        if (tipoFichero.equals("Apx")){                                                                        
                                listaApx.add(mapperApx(temporal)); //añadimos el objeto ya mapeado, de String a Apx
                            }else if(tipoFichero.equals("Aso")){                  
                                listaAso.add(mapperAso(temporal));
                            }                        
                    }
                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
            } catch (IOException ex) {
                System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
            } finally {
                try {
                    excelStream.close();
                } catch (IOException ex) {
                    System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                
            }
        }
        
    }

    private Apx mapperApx(List<String> temporal) { 
        Apx apx = new Apx();       
        apx.setAlarmType( temporal.get(0));
        apx.setIsCritical(temporal.get(1));
        apx.setTimeWindow(temporal.get(2));
        apx.setVolumen(temporal.get(3));
        apx.setRemedyGroup(temporal.get(7));
        apx.setServiceOwner(temporal.get(8));
        apx.setSupportEmail(temporal.get(9));
        apx.setSupportPhone(temporal.get(10));
       
        return apx;
    }

    private Aso mapperAso(List<String> temporal) {
        Aso aso = new Aso();
        aso.setSmc(temporal.get(0));
        aso.setAlarmType(temporal.get(0));
        aso.setSmcName(temporal.get(1));
        aso.setIsCritical(temporal.get(2));
        aso.setTimeWindow(temporal.get(3));
        aso.setVolumen(temporal.get(4));
        aso.setUuaa(temporal.get(8));
        aso.setRemedyGroup(temporal.get(9));
        aso.setServiceOwner(temporal.get(10));
        aso.setSupportEmail(temporal.get(11));
        aso.setSupportPhone(temporal.get(12));
        
        return aso;
    }
    
    public void escribirExcelAPX(){
        List <Apx> apx = listaApx;        
        Workbook libro = new XSSFWorkbook();
        final String nombreArchivo = "APX_Excel.xlsx";
        
        escribirPestaniaReceiversApx(libro, apx);
        escribirPestaniaAlarmTypeApx(libro, apx);
        escribirPestaniaAlarmsApx(libro, apx);
        
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
    
    private void escribirPestaniaReceiversApx(Workbook libro, List <Apx> apx){               
    //Pestaña (hoja) receivers
        Sheet hojaReceivers = libro.createSheet("receivers");
        Row header = hojaReceivers.createRow(0);
        
        //Creamos el estilo para poner el Header en negrita       
        CellStyle cellStyle = libro.createCellStyle();
        Font cellFont = libro.createFont();        
        cellFont.setBold(true);
        cellStyle.setFont(cellFont);         
        header.createCell(0).setCellValue("receiver_name");  
        header.getCell(0).setCellStyle(cellStyle);  //le pongo el estilo creado a la celda 0 y así con todas, pero solo del HEADER.
        header.createCell(1).setCellValue("email");
        header.getCell(1).setCellStyle(cellStyle);
        header.createCell(2).setCellValue("namespace");
        header.getCell(2).setCellStyle(cellStyle);
        header.createCell(3).setCellValue("kind");
        header.getCell(3).setCellStyle(cellStyle);       
       
        Row filaReceivers;
        for (int i = 1; i < apx.size(); i++) {
            filaReceivers = hojaReceivers.createRow(i);                      
            filaReceivers.createCell(0).setCellValue(apx.get(i).getAlarmType());
            filaReceivers.createCell(1).setCellValue(apx.get(i).getSupportEmail());
            filaReceivers.createCell(2).setCellValue("apx.online");
            filaReceivers.createCell(3).setCellValue("MAIL");                
        }                            
        hojaReceivers.autoSizeColumn(0); //Para ajustar el tamaño de las columnas al texto
        hojaReceivers.autoSizeColumn(1); //Para ajustar el tamaño de las columnas al texto
        hojaReceivers.autoSizeColumn(2); //Para ajustar el tamaño de las columnas al texto
        hojaReceivers.autoSizeColumn(3); //Para ajustar el tamaño de las columnas al texto
    }
    
    private List<Apx> crearListSalida(List<Apx> listaEntrada){
        List<Apx> listaSalida = new ArrayList();
        
        for (int i = 0; i < listaEntrada.size(); i++) {
        String x = listaEntrada.get(i).getResponseTimeCritical();
    
            if(!x.equals("")){
                
            }
        }
        
        return listaSalida;
    
    }
    
    private void escribirPestaniaAlarmTypeApx(Workbook libro, List <Apx> apx){               
    //Pestaña (hoja) receivers
        Sheet hojaAlarmType = libro.createSheet("alarmTypes");
        Row header = hojaAlarmType.createRow(0);
        
        //Creamos el estilo para poner el Header en negrita       
        CellStyle cellStyle = libro.createCellStyle();
        Font cellFont = libro.createFont();        
        cellFont.setBold(true);
        cellStyle.setFont(cellFont);         
        header.createCell(0).setCellValue("_id");  
        header.getCell(0).setCellStyle(cellStyle);  //le pongo el estilo creado a la celda 0 y así con todas, pero solo del HEADER.
        header.createCell(1).setCellValue("namespace");
        header.getCell(1).setCellStyle(cellStyle);
        header.createCell(2).setCellValue("alarmType_receiver");
        header.getCell(2).setCellStyle(cellStyle);
        header.createCell(3).setCellValue("notificationPolicies");
        header.getCell(3).setCellStyle(cellStyle);       
        header.createCell(4).setCellValue("volume");
        header.getCell(4).setCellStyle(cellStyle); 
        
        Row filaAlarmtype;
        for (int i = 1; i < apx.size(); i++) {
            filaAlarmtype = hojaAlarmType.createRow(i);                      
            filaAlarmtype.createCell(0).setCellValue(apx.get(i).getAlarmType());
            filaAlarmtype.createCell(1).setCellValue("apx.online");
            filaAlarmtype.createCell(2).setCellValue(apx.get(i).getAlarmType() + ",mail-receiver-monitorizacion,mail-set1,support-level1");            
            String notificationPolicies = "";
            if (apx.get(i).getTimeWindow().equalsIgnoreCase("24H")){
                notificationPolicies = "medium_volumetrics_24_hours_window";
            }
            else if(apx.get(i).getTimeWindow().equalsIgnoreCase("WORK_HOURS")){
                notificationPolicies = "medium_volumetrics_work_hours_window";
            }
            else if(apx.get(i).getTimeWindow().equalsIgnoreCase("NON_WORK_HOURS")){
                notificationPolicies = "medium_volumetrics_non_work_hours_window";
            }
            filaAlarmtype.createCell(3).setCellValue(notificationPolicies);  
            filaAlarmtype.createCell(4).setCellValue(apx.get(i).getVolumen());
        }             
        
        hojaAlarmType.autoSizeColumn(0); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(1);
        hojaAlarmType.autoSizeColumn(2); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(3); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(4); //Para ajustar el tamaño de las columnas al texto
    }
    private void escribirPestaniaAlarmsApx(Workbook libro, List <Apx> apx){               
    //Pestaña (hoja) receivers
        Sheet hojaAlarmType = libro.createSheet("alarms");
        Row header = hojaAlarmType.createRow(0);
        
        //Creamos el estilo para poner el Header en negrita       
        CellStyle cellStyle = libro.createCellStyle();
        Font cellFont = libro.createFont();        
        cellFont.setBold(true);
        cellStyle.setFont(cellFont);         
        header.createCell(0).setCellValue("_id");  
        header.getCell(0).setCellStyle(cellStyle);  //le pongo el estilo creado a la celda 0 y así con todas, pero solo del HEADER.
        header.createCell(1).setCellValue("alarmType");
        header.getCell(1).setCellStyle(cellStyle);
        header.createCell(2).setCellValue("namespace");
        header.getCell(2).setCellStyle(cellStyle);
        header.createCell(3).setCellValue("env");
        header.getCell(3).setCellStyle(cellStyle);       
        header.createCell(3).setCellValue("actionIfCritical");
        header.getCell(3).setCellStyle(cellStyle); 
        header.createCell(4).setCellValue("actionIfStalled");
        header.getCell(4).setCellStyle(cellStyle); 
        header.createCell(5).setCellValue("actionIfWarning");
        header.getCell(5).setCellStyle(cellStyle); 
        header.createCell(6).setCellValue("description");
        header.getCell(6).setCellStyle(cellStyle); 
        header.createCell(7).setCellValue("documentation");
        header.getCell(7).setCellStyle(cellStyle); 
        header.createCell(8).setCellValue("group");
        header.getCell(8).setCellStyle(cellStyle); 
        header.createCell(9).setCellValue("serviceName");
        header.getCell(9).setCellStyle(cellStyle); 
        header.createCell(10).setCellValue("serviceOwner");
        header.getCell(10).setCellStyle(cellStyle); 
        header.createCell(11).setCellValue("supportEmail");
        header.getCell(11).setCellStyle(cellStyle); 
        header.createCell(12).setCellValue("supportPhone");
        header.getCell(13).setCellStyle(cellStyle); 
        header.createCell(14).setCellValue("monitoredResource");
        header.getCell(14).setCellStyle(cellStyle); 
        header.createCell(15).setCellValue("statusFrequency");
        header.getCell(15).setCellStyle(cellStyle); 
        
        Row filaAlarmtype;
        for (int i = 1; i < apx.size(); i++) {
            filaAlarmtype = hojaAlarmType.createRow(i);                      
            filaAlarmtype.createCell(0).setCellValue(apx.get(i).getAlarmType());
            filaAlarmtype.createCell(1).setCellValue("apx.online");
            filaAlarmtype.createCell(2).setCellValue(apx.get(i).getAlarmType() + ",mail-receiver-monitorizacion,mail-set1,support-level1");            
            String notificationPolicies = "";
            if (apx.get(i).getTimeWindow().equalsIgnoreCase("24H")){
                notificationPolicies = "medium_volumetrics_24_hours_window";
            }
            else if(apx.get(i).getTimeWindow().equalsIgnoreCase("WORK_HOURS")){
                notificationPolicies = "medium_volumetrics_work_hours_window";
            }
            else if(apx.get(i).getTimeWindow().equalsIgnoreCase("NON_WORK_HOURS")){
                notificationPolicies = "medium_volumetrics_non_work_hours_window";
            }
            filaAlarmtype.createCell(3).setCellValue(notificationPolicies);  
            filaAlarmtype.createCell(4).setCellValue(apx.get(i).getVolumen());
        }             
        
        hojaAlarmType.autoSizeColumn(0); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(1); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(2); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(3); //Para ajustar el tamaño de las columnas al texto
        hojaAlarmType.autoSizeColumn(4); //Para ajustar el tamaño de las columnas al texto
    }
    
}
