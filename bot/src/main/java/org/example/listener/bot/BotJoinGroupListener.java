package org.example.listener.bot;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import org.example.listener.api.CustomListener;
import org.example.utils.BotConfig;
import org.example.utils.LogUtils;

/**
 * 机器人加入组监听器
 *
 * @author Shimarin
 * @date 2023/3/9
 */
public class BotJoinGroupListener extends CustomListener {
    public BotJoinGroupListener() {
        super.clazz=BotJoinGroupEvent.class;
        super.listen();
    }

    @Override
    public <T extends Event> void function(T event) {
        BotJoinGroupEvent eventType=(BotJoinGroupEvent) event;
        Group group = eventType.getGroup();
        LogUtils.info("机器人加入了一个新群"+group.getId());
        group.sendMessage(BotConfig.Botinfo());
    }
}
