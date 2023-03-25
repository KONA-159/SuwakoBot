package org.example.listener.group;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.Service.GroupService;
import org.example.listener.api.GroupMessageListener;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
public class SubscribePopularListener implements GroupMessageListener {
    @Override
    public void function(GroupMessageEvent eventType) {
        String message = eventType.getMessage().contentToString();
        if (message.startsWith("/popular")){
            GroupService.INSTANCE.subscribePopular(eventType.getGroup().getId()+"");
        }
    }
}
