package net.zz.xls.infrastructure.utils;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.zz.xls.infrastructure.annotation.Cell;
import net.zz.xls.infrastructure.annotation.Cells;
import net.zz.xls.infrastructure.entity.Common;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Created by ZaoSheng on 2015/7/21.
 */
public class ExcelUtils {
    List<Field> fields;

    /**
     * read the Excel file
     *
     * @param is the is of the Excel file
     * @return
     * @throws IOException
     */

    public <T> List<T> readExcel(InputStream is, String fileName, Class<T> clazz) throws IOException {


        if (!Common.EMPTY.equals(fileName)) {
            Field[] fields = clazz.getDeclaredFields();
            this.fields = Arrays.asList(fields);
            System.out.println("--------------------------");
            System.out.println("fields.length:" + fields.length);
            System.out.println("fields.size:" + this.fields.size());
            try {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(fileName)) {
                    return readXls(is, clazz);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(fileName)) {
                    return readXlsx(is, clazz);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(fileName + Common.NOT_EXCEL_FILE);
        }

        return null;
    }


    /**
     * Read the Excel 2010
     *
     * @param is the is of the excel Excel
     * @param clazz the clazz of the Entity
     * @return
     * @throws IOException
     */

    public <T> List<T> readXlsx(InputStream is, Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return getEntitys(new XSSFWorkbook(is), clazz);
    }


    /**
     * Read the Excel 2003-2007
     *
     * @param is the path of the Excel
     * @param clazz the clazz of the Entity
     * @return
     * @throws IOException
     */
    public <T> List<T> readXls(InputStream is, Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
       return getEntitys(new HSSFWorkbook(is), clazz);
    }



    private void setMethod(Class clazz, Object t, Field field, org.apache.poi.ss.usermodel.Cell cell) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method set = clazz.getDeclaredMethod(String.format("set%s", StringUtils.initcap(field.getName())), field.getType());
        set.invoke(t, getValue(cell));
    }


    private <T> List<T> getEntitys( Workbook workbook, Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<T> list = new ArrayList<T>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            //---------------------------待
            T t = getEntity(sheet, clazz);

            if (null != t)
            {
                list.add(t);
            }
        }
        return list;
    }

    // Read the Row
    private <T> T getEntity(Sheet sheet, Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // Read the Row
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null) {
                T t = clazz.newInstance();

                short lastNum = row.getLastCellNum();
                for (short start = 0; start <= lastNum; start++) {
                    org.apache.poi.ss.usermodel.Cell ocell = row.getCell(start);
                    Object object = null;
                    for (Field field : fields) {

                        Cells cells = field.getAnnotation(Cells.class);
                        if (null != cells) {
                            String[] cs = cells.value();
                            for (String cell : cs) {
                                if (String.valueOf(start).equals(cell)) {
                                    setMethod(clazz, t, field, ocell);
                                }
                            }
                        } else {
                            Cell cell = field.getAnnotation(Cell.class);
                            if (null != cell) {
                                if (String.valueOf(start).equals(cell.value())) {
                                    setMethod(clazz, t, field, ocell);
                                }
                            }
                            fields.remove(field);
                        }

                    }
                }
                return t;
            }
        }
        return null;
    }


/*
    @SuppressWarnings("static-access")
    private Object getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return xssfRow.getBooleanCellValue();
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return xssfRow.getNumericCellValue();
        }  else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK) {
            return xssfRow.getDateCellValue();
        } else {
            return xssfRow.getStringCellValue();
        }
    }
*/

    @SuppressWarnings("static-access")
    private Object getValue(org.apache.poi.ss.usermodel.Cell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return hssfCell.getBooleanCellValue();
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return hssfCell.getNumericCellValue();
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK) {
            return hssfCell.getDateCellValue();
        } else {
            return hssfCell.getStringCellValue();
        }
    }
}