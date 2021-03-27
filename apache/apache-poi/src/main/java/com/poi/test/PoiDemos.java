package com.poi.test;

import com.zj.test.util.TestHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/27 12:36
 * @description: poi对txt,doc,docx,xls,xlsx等文件内容提取demo
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class PoiDemos {

    // 测试文件所在目录
    String filesDir = "C:\\Users\\Zhou Jian\\Desktop\\";

    /**
     * 1.demo: 提取xlsx文件内容，转化成String
     */
    @Test
    public void xlsx2String() {
        File file = new File(filesDir, "poi-test.xlsx");
        XSSFWorkbook xlsxwb = new XSSFWorkbook();
        try {
            OPCPackage pkg = OPCPackage.open(new FileInputStream(file));
            xlsxwb = new XSSFWorkbook(pkg);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // excel内容提取器
        ExcelExtractor extractor = new XSSFExcelExtractor(xlsxwb);
        extractor.setFormulasNotResults(true);
        /*
        是否返回sheet名：如果为true,sheet名将独占一行返回:

        Sheet1
        姓名	手机号	年龄	爱好	是不是好人
        周健	15156444479	26	玩游戏	是
        马云	1110	65	赚人民的钱	不是
        刘强东	12345	45	为人民赚钱	是
        */
        extractor.setIncludeSheetNames(false);

        /*
        org.apache.poi.ss.extractor.ExcelExtractor.getText

        [作用]
        将xlsx文件内容以文件返回，会按顺序读取多个工作簿(sheet)的内容，
        每个sheet的内容以多行字符串返回
        每行字符串包含了多个列的值，以\t作为分隔符

        [输出内容]
        姓名	手机号	年龄	爱好	是不是好人
        周健	15156444479	26	玩游戏	是
        马云	1110	65	赚人民的钱	不是
        刘强东	12345	45	为人民赚钱	是
        姓名2	手机号	年龄	爱好	是不是好人
        周健2	15156444479	26	玩游戏	是
        马云2	1110	65	赚人民的钱	不是
        刘强东2	12345	45	为人民赚钱	是

        注意：如果某个列没有值，则不会输出，这就导致了无法区分是哪个列没有值：
        姓名2	手机号	年龄	爱好	是不是好人
        周健2	15156444479	玩游戏	是
         */
        TestHelper.println(extractor.getText());
    }

    /**
     * 2.demo: 提取xlsx文件内容，转化成String
     */
    @Test
    public void xls2String() {
        File file = new File(filesDir, "poi-test.xls");

        HSSFWorkbook xlswb = new HSSFWorkbook();
        try {
            POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(file));
            xlswb = new HSSFWorkbook(fileSystem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExcelExtractor extractor = new org.apache.poi.hssf.extractor.ExcelExtractor(xlswb);
        extractor.setFormulasNotResults(true);
        extractor.setIncludeSheetNames(false);

        TestHelper.println(extractor.getText());
    }

    public void doc2String(File file) {
        StringBuffer buffer = new StringBuffer();

        WordExtractor extractor = null;
        POIFSFileSystem fileSystem;
        try {
            fileSystem = new POIFSFileSystem(new FileInputStream(file));
            extractor = new WordExtractor(fileSystem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] paragraphText = extractor.getParagraphText();
        for (String paragraph : paragraphText) {
            buffer.append(paragraph);
        }

        TestHelper.println(buffer.toString());
    }
}
