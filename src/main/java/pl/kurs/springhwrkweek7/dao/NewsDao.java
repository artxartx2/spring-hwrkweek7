package pl.kurs.springhwrkweek7.dao;

import pl.kurs.springhwrkweek7.model.NewsDTO;

import java.util.List;

public interface NewsDao {

    List<NewsDTO> gelAllArticle();

    void getArticleFromApi();

    void updateArticle(NewsDTO newArticle);


}
