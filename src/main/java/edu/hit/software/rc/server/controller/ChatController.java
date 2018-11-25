package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.ChatRecord;
import edu.hit.software.rc.server.service.ChatService;
import edu.hit.software.rc.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/chat")
public class ChatController implements Controller {
    private final ChatService chatService;
    private final PermissionService permissionService;

    @Autowired
    public ChatController(ChatService chatService, PermissionService permissionService) {
        this.chatService = chatService;
        this.permissionService = permissionService;
    }

    @RequestMapping("/records/send/user")
    public List<ChatRecord> recordsSendUser(long user, Instant begin, Instant end){
        return chatService.getChatRecordsSendBy(user, begin, end);
    }
    @RequestMapping("/records/receive/group")
    public List<ChatRecord> recordsReceiveGroup(long target, Instant begin, Instant end){
        return chatService.getChatRecordsSendToGroup(target, begin, end);
    }
    @RequestMapping("/records/receive/user")
    public List<ChatRecord> recordsReceiveUser(long target, Instant begin, Instant end, HttpSession session){
        if(requiresAccount(target, session)){
            return chatService.getChatRecordsSendToUser(target, begin, end);
        }else{
            return List.of();
        }
    }
    @RequestMapping("/send")
    public Instant send(long user, long target, long targetGroup, String content, HttpSession session){
        if(requiresAccount(user, session)){
            ChatRecord chatRecord = new ChatRecord();
            chatRecord.setPublisher(user);
            chatRecord.setTarget(target);
            chatRecord.setTargetGroup(targetGroup);
            chatRecord.setContent(content);
            if(chatService.send(chatRecord)){
                return chatRecord.getTime();
            }
        }
        return null;
    }
}
