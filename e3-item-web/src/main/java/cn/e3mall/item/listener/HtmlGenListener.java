package cn.e3mall.item.listener;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王兴毅
 * @date 2018.08.11 08:15
 */
public class HtmlGenListener implements MessageListener {

    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${HTML_GEN_PATH}")
    private String HTML_GEN_PATH;

    @Override
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            Long itemId = new Long(text);
            //等待事务提交
            Thread.sleep(1000);
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            Map data = new HashMap<>();
            data.put("item", item);
            data.put("itemDesc", itemDesc);
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Writer out = new FileWriter(HTML_GEN_PATH + itemId + ".html");
            template.process(data, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
