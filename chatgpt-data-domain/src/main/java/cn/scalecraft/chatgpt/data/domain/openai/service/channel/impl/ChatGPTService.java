package cn.scalecraft.chatgpt.data.domain.openai.service.channel.impl;

import cn.scalecraft.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import cn.scalecraft.chatgpt.data.domain.openai.model.valobj.GenerativeModelVO;
import cn.scalecraft.chatgpt.data.domain.openai.service.channel.OpenAiGroupService;
import cn.scalecraft.chatgpt.data.domain.openai.service.channel.model.IGenerativeModelService;
import cn.scalecraft.chatgpt.data.domain.openai.service.channel.model.impl.ImageGenerativeModelServiceImpl;
import cn.scalecraft.chatgpt.data.domain.openai.service.channel.model.impl.TextGenerativeModelServiceImpl;
import cn.bugstack.chatgpt.session.OpenAiSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.HashMap;
import java.util.Map;


@Service
public class ChatGPTService implements OpenAiGroupService {

    @Autowired(required = false)
    protected OpenAiSession chatGPTOpenAiSession;

    private final Map<GenerativeModelVO, IGenerativeModelService> generativeModelGroup = new HashMap<>();

    public ChatGPTService(ImageGenerativeModelServiceImpl imageGenerativeModelService, TextGenerativeModelServiceImpl textGenerativeModelService) {
        generativeModelGroup.put(GenerativeModelVO.IMAGES, imageGenerativeModelService);
        generativeModelGroup.put(GenerativeModelVO.TEXT, textGenerativeModelService);
    }

    @Override
    public void doMessageResponse(ChatProcessAggregate chatProcess, ResponseBodyEmitter emitter) throws Exception {
        GenerativeModelVO generativeModelVO = chatProcess.getGenerativeModelVO();
        generativeModelGroup.get(generativeModelVO).doMessageResponse(chatProcess, emitter);
    }

}
