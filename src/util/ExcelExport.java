package util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExport {
	private String startDate;
	private String endDate;
	private String dateType;
	private String orgType;

	public static void main(String[] args) {
		ExcelExport exExcel = new ExcelExport();
		//exExcel.export();
		System.out.println("O.K.");
	}

	/**
	 * 
	 */
	public void export(HttpServletResponse response) {
		try {
			String fileName = "2019年度报表0920";
			dateType="mothly";
			
			/*WritableWorkbook wbook = Workbook
					.createWorkbook(new FileOutputStream(fileName  + ".xls"));
			*/// 建立excel文件
			
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; fileName=" + new String((fileName+".xls").getBytes(), "iso8859-1"));
			WritableWorkbook wbook = Workbook.createWorkbook(out); 
			
			WritableSheet wsheet = wbook.createSheet("导出数据", 0); // sheet名称
			WritableCellFormat cellFormatNumber = new WritableCellFormat();
			cellFormatNumber.setAlignment(Alignment.RIGHT);

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式、字体�?�粗体�?�斜体�?�下划线、颜�?
			WritableCellFormat wcf = new WritableCellFormat(wf); // title单元格定�?
			
			WritableCellFormat wcfe = new WritableCellFormat(); // �?般单元格定义
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

			wcf.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			 
			wcfe.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			wsheet.setColumnView(0, 20);// 设置列宽
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 20);

			int rowIndex = 0;
			int columnIndex = 0;
			
			List<String[]> pageDataList = new ArrayList<String[]>();
			pageDataList = getDataListByCompanyYear();
			
			if (null != pageDataList) {
				// rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 500);// 设置标题行高
				wsheet.addCell(new Label(columnIndex++, rowIndex, fileName, wcf));
				wsheet.mergeCells(0, rowIndex, "mothly".equals(dateType) ? 4
						: 3, rowIndex);// 合并标题�?占单元格
				rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 380);// 设置项目名行�?
				wsheet.addCell(new Label(columnIndex++, rowIndex, "编号", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "部门", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "报销总额", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "年份", wcf));
				if ("mothly".equals(dateType)) {
					wsheet.addCell(new Label(columnIndex++, rowIndex, "月份", wcf));
				}
				
				// �?始行循环
				for (String[] array : pageDataList) { // 循环�?
					rowIndex++;
					columnIndex = 0;
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[0],
							wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[1],
							wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[2],
							wcfe));
					wsheet.addCell(new Label(columnIndex++, rowIndex, array[3],
							wcfe));
					if ("mothly".equals(dateType)) {
						wsheet.addCell(new Label(columnIndex++, rowIndex,
								array[4], wcfe));
					}
				}

				rowIndex++;
				columnIndex = 0;
			}

			wbook.write();
			if (wbook != null) {
				wbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String[]> getDataListByCompanyYear() {
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] { "1035", "业务一部1", "10906.00", "2013年", "09" });
		list.add(new String[] { "1024", "业务二部2", "5394.00", "2013年", "09" });
		list.add(new String[] { "1013", "财务部", "906.00", "2013年", "09" });
		list.add(new String[] { "1005", "平台研发部", "218.00", "2013年", "09" });


		return list;
	}
	
	
	
}
