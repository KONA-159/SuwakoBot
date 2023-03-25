package org.example.listener.api;

import net.mamoe.mirai.event.Event;
import org.example.utils.BotConfig;

/**
 * 自定义侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public abstract class CustomListener{
    protected Class<? extends Event> clazz=null;
    public void listen(){
        BotConfig.getBot().getEventChannel().subscribeAlways(clazz,this::function);
    }

    public abstract <T extends Event> void function(T event);


}
