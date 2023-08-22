package shop.mtcoding.blogv2._core.error.ex;

public class MyApiException extends RuntimeException { // ajax 로 터지는걸 이쪽으로 !
    public MyApiException(String message) {
        super(message);
    }

}
