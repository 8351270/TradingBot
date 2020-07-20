package com.tradingbot.service;

import com.google.gson.Gson;
import com.tradingbot.entity.authentication.request.AuthenticationRequest;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl {
    //String s = {"method":9,"params":{"data":{"method":"loginSessionByApiToken","params":{"apiKey":"UGrJHxrbDY1DfF5YhKz6","time":{"seconds":1584974565,"nanos":539258000},"hmacDigest":"d332bea6030fec9ee6c3ca86628a12fc19d6687a1ad07002bc4038b3ed66c7e"}}},"id":15};

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBookServiceImpl.class);

    @Autowired
    Environment environment;

//    @PostConstruct
    public void init()  {
        AuthenticationRequest auth = new AuthenticationRequest();
        auth.setId(15);
        auth.setMethod(9);
        auth.getParams().getData().setMethod("loginSessionByApiToken");
        auth.getParams().getData().getParams().setApiKey(this.environment.getProperty("api.id"));
        String digest = "";
        auth.getParams().getData().getParams().setHmacDigest(digest);
        long unixTime = System.currentTimeMillis()/1000L;
        long nanos = (long) (Math.random() * (999999999));
        auth.getParams().getData().getParams().getTime().setSeconds(unixTime);
        auth.getParams().getData().getParams().getTime().setNanos(nanos);


        String hexString = this.environment.getProperty("api.secret");
        try {

            //TODO Complete
            assert hexString != null;
            byte[] bytes = Hex.decodeHex(hexString);
            String data = "key=" + this.environment.getProperty("api.id") + ";" + "time=" + unixTime;
            byte[] bytes2 = data.getBytes();

//            String s = "some text here";
//            byte[] b = s.getBytes(StandardCharsets.UTF_8);
//            byte[] b = {(byte) 99, (byte)97, (byte)116};
//            String s = new String(b, StandardCharsets.US_ASCII);


//            At the input of the HMAC:
//• decode.hex (api-secret) - as a secret value;
//• {‘key =’ || api-key || ‘;’ || ‘Time =’ || str (timestamp)}. encode (utf-8) - as the data;
//• SHA-256 - as a hash function.

        }catch (DecoderException ex){
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex);

        }

        Gson g = new Gson();
        String str = g.toJson(auth);
        System.out.println(str);


    }
    public AuthenticationServiceImpl(){




    }
}
