package org.example.Service;

import lombok.Data;

import org.example.entity.*;
import org.example.mapper.GroupMapper;
import org.example.mapper.UpLatestVideoEntityMapper;
import org.example.spider.PopularSpider;
import org.example.spider.Spider;
import org.example.spider.UpSpaceSpider;
import org.example.utils.LogUtils;
import org.example.utils.SqlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Bilibili服务
 *
 * @author 13988
 * @date 2023/03/09
 */
@Data
public class BiliService {

    private GroupMapper groupMapper;
    private UpLatestVideoEntityMapper upLatestVideoEntityMapper;
    private Spider biliSpider;

    private Spider popularSpider;

    public final static BiliService INSTANCE=new BiliService();

    private BiliService() {
        this.groupMapper = SqlUtils.getSession().getMapper(GroupMapper.class);
        this.upLatestVideoEntityMapper=SqlUtils.getSession().getMapper(UpLatestVideoEntityMapper.class);
    }


    /**
     * 订阅up主
     *
     * @param uid uid
     * @throws IOException ioexception
     */
    public void SubscribeUp(String uid) throws IOException {
        UpLatestVideoEntity upLatestVideoByUid = getUpLatestVideoByUid(uid);
        upLatestVideoEntityMapper.insertVideo(upLatestVideoByUid);
    }

    /**
     * 根据uid获取最新视频
     *
     * @param uid uid
     * @return {@link UpLatestVideoEntity}
     * @throws IOException ioexception
     */
    public UpLatestVideoEntity getUpLatestVideoByUid(String uid) throws IOException {
        Spider spider = new UpSpaceSpider(uid);
        UpLatestVideoEntity upLatestVideoEntity = (UpLatestVideoEntity) spider.EntityGet();
        return upLatestVideoEntity;
    }

    /**
     * 获取所有订阅的up主个人空间
     *
     * @return {@link List}<{@link UpLatestVideoEntity}>
     */
    public List<UpLatestVideoEntity> getAllUp(){
        return upLatestVideoEntityMapper.findAll();
    }

    /**
     * 更新数据库中up主最新视频
     *
     * @param video 视频
     */
    public void update(UpLatestVideoEntity video){
        upLatestVideoEntityMapper.updateLatestVideo(video);
    }


    /**
     * 更新所有up主的最新视频
     *
     * @return {@link List}<{@link UpLatestVideoEntity}>
     * @throws IOException ioexception
     */
    public List<UpLatestVideoEntity> biliPush() throws IOException {
        List<UpLatestVideoEntity> result=new ArrayList<UpLatestVideoEntity>();
        List<UpLatestVideoEntity> ups = getAllUp();
//        遍历所有群订阅的up主列表
        for (int i = 0; i < ups.size(); i++) {
            String uid = ups.get(i).getUid();
            biliSpider = new UpSpaceSpider(uid);
            LogUtils.info("正在获取最新视频...uid="+uid);
            //爬当前up的个人空间并得到最新视频
            UpLatestVideoEntity latestVideo = getUpLatestVideoByUid(uid);
            LogUtils.info("成功获取最新视频");
            if (!ups.get(i).getBvid().equals(latestVideo.getBvid())) {
                update(latestVideo);
                LogUtils.info(uid+latestVideo.getAuthor()+"最新视频已更新");
                result.add(latestVideo);
//                GroupService.INSTANCE.notifyNewVideo(uid);
            }
        }
        return result;
    }

    public List<PopularVideoEntity> getPopularList() throws IOException {
        PopularSpider popularSpider = new PopularSpider();
        return popularSpider.EntitiesGet();
    }



}
