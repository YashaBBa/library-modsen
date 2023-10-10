package com.modsen.library.dto.mapper;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.Diary;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryMapper {
    public Diary toDiary(List<Book> list){
        return new Diary(list);
    }


}
