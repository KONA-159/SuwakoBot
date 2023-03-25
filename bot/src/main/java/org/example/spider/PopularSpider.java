package org.example.spider;

import com.google.gson.Gson;
import org.example.entity.Entity;
import org.example.entity.PopularListEntity;
import org.example.entity.PopularVideoEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
public class PopularSpider extends Spider{
    public PopularSpider() {
        super("https://api.bilibili.com/x/web-interface/popular?pn=1");
    }

    @Override
    public Entity EntityGet() throws IOException {
        return null;
    }

    @Override
    public List<PopularVideoEntity> EntitiesGet() throws IOException {
        Gson gson=new Gson();
        PopularListEntity popularListEntity = gson.fromJson(super.httpGet("").content, PopularListEntity.class);
        List<PopularListEntity.DataDTO.ListDTO> list = popularListEntity.getData().getList();
        List<PopularVideoEntity> result=new ArrayList<PopularVideoEntity>();
        for (int i = 0; i < list.size(); i++) {
            PopularListEntity.DataDTO.ListDTO listDTO = list.get(i);
            result.add(new PopularVideoEntity(listDTO.getOwner().getName(),
                    listDTO.getPic(),
                    listDTO.getTitle(),
                    listDTO.getShortLink()));
        }
        return result;
    }
}
