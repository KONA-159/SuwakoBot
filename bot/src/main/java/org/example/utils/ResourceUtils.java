package org.example.utils;

//import io.ktor.http.Url;

//import io.ktor.http.Url;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;


public class ResourceUtils {
    /**
     * 从url获取资源
     *
     * @param urlString url字符串
     * @return {@link ExternalResource}
     * @throws IOException ioexception
     */
    public static ExternalResource getResourceFromUrl(String urlString) throws IOException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(urlString);
            final HttpEntity result = httpclient.execute(httpget).getEntity();
            InputStream content = result.getContent();
            byte[] bytes = content.readAllBytes();
            httpclient.close();
            return ExternalResource.Companion.create(bytes);
        }
    }

    public static Image getImageFromUrl(Group group,String url){
        LogUtils.info("正在获取图片...");
        Image image = null;
        try {
//                    image = notifyGroup.uploadImage(ResourceUtils.getResourceFromUrl(upLatestVideoEntity.getPic()));
            image = group.uploadImage(ResourceUtils.getResourceFromUrl(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogUtils.info("图片获取成功！");
        return image;
    }
}
