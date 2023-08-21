package shop.mtcoding.blogv2.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO saveDTO, Integer userId) { // 데이터 받기
        replyService.댓글쓰기(saveDTO, 1); // 핵심로직 호출
        return "redirect:/board/" + saveDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable Integer id, Integer boardId) {
        // 인증체크 필요
        replyService.댓글삭제(id);
        return "redirect:/board/" + boardId;

    }
}
