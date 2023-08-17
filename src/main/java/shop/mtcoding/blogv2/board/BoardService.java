package shop.mtcoding.blogv2.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blogv2.user.User;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, int sessionUserId) {
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(sessionUserId).build())
                .build();
        boardRepository.save(board);
    }

    public Page<Board> 게시글목록보기(Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    public Board 상세보기(Integer id) {
        // board만 가져오면 됨!
        return boardRepository.findById(id).get();
    }

    @Transactional
    public void 글삭제(Integer id) {
        boardRepository.deleteById(id);
    }

    public Board 글수정(Integer id, BoardRequest.UpdateDTO updateDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(updateDTO.getTitle());
            board.setContent(updateDTO.getContent());
            return boardRepository.save(board);
        } else {
            // 해당 id에 해당하는 글이 존재하지 않을 때 처리
            // 예: 예외 던지기, 새로운 글 생성 등
            return null; // 또는 적절한 처리를 위한 값 반환
        }
    }// flush(더티체킹)

}
