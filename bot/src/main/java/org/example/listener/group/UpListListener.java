package org.example.listener.group;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.example.Service.GroupService;
import org.example.listener.api.GroupMessageListener;

import java.util.List;

/**
 * 查询up主侦听器
 *
 * @author 13988
 * @date 2023/03/09
 */
public class UpListListener implements GroupMessageListener {

    private GroupService groupService;

    public UpListListener(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void function(GroupMessageEvent eventType) {
        String  message = eventType.getMessage().contentToString();
        if(message.startsWith("/uplist")) {
            Group group = eventType.getGroup();
            List<String> authors= groupService.getUpNamesFromGroupId(group.getId()+"");
            MessageChainBuilder chain = new MessageChainBuilder();
            chain.append("当前关注列表中的up主：");
            for (String author : authors) {
                chain.append(String.valueOf('\n')).append(author);
            }
            MessageChain messages= chain.build();
            group.sendMessage(messages);
        }
    }
}
