package org.example.listener.group;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.Service.GroupService;
import org.example.listener.api.GroupMessageListener;
import org.example.utils.LogUtils;
import org.example.utils.RegexUtils;

/**
 * 订阅监听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public class UpSubscribeListener implements GroupMessageListener {

    private GroupService groupService;

    public UpSubscribeListener(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void function(GroupMessageEvent eventType)  {
        String message = eventType.getMessage().contentToString();
        if (message.startsWith("/add")) {
            String[] uids = RegexUtils.getArgs(message);
            if (uids != null) {
                Group group = eventType.getGroup();
                for (String uid : uids) {
                    if (RegexUtils.isUid(uid)) {
                            groupService.SubscribeUp(group.getId() + "", uid);
                            group.sendMessage("添加up主"+uid+"成功");
                            LogUtils.info(group.getId()+"订阅了up主"+uid);
                    }
                }
            }
        }
    }
}
