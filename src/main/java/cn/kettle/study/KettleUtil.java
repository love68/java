package cn.kettle.study;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.io.File;
import java.util.Map;

public class KettleUtil {
    public String RES_DIR = "src\\main\\resources";
    private String fullFileName;

    public KettleUtil(String fileName) {
        //设定ktr的文件所在路径，这里可自行根据项目classpath替换
        //这样设定的结果，如果跑在tomcat中，则需要把ktr放到apache-tomcat-7.0.70\bin\res路径下。
        fullFileName = System.getProperty("user.dir") + File.separator + RES_DIR;
        fullFileName += File.separator + fileName;

    }
    /**
     * 没有参数是,设置参数为null
     *
     * @param paras
     */
    public void runTransformation(Map<String, String> paras) {
        try {
            //kettle初始化
            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta(fullFileName);
            Trans transformation = new Trans(transMeta);
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                //向ktr中传值
                transformation.setVariable(entry.getKey(), entry.getValue());
            }
            transformation.execute(null);
            transformation.waitUntilFinished();
            if (transformation.getErrors() > 0) {
                throw new RuntimeException(
                        "There wereerrors during transformation execution.");
            }
        } catch (KettleException e) {
            System.out.println(e);
        }
    }
}

