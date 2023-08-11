package shop.mtcoding.blogv2.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest /// 모든 Repository, EntityManager
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save_test() {
        // 비영속 객체
        Board board = Board.builder()
                .title("제목6")
                .content("내용6")
                .user(User.builder().id(1).build())
                .build();

        System.out.println("확인 " + board.getId()); // 영속화 전 - pk 없음
        // 영속 객체
        boardRepository.save(board); // 영속화 - insert 자동으로 실행됨, DB와 동기화 됨
        System.out.println("확인2 " + board.getId()); // 영속화 후 - pk 있음
    }

}
