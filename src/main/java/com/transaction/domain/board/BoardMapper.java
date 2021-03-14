package com.transaction.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardMapper {
    int setBoard(@Param("title") String title, @Param("content") String content);
}
