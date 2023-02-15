package com.samsamoo.zzalu.global.advice;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class ContreeCommonError implements ErrorPageRegistrar {
//    private final String ERROR_PATH = "/error";
//
//    @GetMapping(ERROR_PATH)
//    public String redirectRoot(){
//        return "/static/index.html";
//    }
//
//    public String getErrorPath(){
//        return null;
//    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/static/index.html");
        registry.addErrorPages(e404);
    }
}
