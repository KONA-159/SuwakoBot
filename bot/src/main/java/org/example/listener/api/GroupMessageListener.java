package org.example.listener.api;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.utils.BotConfig;

/**
 * 群消息侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public interface GroupMessageListener extends Listener<GroupMessageEvent> {
    @Override
    default void listen(){
        BotConfig.getBot().getEventChannel().subscribeAlways(GroupMessageEvent.class,this::function);

    }

    @Override
    void function(GroupMessageEvent eventType) ;
}
