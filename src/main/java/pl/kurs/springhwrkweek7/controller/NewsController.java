package pl.kurs.springhwrkweek7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kurs.springhwrkweek7.dao.NewsDaoImpl;
import pl.kurs.springhwrkweek7.model.NewsDTO;

@Controller
public class NewsController {

    private NewsDaoImpl newsDaoImpl;

    @Autowired
    public NewsController(NewsDaoImpl newsDaoImpl) {
        this.newsDaoImpl = newsDaoImpl;
    }

    @GetMapping("/reload-news-list")
    public String Reload() {
        newsDaoImpl.getArticleFromApi();

        return "redirect:/news-list";
    }

    @GetMapping("/news-list")
    public String getAllCars(Model model) {
        model.addAttribute("news", newsDaoImpl.gelAllArticle());

        return "newsList";
    }

    @GetMapping("/news-update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        NewsDTO news = newsDaoImpl.getById(id);
        model.addAttribute("news", news);

        return "updateNews";
    }


    @PostMapping("/news-update")
    public String saveModNews(@ModelAttribute NewsDTO modNews) {
        newsDaoImpl.updateArticle(modNews);

        return "redirect:/news-list";
    }


}
