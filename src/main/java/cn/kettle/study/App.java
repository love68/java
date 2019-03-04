package cn.kettle.study;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception{
        System.out.println("starting");;
        kettleExcelToSql("","");
        System.out.println("over");
    }
    public static void kettleExcelToSql(String fileUrl,String pc)throws Exception{
        //fileUrl和pc等变量自行修改
        fileUrl ="D:\\dev\\pdi-ce-7.1.0.0-12\\user.xls";
        pc = "已入库";
        //调用ktr文件的文件名为excelToSql.ktr
        //就是放到apache-tomcat-7.0.70\bin\res路径下的ktr文件
        KettleUtil etl = new KettleUtil("test1.ktr");
        Map<String,String> para = new HashMap<String,String>();
        //给转换中命名参数赋值，这里将需要解析的excel文件路径fileUrl传到ktr中
        para.put("FILE_PATH", fileUrl);
        //传递数值，并将其同excel的数据一起入库
        para.put("PC", pc);
        etl.runTransformation(para);
    }
}
