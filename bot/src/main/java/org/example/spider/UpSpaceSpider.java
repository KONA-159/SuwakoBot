package org.example.spider;

import com.google.gson.Gson;
import org.example.entity.Entity;
import org.example.entity.SpaceEntity;
import org.example.entity.UpLatestVideoEntity;

import java.io.IOException;
import java.util.List;

/**
 * Bilibili爬虫
 *
 * @author 13988
 * @date 2023/03/09
 */
public class UpSpaceSpider extends Spider{

    private String uid;
    public UpSpaceSpider(String uid) {
        super( "https://api.bilibili.com/x/space/arc/search?mid=");
        this.uid=uid;
    }

    /**
     * 根据uid爬取个人空间
     * @return
     * @throws IOException
     */
    @Override
    public Entity EntityGet() throws IOException {
        Gson gson=new Gson();
        SpaceEntity space = gson.fromJson(super.httpGet(uid).content, SpaceEntity.class);
        SpaceEntity.DataDTO.ListDTO.VlistDTO video = space.getData().getList().getVlist().get(0);
        return new UpLatestVideoEntity(
                uid,
                video.getTitle(),
                video.getPic(),
                video.getAuthor(),
                video.getCreated().toString(),
                video.getBvid()
        );
    }

    @Override
    public List<Entity> EntitiesGet() throws IOException {
        return null;
    }
}
