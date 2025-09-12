package com.mycompany.lambdaFunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import jdk.internal.org.jline.terminal.TerminalBuilder;

import java.util.HashMap;
import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> event, Context context) {
        Map<String, Object> response = new HashMap<>();
        String variable = System.getenv("my_variable");

        response.put("statusCode", 200);
        response.put("body", event);

        showMessage("Variavel de ambiente: " + variable);
        showMessage("Event: " + event);
        return response;
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

}
