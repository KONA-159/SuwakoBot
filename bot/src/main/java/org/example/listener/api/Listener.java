package org.example.listener.api;

import net.mamoe.mirai.event.Event;

import java.io.IOException;

/**
 * 侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public interface Listener<T extends Event> {
    void listen();

    void function(T eventType) throws IOException;
}
