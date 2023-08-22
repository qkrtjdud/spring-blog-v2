package shop.mtcoding.blogv2._core.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUtil<T> { // json 응답 할려고 만듦
    private boolean sucuess; // true , false
    private T data; // 메시지-댓글쓰기 성공, 댓글쓰기 실패

    public ApiUtil(boolean sucuess, T data) {
        this.sucuess = sucuess;
        this.data = data;
    }
}
