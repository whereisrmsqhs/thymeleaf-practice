package thymeleaf.practice;

import lombok.Data;

@Data
public class ThumbnailInfo {

    private String title;
    private String author;
    private String status;

    public ThumbnailInfo(String title, String author, String status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }
}
