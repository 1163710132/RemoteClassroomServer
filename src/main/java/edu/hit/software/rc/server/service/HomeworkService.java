package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Answer;
import edu.hit.software.rc.server.entity.Homework;
import edu.hit.software.rc.server.entity.HomeworkEntry;
import edu.hit.software.rc.server.entity.Question;
import edu.hit.software.rc.server.mapper.AnswerMapper;
import edu.hit.software.rc.server.mapper.HomeworkEntryMapper;
import edu.hit.software.rc.server.mapper.HomeworkMapper;
import edu.hit.software.rc.server.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class HomeworkService {
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final HomeworkMapper homeworkMapper;
    private final HomeworkEntryMapper homeworkEntryMapper;

    @Autowired
    public HomeworkService(
            QuestionMapper questionMapper,
            AnswerMapper answerMapper,
            HomeworkMapper homeworkMapper,
            HomeworkEntryMapper homeworkEntryMapper) {
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
        this.homeworkMapper = homeworkMapper;
        this.homeworkEntryMapper = homeworkEntryMapper;
    }

    public Question createQuestion(long publisherId, String content){
        Question question = new Question();
        question.setPublisher(publisherId);
        question.setContent(content);
        question.setStandard(-1);
        if(questionMapper.insertQuestion(question) > 0){
            return question;
        }else{
            return null;
        }
    }

    public Answer createAnswer(long publisherId, long questionId, String content){
        Answer answer = new Answer();
        answer.setPublisher(publisherId);
        answer.setQuestion(questionId);
        answer.setContent(content);
        answer.setTime(Instant.now());
        if(answerMapper.insertAnswer(answer) > 0){
            return answer;
        }else{
            return null;
        }
    }

    public Homework createHomework(String name, long publisherId, long targetId, long rater, Instant begin, Instant end, long[] questionIds){
        Homework homework = new Homework();
        homework.setName(name);
        homework.setPublisher(publisherId);
        homework.setTarget(targetId);
        homework.setBegin(begin);
        homework.setEnd(end);
        homework.setCanceled(false);
        homework.setPublished(Instant.now());
        homework.setRater(rater);
        if(homeworkMapper.insertHomework(homework) > 0){
            for(int i = 0;i < questionIds.length;i++){
                HomeworkEntry entry = new HomeworkEntry();
                entry.setHomework(homework.getId());
                entry.setQIndex(i);
                entry.setQuestion(questionIds[i]);
                homeworkEntryMapper.insertEntry(entry);
            }
            return homework;
        }else{
            return null;
        }
    }

    public List<Long> selectFutureHomework(long groupId, Instant since){
        return homeworkMapper.selectFutureHomework(groupId, since);
    }

    public List<Long> selectPastHomework(long groupId, Instant until){
        return homeworkMapper.selectFutureHomework(groupId, until);
    }

    public List<Long> selectNowHomework(long groupId, Instant now){
        return homeworkMapper.selectFutureHomework(groupId, now);
    }

    public boolean setStandardAnswer(long questionId, long answerId){
        return questionMapper.updateStandardAnswer(questionId, answerId) > 0;
    }

    public Question getQuestion(long questionId){
        return questionMapper.getQuestionById(questionId);
    }

    public Answer getAnswer(long answerId){
        return answerMapper.getAnswerById(answerId);
    }

    public long selectLastAnswer(long publisherId, long questionId, Instant ddl){
        return answerMapper.selectLastAnswer(publisherId, questionId, ddl);
    }

    public Homework getHomework(long homeworkId){
        return homeworkMapper.getHomeworkById(homeworkId);
    }

    public List<Homework> getHomeworkByGroup(long groupId){
        return homeworkMapper.getHomeworkByTargetGroup(groupId);
    }

    public HomeworkEntry getHomeworkEntry(long homeworkId, int qIndex){
        return homeworkEntryMapper.getEntryByHomeworkAndQIndex(homeworkId, qIndex);
    }

    public List<HomeworkEntry> getHomeworkEntries(long homeworkId){
        return homeworkEntryMapper.getEntriesByHomework(homeworkId);
    }

    public boolean cancelHomework(long homeworkId){
        return homeworkMapper.cancelHomework(homeworkId) > 0;
    }

}
