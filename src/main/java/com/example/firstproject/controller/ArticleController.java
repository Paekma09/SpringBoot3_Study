package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());
        //1. DTO 를 엔티티로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());
        //2. 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //Optional<Article> articleEntity = articleRepository.findById(id);

        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleEntityAll = articleRepository.findAll();
        //ArrayList<Article> articleEntityAll = articleRepository.findAll();
        //List<Article> articleEntityAll = (List<Article>) articleRepository.findAll();
        //Iterable<Article> articleEntityAll = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityAll);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
