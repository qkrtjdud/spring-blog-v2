package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @Repository 안쓰고 JpaRepository를 인터페이스 받으면 
 * save(), findById(), findAll(), count(), deleteById() 
 * 기본적인 함수(CRUD)를 만들어주지 않아도 됨! JpaRepository에 다있음! 
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
