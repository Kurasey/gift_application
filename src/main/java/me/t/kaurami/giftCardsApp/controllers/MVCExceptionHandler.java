package me.t.kaurami.giftCardsApp.controllers;

import me.t.kaurami.giftCardsApp.services.bookingservice.AlreadyBookedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@ControllerAdvice
public class MVCExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException e, Model model, ServletWebRequest webRequest) {
        model.addAttribute("exceptionMessage", "Доступ запрещен");
        log(e, "User %s tried to access a protected resource %s with http method %s".formatted(
                webRequest.getUserPrincipal().getName(),
                webRequest.getRequest().getRequestURI(),
                webRequest.getHttpMethod()));
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("exceptionMessage", "Страница не найдена");
        log(e);
        return "error";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, Model model, ServletWebRequest request) {
        model.addAttribute("exceptionMessage", "Страница не найдена");
        log(e, "Method %s is not supported for %s.".formatted(request.getHttpMethod(), request.getRequest().getRequestURI()));
        return "error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e, Model model) {
        model.addAttribute("exceptionMessage", "Элемент не найден");
        log(e);
        return "error";
    }

    @ExceptionHandler(AlreadyBookedException.class)
    public String handleAlreadyBookedException(AlreadyBookedException e, Model model) {
        model.addAttribute("exceptionMessage", "Подарок уже забронирован");
        log(e);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String generalHandler(Exception e, Model model) {
        model.addAttribute("exceptionMessage", "Что-то пошло не так.\n Попробуйте еще раз или обратитесь в техподдержку.");
        log(e);
        return "error";
    }

    private void log(Exception exception, String ... additionalMessages){
        Optional<String> message = Arrays.stream(additionalMessages).reduce((s, s2) -> (s + "; " + s2));
        logger.error("Exception from view layer. Class: %s. Message: %s; %s".formatted(
                exception.getClass(),
                exception.getMessage(),
                message.isPresent()? message.get() : ""));
        exception.printStackTrace();
    }

}
