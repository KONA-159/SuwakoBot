package org.example.controller;

import org.example.Service.BiliService;
import org.example.Service.GroupService;
import org.example.listener.bot.BotLeaveListener;
import org.example.listener.group.UpDeleteListener;
import org.example.listener.group.UpListListener;
import org.example.listener.group.UpSubscribeListener;
import org.example.utils.BotConfig;
import org.example.utils.LogUtils;
import org.example.utils.SqlUtils;
import org.example.utils.TimeUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 控制器
 *
 * @author 13988
 * @date 2023/03/09
 */
public class Controller {

    private BiliService biliService;
    private GroupService groupService;

    private BotConfig botConfig;

    /**
     * 控制器初始化
     *
     * @throws IOException ioexception
     */
    public Controller() throws IOException {
//        初始化service
        this.botConfig = new BotConfig();
        this.biliService = BiliService.INSTANCE;
        this.groupService = GroupService.INSTANCE;
        LogUtils.info("Service层初始化完成");
//        初始化数据库
        LogUtils.info("正在初始化数据库");
        SqlUtils.Init();
        LogUtils.info("数据库初始化完成");
//        初始化监听器
        LogUtils.info("正在初始化监听器");
        new BotLeaveListener(groupService);
        new UpDeleteListener(groupService);
        new UpListListener(groupService);
        new UpSubscribeListener(groupService);
        LogUtils.info("监听器初始化完成");
//        启动定时任务
        TimerInit();
        LogUtils.info("定时任务启动");
    }

    /**
     * 定时器初始化
     */
    public void TimerInit() {
//        每隔五分钟更新up主视频并推送
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    GroupService.INSTANCE.notifyNewVideo(BiliService.INSTANCE.biliPush());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1000 * 60 * 5);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    GroupService.INSTANCE.notifyPopularVideo(BiliService.INSTANCE.getPopularList());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, TimeUtils.marginOfTime(System.currentTimeMillis(),TimeUtils.getTomorrowTime(9,0,0).getTime()),1000*60*60*24);
    }
}
