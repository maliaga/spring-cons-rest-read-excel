package hello.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadExcelUtil {

    public static List<String[]> readExcelFileToArray(File excelFile){
        List<String[]> arrayDatos = new ArrayList<>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excelFile);
            // High level representation of a workbook.
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // We chose the sheet is passed as parameter.
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow = hssfSheet.getRow(hssfSheet.getTopRow());
            String [] datos = new String[hssfRow.getLastCellNum()];
            // For this example we'll loop through the rows getting the data we want
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos
            for (Row row: hssfSheet) {
                for (Cell cell : row) {
                /*
                    We have those cell types (tenemos estos tipos de celda):
                        CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
                */
                    datos[cell.getColumnIndex()] =
                            (cell.getCellType() == Cell.CELL_TYPE_STRING)?cell.getStringCellValue():
                                    (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)?"" + cell.getNumericCellValue():
                                            (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)?"" + cell.getBooleanCellValue():
                                                    (cell.getCellType() == Cell.CELL_TYPE_BLANK)?"BLANK":
                                                            (cell.getCellType() == Cell.CELL_TYPE_FORMULA)?"FORMULA":
                                                                    (cell.getCellType() == Cell.CELL_TYPE_ERROR)?"ERROR":"";
                }
                arrayDatos.add(datos);
                datos = new String[hssfRow.getLastCellNum()];
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
        return arrayDatos;
    }
}
