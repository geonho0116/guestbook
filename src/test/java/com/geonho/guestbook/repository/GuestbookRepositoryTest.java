package com.geonho.guestbook.repository;

import com.geonho.guestbook.entity.GuestBook;
import com.geonho.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test @Ignore
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            GuestBook guestBook = GuestBook.builder()
                    .title("title///"+i)
                    .content("content///"+i)
                    .writer("user"+(i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestBook));
        });
    }

    @Test @Ignore
    public void updateTest() {
        Optional<GuestBook> result = guestbookRepository.findById(300L);

        if (result.isPresent()) {
            GuestBook guestBook = result.get();

            guestBook.changeTitle("변경된타이틀");
            guestBook.changeContent("변경된컨텐츠");
            guestbookRepository.save(guestBook);
        }
    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        System.out.println(pageable);
        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);
        System.out.println(result);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
//        result.stream().forEach(System.out::println); //람다식
    }

    @Test
    public void testQuery2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestBook qGuestBook = QGuestBook.guestBook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);
        builder.and(exAll);
        builder.and(qGuestBook.gno.gt(0L));
        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);
//        result.stream().forEach(System.out::println);
        result.forEach(System.out::println);
    }


}
