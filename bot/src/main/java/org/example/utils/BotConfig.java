package org.example.utils;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.PlainText;

/**
 * 机器人配置
 *
 * @author 13988
 * @date 2023/03/09
 */
public class BotConfig {

    private static Bot bot;

    public BotConfig() {
        bot=Bot.getInstances().get(0);
    }

    public static Bot getBot() {
        return bot;
    }

    public static void setBot(Bot bot) {
        BotConfig.bot = bot;
    }

    public static PlainText Botinfo(){
        return new PlainText("Bili推送功能测试版\n\n输入：" +
                "\n\n/info查看使用说明" +
                "\n\n/add uid订阅up主" +
                "\n\n/delete uid删除订阅" +
                "\n\n/uplist查看当前订阅的up主列表" +
                "\n\n/popular订阅每日热门视频，将在9:00推送" +
                "\n\n/nopopular取消订阅每日热门视频");
    }
}
