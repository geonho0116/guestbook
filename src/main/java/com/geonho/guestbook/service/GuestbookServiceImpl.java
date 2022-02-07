package com.geonho.guestbook.service;

import com.geonho.guestbook.dto.GuestbookDTO;
import com.geonho.guestbook.dto.PageRequestDTO;
import com.geonho.guestbook.dto.PageResultDTO;
import com.geonho.guestbook.entity.GuestBook;
import com.geonho.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestbookDTO dto) {

        System.out.println("dto : "+dto.toString());

        GuestBook entity = dtoToEntity(dto);

        System.out.println("entity : "+entity.toString());

        repository.save(entity); //register

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<GuestBook> result = repository.findAll(pageable);
        Function<GuestBook, GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result,fn);
    }


}
