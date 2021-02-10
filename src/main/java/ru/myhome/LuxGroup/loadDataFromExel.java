package ru.myhome.LuxGroup;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ru.myhome.DataFile.CutPrice;
import ru.myhome.DataFile.CutPriceList;
import ru.myhome.DataFile.MaterialPrice;
import ru.myhome.DataFile.MaterialPriceList;


public class loadDataFromExel{
	
	private final static String mPriceListFile = "MaterialsPrice.xlsx";
	private final static String cutPriceListFile = "CutPrice.xlsx";
	
	public static MaterialPriceList loadMaterialPriceList() {
		System.out.println("Load material price list...");
		String filename = mPriceListFile;
		MaterialPriceList mList = new MaterialPriceList();
		XSSFWorkbook wBook;
		try {
			File file = new File(filename);
			wBook = new XSSFWorkbook(file);
			Sheet sheet = wBook.getSheetAt(0);
			for (Row row : sheet) {
				if(row.getCell(1).getCellType() == CellType.STRING) {
					continue;
				}
				String type = row.getCell(0).getStringCellValue();
				double thickness = row.getCell(1).getNumericCellValue();
				double cost = row.getCell(2).getNumericCellValue();
				String provider = row.getCell(3).getStringCellValue();
				MaterialPrice mPrice = new MaterialPrice(type, thickness, cost, provider);
				mList.addItem(mPrice);
			}
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Eror...");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			return null;
		}
		try {
			wBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	public static CutPriceList loadCutPriceList() {
		System.out.println("loading cut price list");
		String filename = cutPriceListFile;
		CutPriceList cutList = new CutPriceList();
		try {
			File file = new File(filename);
			XSSFWorkbook wBook = new XSSFWorkbook(file);
			Sheet sheet = wBook.getSheetAt(0);
			for (Row row : sheet) {
				if(row.getCell(2).getCellType() == CellType.STRING) {
					continue;
				}
				String types = row.getCell(0).getStringCellValue();
				double cost1 = row.getCell(2).getNumericCellValue();
				double cost2 = row.getCell(3).getNumericCellValue();
				double tMin, tMax;
				
				if(row.getCell(1).getCellType() == CellType.STRING) {
					String thick = row.getCell(1).getStringCellValue();
					String[] str = thick.split("-");
					if(str.length > 1) {
						tMin = Double.parseDouble(str[0].replace(",", "."));
						tMax = Double.parseDouble(str[1].replace(",", "."));
					}else tMin = tMax = Double.parseDouble(str[0].replace(",", "."));
				}else tMin = tMax = row.getCell(1).getNumericCellValue(); 
				for(String type: types.split(",")) {
					CutPrice cutPrice = new CutPrice();
					cutPrice.setType(type.trim());
					cutPrice.setThickMin(tMin);
					cutPrice.setThickMax(tMax);
					cutPrice.setСutPrice(cost1, cost2);
					cutList.addItem(cutPrice);
					System.out.println(cutPrice);
				}
			}
			wBook.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Eror...");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			return null;
		}
		return cutList;
	}
}
