package com.sparta.springcore.validator;

import com.sparta.springcore.dto.ProductRequestDto;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
@Component
public class ProductValidator{

    public static void validateProductInput(ProductRequestDto requestDto, Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
        }

        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("저장할 수 있는 상품명이 없습니다.");
        }
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(requestDto.getImage())) {
            throw new IllegalArgumentException("상품 이미지 URL 포맷이 맞지 않습니다.");
        }

        if (!urlValidator.isValid(requestDto.getLink())) {
            throw new IllegalArgumentException("상품 최저가 페이지 URL 포맷이 맞지 않습니다.");
        }

        if (requestDto.getLprice() <= 0) {
            throw new IllegalArgumentException("상품 최저가가 0 이하입니다.");
        }
    }

}