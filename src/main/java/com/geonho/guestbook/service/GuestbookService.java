package com.geonho.guestbook.service;

import com.geonho.guestbook.dto.GuestbookDTO;
import com.geonho.guestbook.dto.PageRequestDTO;
import com.geonho.guestbook.dto.PageResultDTO;
import com.geonho.guestbook.entity.GuestBook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    default GuestBook dtoToEntity(GuestbookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(GuestBook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
