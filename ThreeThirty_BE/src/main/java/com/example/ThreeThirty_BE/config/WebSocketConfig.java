package com.example.ThreeThirty_BE.config;

// 어떤 요청에 어떤 응답을 할지를 설정하는 파일이다.

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/*
@RequiredArgsConstructor의 경우 lombok를 이용한건데. 먼저 lombok가 뭔지 알아야한다.
lombok란 자바 프로그래밍에서 반복적이고 번거로운 작업을 간소화하기 위해 만들어진 라이브러리로 Getter, Setter, Equals 및
HashCode 메서드, 생성자등을 자동으로 생성해준다.

RequiredArgsConsturctor의 경우, 해당 클래스의 생성자를 자동으로 생성하여, final로 선언된
필드를 생성자의 매개변수에 포함한다.
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        // ws/chat 경로를 통해 들어오는 소켓 통신 요청에 대한 처리를 위한 Handler 추가
        // handler란 관련 프로토콜 요청 처리를 담당하는 녀석을 말한다.
        registry.addHandler( webSocketHandler, "ws/chat").setAllowedOrigins("*");
    }
}
