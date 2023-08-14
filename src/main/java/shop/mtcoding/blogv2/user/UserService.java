package shop.mtcoding.blogv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.user.UserRequest.LoginDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .email(joinDTO.getEmail())
                .build();
        userRepository.save(user);
    }

    public void 로그인(LoginDTO loginDTO) {
        User user = User.builder()
                .username(loginDTO.getUsername())
                .password(loginDTO.getPassword())
                .build();

        System.out.println("로그인" + user.getUsername());
        System.out.println("로그인" + user.getPassword());
        // System.out.println("로그인" + loginDTO.getUsername());
        // System.out.println("로그인" + loginDTO.getPassword());
    }
}
