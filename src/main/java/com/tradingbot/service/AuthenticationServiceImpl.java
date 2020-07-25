package com.tradingbot.service;

import com.google.gson.Gson;
import com.tradingbot.entity.authentication.request.AuthenticationRequest;
import com.tradingbot.entity.authentication.response.AuthenticationResponse;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AuthenticationServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    final Environment environment;

    public AuthenticationServiceImpl(Environment environment) {
        this.environment = environment;
    }

    public WebSocketMessage<String> authentication() {

        AuthenticationRequest auth = new AuthenticationRequest();
        auth.setId(101);
        auth.setMethod(9);
        auth.getParams().getData().setMethod("loginSessionByApiToken");
        auth.getParams().getData().getParams().setApiKey(this.environment.getProperty("api.id"));
        String digest = "";
        auth.getParams().getData().getParams().setHmacDigest(digest);
        long unixTime = System.currentTimeMillis() / 1000L;
        long nanos = (long) (Math.random() * (999999999));
        auth.getParams().getData().getParams().getTime().setSeconds(unixTime);
        auth.getParams().getData().getParams().getTime().setNanos(nanos);
        String hexString = this.environment.getProperty("api.secret");

        try {
            assert hexString != null;
            byte[] bytes = Hex.decodeHex(hexString);
            String data = "key=" + this.environment.getProperty("api.id") + ";" + "time=" + unixTime;
            byte[] bytes2 = data.getBytes(StandardCharsets.UTF_8);
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacSha256 = null;
            hmacSha256 = mac.doFinal(bytes2);
            auth.getParams().getData().getParams().setHmacDigest(Hex.encodeHexString(hmacSha256));

        } catch (DecoderException | NoSuchAlgorithmException | InvalidKeyException ex) {
            LOGGER.error("Exception while creating hmac " + ex.getMessage());
            throw new RuntimeException(ex);
        }

        Gson g = new Gson();
        String str = g.toJson(auth);
        return new TextMessage(str);
    }

    public void addAuthentication(JSONObject jsonResponse) {
        Gson g = new Gson();
        AuthenticationResponse ar = g.fromJson(jsonResponse.toString(), AuthenticationResponse.class);
        List<String> permissions = ar.getResult().getValue().getPermissions();
        long sessionTime = ar.getResult().getValue().getExpirationTime().getSeconds() - ar.getResult().getValue().getStartTime().getSeconds();
        sessionTime = sessionTime / 3600;
        LOGGER.info("User with id: " + ar.getResult().getValue().getUserId() + " successfully authenticated");
        LOGGER.info("session expires in: " + sessionTime + " hours");
        LOGGER.info("User permissions are:");
        for (String permission : permissions) {
            LOGGER.info("   " + permission);
        }
    }
}