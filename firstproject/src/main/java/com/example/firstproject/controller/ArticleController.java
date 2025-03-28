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

import java.util.ArrayList;

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
        log.info(form.toString());
//        System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "";

    }

    //get매핑 post매핑
    //get과 post 비교
    //get은 데이터를 가져오는 용도
    //전송방식은 URL에 쿼리 문자열 형식을 전송됨
    //URL에 포함되어 데이터 노출, 보안 취약
    //최대 데이터 길이가 제한적임

    //post는 데이터를 서버로 전송하는 목적, 아까 사요했던 form 데이터 전송 등
    //전송하는 데이턱 URL에 포함되어 전송되는 방식이 아닌 body에 포함되어 전송됨]
    //데이터 제한이 get에 비해 훨씬 큰 데이터 가능 (json 등)

    //HTTP 상태코드
    //200 ok 요청 처리 정상
    //40x 잘못된 요청
    //50x 서버 오류
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+ id);
        // 1. id를 조회해 데이터 가져오기

//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        // name 이라는 이름으로 value 객체 추가
//        model.addAttribute(String name, Objects value);
        model.addAttribute("article",articleEntity);

        // 3. 뷰 페이지 반환하기

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //인터페이스 iter는 인터페이스 List랑 같지 않다.

        //2. 모델에 데이터 등록
        model.addAttribute("articlelist",articleEntityList);
        //3. 뷰 페이지 설정

        return "articles/index";
    }




}
