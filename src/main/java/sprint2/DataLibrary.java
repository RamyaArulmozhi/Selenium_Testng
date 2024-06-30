package sprint2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLibrary {

	public static Object[][] readData(String fileName) throws IOException {
		XSSFWorkbook book = new XSSFWorkbook("src/main/resources/data/" + fileName + ".xlsx");

		XSSFSheet sheet = book.getSheet("credentials");
		
		int rowCount = sheet.getPhysicalNumberOfRows();

		int colCount = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[rowCount-1][colCount];//As first row is Heading row

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String cellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println(cellValue);
				data[i - 1][j] = cellValue;
			}
		}

		book.close();

		return data;

	}
}
