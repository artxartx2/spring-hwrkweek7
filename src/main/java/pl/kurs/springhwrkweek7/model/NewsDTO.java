package pl.kurs.springhwrkweek7.model;

public class NewsDTO {


    private long newsId;
    private String title;
    private String description;
    private String author;
    private String newsDate;


    public NewsDTO(long newsId, String title, String description, String author, String newsDate) {
        this.newsId = newsId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.newsDate = newsDate;
    }

    public NewsDTO() {
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }


    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
