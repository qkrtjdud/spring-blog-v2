package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepository.class)
@DataJpaTest // JpaRepository 띄어준다
public class UserQueryRepositoryTest {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private EntityManager em;

    // persist(영속화)
    @Test
    public void save_test() {
        User user = User.builder()
                .username("love")
                .password("1234")
                .email("love@nate.com")
                .build();
        userQueryRepository.save(user);
    }

    // 1차캐시
    @Test
    public void findById_test() {
        // pc는 비어있다
        System.out.println("1. pc는 비어있다");
        userQueryRepository.findById(1);
        // pc는 user 1의 객체가 영속화 되어 있다
        System.out.println("2. pc의 user 1은 영속화 되어 있다");
        em.clear();
        userQueryRepository.findById(1);
        System.out.println("??");
    }

    @Test
    public void update_test() {
        /*
         * JPA update 알고리즘
         * 1. 업데이트 할 객체를 영속화
         * 2. 객체 상태 변경
         * 3. em.flush() or @Transactional 정상종료
         */
        User user = userQueryRepository.findById(1);
        user.setEmail("ssarmango@nate.com");
        em.flush();
    }
}
