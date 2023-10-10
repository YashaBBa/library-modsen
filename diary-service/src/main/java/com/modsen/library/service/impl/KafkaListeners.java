package com.modsen.library.service.impl;

import com.modsen.library.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListeners {
    private final DiaryService diaryService;

    @KafkaListener(topics = "library", groupId = "myGroup")
    void listener(Long id) {
        diaryService.addBookInDiary(id);
    }

}
