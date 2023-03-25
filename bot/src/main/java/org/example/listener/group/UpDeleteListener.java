package org.example.listener.group;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.example.Service.GroupService;
import org.example.listener.api.GroupMessageListener;
import org.example.utils.LogUtils;
import org.example.utils.RegexUtils;

/**
 * up主删除侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public class UpDeleteListener implements GroupMessageListener {

    private GroupService groupService;
    public UpDeleteListener(GroupService groupService) {
        this.groupService=groupService;
    }

    @Override
    public void function(GroupMessageEvent eventType) {
        String  message = eventType.getMessage().contentToString();
        if(message.startsWith("/delete")) {
            String[] uids= RegexUtils.getArgs(message);
            if (uids!=null) {
                Group group = eventType.getGroup();
                for (String uid : uids) {
                    if (RegexUtils.isUid(uid)) {
                        groupService.deleteUp(group.getId() + "",uid);
                        group.sendMessage("删除up主"+uid+"成功");
                        LogUtils.info(group.getId()+"删除了up主"+uid);
                    }
                }
            }
        }
    }
}
