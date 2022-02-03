package com.geonho.guestbook.repository;

import com.geonho.guestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<GuestBook,Long>, 
        QuerydslPredicateExecutor<GuestBook> { //querydsl을 사용하기 위한 추가 상속

}
