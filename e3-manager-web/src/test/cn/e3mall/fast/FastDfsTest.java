package cn.e3mall.fast;

import cn.e3mall.common.util.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @author 王兴毅
 * @date 2018.08.06 11:11
 */
public class FastDfsTest {

    @Test
    public void testUpload() throws Exception{
        ClientGlobal.init("D:\\myIDEA\\e3\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String[] strings = storageClient.upload_file("C:\\Users\\王兴毅\\Pictures\\19529864.jpg", "jpg", null);
        for (String s : strings){
            System.out.println(s);
        }
    }

    @Test
    public void testFastDfsClient() throws Exception{
        FastDFSClient fastDFSClient = new FastDFSClient("D:\\myIDEA\\e3\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        String s = fastDFSClient.uploadFile("C:\\Users\\王兴毅\\Pictures\\3e610001b7e603106d01.jpg");
        System.out.println(s);
    }
}
