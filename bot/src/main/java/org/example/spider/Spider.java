package org.example.spider;

import lombok.Data;
import org.example.entity.Entity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

/**
 * 爬虫
 *
 * @author 13988
 * @date 2023/03/09
 */
public abstract class Spider {
    private static String PrefixApi;

    public Spider(String prefixApi) {
        this.PrefixApi=prefixApi;
    }

    /**
     * http get
     *
     * @param para 参数
     * @return {@link Result}
     * @throws IOException ioexception
     */
    public Result httpGet(String para) throws IOException {
//        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
//            final HttpGet httpget = new HttpGet(PrefixApi+para);
//            final Result result = httpclient.execute(httpget, response -> {
//                return new Result(response.getStatusLine().getStatusCode(),EntityUtils.toString(response.getEntity()));
//            });
//            httpclient.close();
//            return result;
//        }
        Connection.Response response = Jsoup.connect(PrefixApi + para).ignoreContentType(true).execute();
        return new Result(response.statusCode(),response.body());
    }

    public abstract Entity EntityGet() throws IOException;
    public abstract List<? extends Entity> EntitiesGet() throws IOException;

    @Data
    public static class Result {

        final int status;
        final String content;

        public Result(final int status, final String content) {
            this.status = status;
            this.content = content;
        }
    }
}
