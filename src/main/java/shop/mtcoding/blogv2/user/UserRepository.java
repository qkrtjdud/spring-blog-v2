package shop.mtcoding.blogv2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    // excuteQuery
    // @Query(value = "select * from user_tb where id = :id", nativeQuery = true)
    // User mFindById(@Param("id") Integer id);

    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

}
