package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;  // articleService 객체 주입

    @Test   //해당 메서드가 테스트 코드임을 선언
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        //Arrays.asList() 메서드는 입려된 배열 또는 2개 이상의 동일한 타입 데이터를 정적 리스트로 만들어 반환한다.
        //정적 리스트는 고정 크기이므로 add()나 remove() 메서드를 사용할 수 없다.
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        //2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() {
        //1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_fail() {
        //1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }
}