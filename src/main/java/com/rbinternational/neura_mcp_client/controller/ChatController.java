package com.rbinternational.neura_mcp_client.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatClient chatClient;


    public ChatController(ChatClient.Builder chat, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chat.defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    @GetMapping
    public String chat(@RequestParam("q") String message) {
        PromptTemplate pt = new PromptTemplate(message);
        return this.chatClient.prompt(pt.create())
                .call()
                .content();
    }
}
