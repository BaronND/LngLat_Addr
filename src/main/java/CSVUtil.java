/**
 * Created by yinyi on 2019/8/9.
 */
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static char separator = ',';

    public static void main(String[] args) throws Exception {
        // 测试导出
        //E:/临时文档/web_test/test/test_data.csv   /mnt/yinyi/lnglat/test_data/lnglat_addr_csv/p001.csv
        String fname=args[0].toString();
        String rfilePath = "/mnt/yinyi/lnglat/test_data/lnglat_addr_csv/p00"+fname+".csv";
        readCSV(rfilePath,fname);
    }

    /**
     * 读取CSV文件
     * @param filePath:全路径名
     */
    public static List<String> readCSV(String filePath,String wf) throws Exception {

        //E:/临时文档/web_test/test/result.csv  /mnt/yinyi/lnglat/result_data/lnglat_result_xls/1.csv
        String wfilePaht="/mnt/yinyi/lnglat/result_data/lnglat_result_xls/p00"+wf+".csv";
        CsvReader reader = null;
        List<String> dataList = new ArrayList<String>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePath, separator, Charset.forName("UTF-8"));
            reader.setSafetySwitch(false);//否则超过10W行就报错

            reader.readHeaders();
            while (reader.readRecord()) {
                String returnValue = AddrLngLatUtil.transAddToLngLat(reader.get(0));
                dataList.add(reader.get(0)+"_"+returnValue);
                long line = reader.getCurrentRecord();
                System.out.println("行数"+line+":"+reader.get(0)+"_"+returnValue);
                if(line %5000 ==0){
                    createCSV(dataList, wfilePaht);
                    dataList.clear();
                    System.out.println("===============写入完成当前行数================="+line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return dataList;
    }

    /**
     * 生成CSV文件
     * @param dataList:数据集
     * @param filePath:全路径名
     */
    public static boolean createCSV(List<String> dataList, String filePath) throws Exception {
        boolean isSuccess = false;
        CsvWriter writer = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath, true);
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            writer = new CsvWriter(out, separator, Charset.forName("UTF-8"));
            for (String strs : dataList) {
                writer.writeComment(strs);
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }
}


