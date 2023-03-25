package org.example.listener.group;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.listener.api.GroupMessageListener;
import org.example.utils.BotConfig;

/**
 * @author Shimarin
 * @date 2023/3/9
 */
public class InfoListener implements GroupMessageListener {
    @Override
    public void function(GroupMessageEvent eventType) {
        String message = eventType.getMessage().contentToString();
        if (message.startsWith("/info")) {
            Group group = eventType.getGroup();
            group.sendMessage(BotConfig.Botinfo());
        }
    }
}
