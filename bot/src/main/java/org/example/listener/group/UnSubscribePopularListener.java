package org.example.listener.group;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.Service.GroupService;
import org.example.listener.api.GroupMessageListener;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
public class UnSubscribePopularListener implements GroupMessageListener {
    @Override
    public void function(GroupMessageEvent eventType) {
        String message = eventType.getMessage().contentToString();
        if (message.startsWith("/nopopular")){
            GroupService.INSTANCE.UnsubscribePopular(eventType.getGroup().getId()+"");
        }
    }
}
