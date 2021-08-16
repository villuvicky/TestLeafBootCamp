package dataFromExcel;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String[][] excelData(String SheetName) throws IOException {

		System.out.println(SheetName);
		
		//Step1: set up the workbook path
		XSSFWorkbook wb = new XSSFWorkbook("./data/LoginData.xlsx");
		//step2: Get into the Worksheet
		XSSFSheet ws = wb.getSheet(SheetName);
		int rowCount = ws.getLastRowNum(); // Excluding the first row
		//System.out.println(rowCount);
		short cellCount = ws.getRow(0).getLastCellNum();

		String[][] data = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) { //rows

			for (int j = 0; j < cellCount; j++) {

				String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = cellValue;
			}
		}

		//Close the workbook
		wb.close();

		return data;
	}
}