package com.poi.test;

import com.zj.test.util.TestHelper;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.TextShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * 3.demo: 将doc内容转化成String
     *
     */
    @Test
    public void doc2String() {
        File file = new File(filesDir,"poi-test.doc");
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

        /*
        不会提取图片，只是逐行提取文本，也会返回空行段落分隔符

        提取某doc的文本结果如下：
        1. debug
        1.Copy Value
        复制toString的值
        

        使用方法：debug窗口选中要复制的变量 -> Ctrl + C。

        查找实体类字段
        当实体类中拥有很多字段时，想要定位到某个字段有些费劲，此时可以通过idea进行查找
        使用：debug窗口 -> ctrl +F(注意，此时查找窗口不会弹出) -> 输入想要查询的字段 -> 如果idea找到对应的字段，就会高亮显示：
        

        注意：
        查找前，需要展开变量，否则查找不到。
        输入要查询的字段后，不要按回车，否则变量会被折叠，导致查找不到。

        jump to souce(F4)
        快速定位到变量定义的位置

        show types
        显示变量的类型，一般选中
        
        如果不选中：
        

        Evaluate Expression
        计算代码变量的详细值(以独立窗口呈现)，可以消除其他变量对观察的影响，可以进行一些表达式计算，如将对象转换成json字符串
        
        点击Evaluate Expression... -> Evaluate
        

        2.Git操作
        实例1：使用idea从Github远程仓库克隆
        打开idea设置(Settings)，设置Git安装目录
        
        
        点击Settings窗口OK，保存并应用。

        注：GitHub帐号设置，idea会在push等场景下自动使用并完成验证，建议设置，如下：
        
        克隆远程仓库中的项目到本地
        依次点击VCS(版本控制工具) -> Get from Version Control...(实际大概就是克隆的意思...)，如下：
        
        注：不同版本idea VSC选项可能不一样，请灵活应用
        然后就出现下面的弹窗：
        
        我们填写了仓库地址和保存文件夹后，点击clone开始克隆。
        说明：
        Repository URL：根据仓库地址进行克隆，可以使用https/ssh地址，都可以。
        URL：填写仓库地址。
        Directory：仓库中内容保存的文件夹，文件夹必须为空，建议设置成仓库名(因为仓库名一般为项目名)。
        等待克隆完成后，使用idea打开项目
        
        到此，项目克隆完毕。
        2.实例2：使用Idea删除远程分支
        
        注：点击Delete后会直接删除远程分支，不需要push，请谨慎操作。
        3.实例3：撤回提交(Reset Head)
        撤回commit使用Reset Head窗口，界面是这样的：
        

        撤回提交的前提是在已经commit但是未push的情况下：
        to Commit可选值（界面默认是HEAD，点击Reset没有效果）：
        1.HEAD^				撤回上次提交

        2.HEAD~N				撤回N次提交，N的值最大为当前未push的commit个数+2
        未push的commit个数+1		撤回当前所有未push的commit
        未push的commit个数+2(不推荐)	撤回，会把文件从git仓库中移除(此时文件红色，再次push会冲突)
        使用举例如，未撤回之前：
        
        HEAD~3后：
        
        可以看到撤回了3次提交


        3.commid id		退回到指定的commit提交后(注意，撤回后，id为commid id的commit已commit)
        如：
        

        如何查看commid id的位置：
        位置1：
        
        4.实例4：撤回修改(Revert Sekected Changes)
        与实例3不同的是，有的时候我们只想撤回某个文件的修改，Revert Sekected Changes可以恢复此次commit/push的某个文件的修改，而不必将commit/push的每个文件都恢复。

        使用如下：
        
        注：commit和push后都可以进行此操作，且只会撤回选中的修改，而不会影响该文件后面的修改。如：commit4 -> commit5这连续的2次提交中都修改了1.txt文件，那么我们撤回commit4中1.txt文件的修改，不会撤回commit5对1.txt的修改。
        5.实例5：撤回已经push的提交(Revert Commit)
        Revert Commit：
        
        测试1：
        
        测试2：
        
        6.实例6：合并提交(Interactively Rebase From Here)
        可以将多条commit合并为一个（需要是未push的commit）
        演示1：合并未push的提交：
        
        将5,6的更改合并到rebase4中
        

        Process finished with exit code 0

         */
        TestHelper.println(buffer.toString());
    }


    /**
     * 4.demo: 将docx内容转化成String
     *
     */
    @Test
    public void docx2String() {
        File file = new File(filesDir,"poi-test.docx");
        XWPFWordExtractor extractor = null;
        try {
            OPCPackage pkg = OPCPackage.open(new FileInputStream(file));
            extractor = new XWPFWordExtractor(pkg);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TestHelper.println(extractor.getText());
    }

    /**
     * 5.demo: 读取ppt文本
     *
     */
    @Test
    public void ppt2String(){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filesDir,"poi-test.pptx"));
            PowerPointExtractor extractor = new PowerPointExtractor(fileInputStream);
            TestHelper.println(extractor.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 6.demo: 读取pptx文本
     *
     */
    @Test
    public void pptx2String(){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filesDir,"poi-test.pptx"));
            XSLFPowerPointExtractor extractor = new XSLFPowerPointExtractor(OPCPackage.open(fileInputStream));
            TestHelper.println(extractor.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }

    }
}
