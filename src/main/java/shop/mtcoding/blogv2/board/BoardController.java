package shop.mtcoding.blogv2.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardService.상세보기(id);
        request.setAttribute("board", board);
        return "/board/updateForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO updateDTO) {
        // where데이터, body, session값(권한처리할 때)
        boardService.글수정(id, updateDTO);
        return "redirect:/board/" + id;

    }

    @PostMapping("/board/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id) {
        // 인증체크 필요
        boardService.글삭제(id);
        return Script.href("/");

    }

    @GetMapping("/test/board/{id}")
    public @ResponseBody Board testdetail(@PathVariable Integer id) { // request를 써도 되고 모델로 써도 됨!
        Board board = boardRepository.mFindByIdJoinRepliesInUser(id).get();
        return board;

    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, Model model) { // request를 써도 되고 모델로 써도 됨!
        Board board = boardService.상세보기(id);
        model.addAttribute("board", board); // request에 담는 것과 동일
        return "board/detail";

    }

    // localhost:8080?page=1&keyword=바나나
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        request.setAttribute("boardPG", boardPG);
        request.setAttribute("prevPage", boardPG.getNumber() - 1);
        request.setAttribute("nextPage", boardPG.getNumber() + 1);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기 (V)
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사 (:TODO)
    // 4. 핵심로직 호출(서비스)
    // 5. view or data 응답
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { // 데이터 받기
        boardService.글쓰기(saveDTO, 1); // 핵심로직 호출
        return "redirect:/";
    }
}