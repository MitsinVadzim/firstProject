package com.example.firstProject;

import com.example.firstProject.domain.Message;
import com.example.firstProject.repos.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    private final IMessageRepository messageRepository;

    @Autowired
    public GreetingController(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag) {
        Message message = new Message(text, tag);
        messageRepository.save(message);
        return "redirect:/main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if (filter!=null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        }else{
            messages = messageRepository.findAll();
        }
        model.put("messages", messages);
        return "main";
    }

}
