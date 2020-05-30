package pl.kurs.springhwrkweek7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.kurs.springhwrkweek7.model.NewsDTO;
import pl.kurs.springhwrkweek7.model.news.Article;
import pl.kurs.springhwrkweek7.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl implements NewsDao {

    private NewsService newsService;
    private JdbcTemplate jdbcTemplate;
    private List<NewsDTO> articleList;


    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate, NewsService newsService) {
        this.jdbcTemplate = jdbcTemplate;
        this.newsService = newsService;
        this.articleList = new ArrayList<>();
    }

    @Override
    public List<NewsDTO> gelAllArticle() {
        articleList.clear();
        String sql = "SELECT * FROM news";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> articleList.add(new NewsDTO(
                Long.parseLong(String.valueOf(element.get("news_id"))),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("news_date"))
        )));

        return articleList;
    }

    public NewsDTO getById(long id) {
        String sql = "SELECT * FROM news where news_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (element, rowNum) ->
                new NewsDTO(element.getLong("news_id"),
                        element.getString("title"),
                        element.getString("description"),
                        element.getString("author"),
                        element.getString("news_date")));

    }

    @Override
    public void getArticleFromApi() {
        jdbcTemplate.execute("TRUNCATE TABLE news");
        List<Article> getNews = newsService.getNewsFromApi().getArticles();
        for (Article getNew : getNews) {
            String sql = "INSERT INTO news (title,description,author,news_date) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql,
                    getNew.getTitle(),
                    getNew.getDescription(),
                    getNew.getAuthor(),
                    getNew.getPublishedAt());

        }
    }

    @Override
    public void updateArticle(NewsDTO newArticle) {
        String sql = "UPDATE news SET title=?, description =?, author =? WHERE news_id = ?";
        jdbcTemplate.update(sql,
                newArticle.getTitle(),
                newArticle.getDescription(),
                newArticle.getAuthor(),
                newArticle.getNewsId());
    }


}
