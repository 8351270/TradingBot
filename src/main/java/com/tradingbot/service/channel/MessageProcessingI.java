package com.tradingbot.service.channel;

import org.json.JSONObject;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

public interface MessageProcessingI  {

  List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse);

}
