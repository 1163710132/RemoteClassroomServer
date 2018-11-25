package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.ChatRecord;
import edu.hit.software.rc.server.mapper.ChatRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChatService {
    private final ChatRecordMapper chatRecordMapper;

    @Autowired
    public ChatService(ChatRecordMapper chatRecordMapper) {
        this.chatRecordMapper = chatRecordMapper;
    }

    public ChatRecord getChatRecordById(long id){
        return chatRecordMapper.getChatRecordById(id);
    }

    public List<ChatRecord> getChatRecordsSendBy(long publisher, Instant begin, Instant end){
        return chatRecordMapper.getRecordsByPublisher(publisher, begin, end);
    }

    public List<ChatRecord> getChatRecordsSendToGroup(long group, Instant begin, Instant end){
        return chatRecordMapper.getRecordsByTargetGroup(group, begin, end);
    }

    public List<ChatRecord> getChatRecordsSendToUser(long user, Instant begin, Instant end){
        return chatRecordMapper.getRecordsByTarget(user, begin, end);
    }

    public boolean send(ChatRecord chatRecord){
        return chatRecordMapper.insertChatRecord(chatRecord) > 0;
    }
}
