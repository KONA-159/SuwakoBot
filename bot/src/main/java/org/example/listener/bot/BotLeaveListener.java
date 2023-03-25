package org.example.listener.bot;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotLeaveEvent;
import org.example.Service.GroupService;
import org.example.listener.api.CustomListener;

/**
 * 机器人退群侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public class BotLeaveListener extends CustomListener {

    GroupService groupService;
    public BotLeaveListener(GroupService groupService) {
        this.groupService=groupService;
        super.clazz= BotLeaveEvent.class;
        super.listen();
    }

    @Override
    public <T extends Event> void function(T event) {
        BotLeaveEvent botLeaveEvent=(BotLeaveEvent) event;
        groupService.deleteGroup(botLeaveEvent.getGroupId()+"");
    }
}
