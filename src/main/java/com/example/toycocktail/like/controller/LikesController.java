package com.example.toycocktail.like.controller;

import com.example.toycocktail.common.dto.Response;
import com.example.toycocktail.like.dto.LikesCocktailResponse;
import com.example.toycocktail.like.service.LikeService;
import com.example.toycocktail.member.model.Member;
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
    public Long likeCocktail(@RequestParam Long cocktailId, @AuthenticationPrincipal Member member){
        Long likeId = likeService.likeCocktail(member, cocktailId);
        return likeId;
    }

    @GetMapping("/list")
    public Response getLikeCocktail(@AuthenticationPrincipal Member member){
        return new Response<>(200,likeService.getLikesCocktailList(member));
    }
}
