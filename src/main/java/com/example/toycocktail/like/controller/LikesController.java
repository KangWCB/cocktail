package com.example.toycocktail.like.controller;

import com.example.toycocktail.common.dto.Response;
import com.example.toycocktail.like.dto.LikesCocktailResponse;
import com.example.toycocktail.like.service.LikeService;
import com.example.toycocktail.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikeService likeService;

    @PostMapping
    public Long likeCocktail(@RequestParam Long cocktailId, @CurrentUser Member member){
        Long likeId = likeService.likeCocktail(member, cocktailId);
        return likeId;
    }

    @GetMapping("/list")
    public Response getLikeCocktail(@CurrentUser Member member){
        List<LikesCocktailResponse> likesCocktailList = likeService.getLikesCocktailList(member);
        CountAndData result = new CountAndData(likesCocktailList.size(), likesCocktailList);
        return new Response<>(200,result);
    }

    @AllArgsConstructor
    private class CountAndData{
        private int count;
        private List data;
    }
}
