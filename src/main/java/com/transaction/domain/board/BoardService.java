package com.transaction.domain.board;

import com.transaction.entities.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
//@Transactional(transactionManager = "mainTransactionManager")
//@Transactional(transactionManager = "subTransactionManager")
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public int saveWithRequired(int insertedMemberId) {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        boardMapper.setBoard("title-mybatis-test", "content-test");
        throw new IllegalStateException("this method throw exception");
    }

    public int jpaSaveWithRequired() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Board board = Board.createBoard("title-jpa-test", "jpa-content-test");
        boardRepository.save(board);
        throw new IllegalStateException("this method throw exception");
    }

    public void jpaAndMybatisSaveWithRequired() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Board board = Board.createBoard("title-jpaAndMybatis-test", "jpaAndMybatis-content-test");
        boardRepository.save(board);
        boardMapper.setBoard("title-jpaAndMybatis-test", "content-jpaAndMybatis-test");
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveWithRequiresNew() {
        boardMapper.setBoard("title-REQUIRES_NEW", "content-REQUIRES_NEW");
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NESTED)
    public int saveWithNested() {
        boardMapper.setBoard("title-NESTED", "content-NESTED");
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int saveWithNotSupported() {
        boardMapper.setBoard("title-NOT_SUPPORTED", "content-NOT_SUPPORTED");
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int jpaSaveWithNotSupported() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Board board = Board.createBoard("jpa-title-test-NOT_SUPPORTED", "jpa-content-test-NOT_SUPPORTED");
        boardRepository.save(board);
        throw new IllegalStateException("this method throw exception");
    }
}
