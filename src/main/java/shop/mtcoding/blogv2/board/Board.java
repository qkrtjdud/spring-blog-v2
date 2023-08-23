package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.reply.Reply;
import shop.mtcoding.blogv2.user.User;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "board_tb")
@Entity // ddl-auto가 create
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = true, length = 10000)
    private String content;

    /* @ManyToOne(단방향) 는 Eager 전략이 디폴트값 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 1+N

    /* @OneToMany(양방향) 는 Lazy 전략이 디폴트값 */
    @JsonIgnoreProperties({ "board" })
    @OneToMany(mappedBy = "board"/* FK아니라는 뜻 */, fetch = FetchType.LAZY)
    // @OneToMany(mappedBy = "board"/* FK아니라는 뜻 */, fetch = FetchType.LAZY, cascade
    // = CascadeType.ALL /* 보드 삭제시 그보드에 달린 댓글도 삭제*/)
    private List<Reply> replies = new ArrayList<>();

    @CreationTimestamp // 만들때 알아서 자동으로 시간을 생성
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

}