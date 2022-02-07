package com.geonho.guestbook.service;

import com.geonho.guestbook.dto.GuestbookDTO;
import com.geonho.guestbook.dto.PageRequestDTO;
import com.geonho.guestbook.dto.PageResultDTO;
import com.geonho.guestbook.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title")
                .content("sample content")
                .writer("user0")
                .build();
        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV:"+resultDTO.isPrev());
        System.out.println("NEXT:"+resultDTO.isNext());
        System.out.println("TOTAL:"+resultDTO.getTotalPage());
        System.out.println("--------------------------------");

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);

        }
    }
}
