package cn.e3mall.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王兴毅
 * @date 2018.08.10 12:24
 */
public class FreeMarkerTest {

    @Test
    public void testFreeMarker() throws Exception{
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("D:\\myIDEA\\e3\\e3-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("hello.ftl");
        Map data = new HashMap<>();
        data.put("hello", "hello freemarker");
        Writer out = new FileWriter(new File("E:\\freemarker\\hello.txt"));
        template.process(data, out);
        out.close();
    }
}
