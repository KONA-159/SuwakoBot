package org.example.Service;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.example.entity.GroupRecord;
import org.example.entity.PopularVideoEntity;
import org.example.entity.UpLatestVideoEntity;
import org.example.mapper.GroupMapper;
import org.example.mapper.PopularGroupidMapper;
import org.example.mapper.UpLatestVideoEntityMapper;
import org.example.utils.BotConfig;
import org.example.utils.LogUtils;
import org.example.utils.ResourceUtils;
import org.example.utils.SqlUtils;

import java.io.IOException;
import java.util.List;

/**
 * 群组服务
 *
 * @author 13988
 * @date 2023/03/09
 */
public class GroupService {

    private GroupMapper groupMapper;
    private UpLatestVideoEntityMapper upLatestVideoEntityMapper;

    private PopularGroupidMapper popularGroupidMapper;

    public final static GroupService INSTANCE = new GroupService();

    private GroupService() {
        this.groupMapper = SqlUtils.getSession().getMapper(GroupMapper.class);
        this.upLatestVideoEntityMapper = SqlUtils.getSession().getMapper(UpLatestVideoEntityMapper.class);
    }

    /**
     * 订阅up
     *
     * @param groupId 组id
     * @param uid     uid
     */
    public void SubscribeUp(String groupId, String uid) {
        if (upNotExist(uid)) {
            try {
                BiliService.INSTANCE.SubscribeUp(uid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!(getUpUidsFromGroupId(groupId).contains(uid))) {
            groupMapper.insert(new GroupRecord(groupId, uid));
        }
    }


    /**
     * 删除up
     *
     * @param groupId 组id
     * @param uid     uid
     */
    public void deleteUp(String groupId, String uid) {
        if (getUpUidsFromGroupId(groupId).contains(uid))
            groupMapper.deleteRecord(new GroupRecord(groupId, uid));
        if (upNotExist(uid)) {
            upLatestVideoEntityMapper.deleteByUid(uid);
        }
    }

    /**
     * 删除组
     *
     * @param groupId 组id
     */
    public void deleteGroup(String groupId) {
        List<String> uids = groupMapper.selectByGroupId(groupId);
        groupMapper.deleteByGroup(groupId);
        for (String uid : uids) {
            if (upNotExist(uid)) {
                upLatestVideoEntityMapper.deleteByUid(uid);
            }
        }
    }

    /**
     * 获取该群组关注的up主列表
     *
     * @param groupId 组id
     * @return {@link List}<{@link String}>
     */
    public List<String> getUpUidsFromGroupId(String groupId) {
        return groupMapper.selectByGroupId(groupId);
    }

    /**
     * 检测是否还有群组订阅了该up
     *
     * @param uid uid
     * @return boolean
     */
    private boolean upNotExist(String uid) {
        return groupMapper.selectByUid(uid).isEmpty();
    }

    /**
     * 查看该群组关注的UP主名字
     *
     * @param groupId 组id
     * @return {@link List}<{@link String}>
     */
    public List<String> getUpNamesFromGroupId(String groupId) {
        return groupMapper.selectAuthorByGroup(groupId);
    }

    /**
     * 通知新视频
     * 如果某up主更新了，向订阅ta的所有群组通知
     *
     * @param ups 联合包裹
     */
    public void notifyNewVideo(List<UpLatestVideoEntity> ups) {
        for (UpLatestVideoEntity up : ups) {
            List<String> toNotifyGroupIds = groupMapper.selectByGroupId(up.getUid());
//            UpLatestVideoEntity upLatestVideoEntity = upLatestVideoEntityMapper.selectByUid(uid);
            for (String groupId : toNotifyGroupIds) {
                Bot bot = BotConfig.getBot();
                Group notifyGroup = bot.getGroup(Long.parseLong(groupId));
                //            开始组装信息
                MessageChainBuilder chain = new MessageChainBuilder();
//                chain.append("你订阅的up主：\n").append(upLatestVideoEntity.getAuthor()).append("发布了新视频\n").append(upLatestVideoEntity.getTitle()).append("\n");
                chain.append("你订阅的up主：\n").append(up.getAuthor()).append("发布了新视频\n").append(up.getTitle()).append("\n");
                //            导入图片
                LogUtils.info("正在获取图片...");
                Image image = null;
                try {
//                    image = notifyGroup.uploadImage(ResourceUtils.getResourceFromUrl(upLatestVideoEntity.getPic()));
                    image = notifyGroup.uploadImage(ResourceUtils.getResourceFromUrl(up.getPic()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LogUtils.info("图片获取成功！");
                chain.append(image);
//                chain.append("快来看看吧：https://www.bilibili.com/video/").append(upLatestVideoEntity.getBvid());
                chain.append("快来看看吧：https://www.bilibili.com/video/").append(up.getBvid());
                MessageChain messages = chain.build();
                notifyGroup.sendMessage(messages);
            }
        }
        LogUtils.info("更新已推送！");
    }

    /**
     * 通知流行视频
     *
     * @param videos 视频
     */
    public void notifyPopularVideo(List<PopularVideoEntity> videos) {
        List<String> toNotifyGroupIds = popularGroupidMapper.getGroupIds();
//            UpLatestVideoEntity upLatestVideoEntity = upLatestVideoEntityMapper.selectByUid(uid);
        for (String groupId : toNotifyGroupIds) {
            Bot bot = BotConfig.getBot();
            Group notifyGroup = bot.getGroup(Long.parseLong(groupId));
            //            开始组装信息
            MessageChainBuilder chain = new MessageChainBuilder();
            chain.append("今日热门：\n");
            for (PopularVideoEntity video : videos) {
                chain.append(video.getTitle() + '\n' + video.getAuthor());

                //            导入图片
                Image image = ResourceUtils.getImageFromUrl(notifyGroup, video.getPic());
                ResourceUtils.getImageFromUrl(notifyGroup, video.getPic());
                chain.append(image);
                chain.append("快来看看吧：").append(video.getLink()).append("\n\n");
            }
            MessageChain messages = chain.build();
            notifyGroup.sendMessage(messages);
        }
        LogUtils.info("每日热门已推送！");
    }

    /**
     * 订阅热门
     *
     * @param groupId 组id
     */
    public void subscribePopular(String groupId){
        //todo 优化
        if (popularGroupidMapper.getGroupIds().contains(groupId)) {
            popularGroupidMapper.insertGroupId(groupId);
        }
    }

    /**
     * 退订热门
     *
     * @param groupId 组id
     */
    public void UnsubscribePopular(String groupId){
        //todo 优化
        popularGroupidMapper.deleteGroupId(groupId);
    }
}
