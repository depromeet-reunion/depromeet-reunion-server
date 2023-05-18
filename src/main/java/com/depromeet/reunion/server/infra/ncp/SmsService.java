package com.depromeet.reunion.server.infra.ncp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Naver Cloud Platform SMS Service
 * Using Only SMS, Not MMS
 **/
@Slf4j
@Service
public class SmsService {
    @Value("${ncp.sms.uri}")
    private String uri;

    @Value("${ncp.sms.url}")
    private String url;

    @Value("${ncp.sms.access-key}")
    private String accessKey;

    @Value("${ncp.sms.secret-key}")
    private String secretKey;

    @Value("${ncp.sms.from}")
    private String from;

    public void sendMessage(String phoneNumber, String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String content = mapper.writeValueAsString(new MsgTemplate(from, message, phoneNumber));

            final String timestamp = String.valueOf(System.currentTimeMillis());
            var signature = makeSignature(url, timestamp, accessKey, secretKey);

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create(content, okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_VALUE));

            Request request = new Request.Builder()
                    .url(uri)
                    .method("POST", body)
                    .addHeader("x-ncp-iam-access-key", accessKey)
                    .addHeader("x-ncp-apigw-timestamp", timestamp)
                    .addHeader("x-ncp-apigw-signature-v2", signature)
                    .addHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .build();
            Response response = client.newCall(request).execute();

        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            log.error("Failed to send a SMS message: {}", e.getMessage());
        }

    }


    private String makeSignature(String url, String timestamp, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String SIGNING_ALGORITHM = "HmacSHA256";
        String space = " ";
        String newLine = "\n";
        String method = "POST";

        String message = method + space + url + newLine + timestamp + newLine + accessKey;


        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SIGNING_ALGORITHM);
        Mac mac = Mac.getInstance(SIGNING_ALGORITHM);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(rawHmac);
    }

}
