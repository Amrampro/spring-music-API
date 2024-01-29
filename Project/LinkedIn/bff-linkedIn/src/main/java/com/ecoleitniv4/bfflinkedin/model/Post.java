package com.ecoleitniv4.bfflinkedin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private Long userId; // ID of the user who created the post
    private String title;
    private String content;
    private LocalDateTime creationDate;

    // Default constructor
//    public Post() {
//    }

    // Constructor with fields
//    public Post(Long id, Long userId, String title, String content, LocalDateTime creationDate) {
//        this.id = id;
//        this.userId = userId;
//        this.title = title;
//        this.content = content;
//        this.creationDate = creationDate;
//    }

    // toString method
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
