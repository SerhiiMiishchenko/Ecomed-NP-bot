package com.github.mishchuk7.ecomednpbot.v1;

import com.github.mishchuk7.ecomednpbot.v1.handler.UserRequestHandler;
import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class Dispatcher {

    private final List<UserRequestHandler> handlers;


    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers.stream()
                .sorted(Comparator.comparing(UserRequestHandler::isGlobal).reversed())
                .toList();
    }

    public boolean dispatch(UserRequest userRequest) {
        for (UserRequestHandler request : handlers) {
            if (request.isApplicable(userRequest)) {
                request.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}
