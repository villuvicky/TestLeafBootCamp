package testNg;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String[][] excelData(String sheetName) throws IOException {
		
		//Step1: set up the workbook path
		XSSFWorkbook wb = new XSSFWorkbook("./TestData/LoginData2.xlsx");
		//step2: Get into the Worksheet
		XSSFSheet ws = wb.getSheet(sheetName);
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