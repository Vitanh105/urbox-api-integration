package com.vanh.urboxapiintegration.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SignatureUtil {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public SignatureUtil(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public String generateSignature(Map<String, Object> data, String privateKeyPath) {
        //Theo yêu cầu bảo mật
        //Sử dụng ksort (sắp xếp theo key với dạng alphabet)
        TreeMap<String, Object> sortedData = new TreeMap<>(data);

        //Sử dụng json_encode:
        String jsonData = objectMapper.writeValueAsString(sortedData);

        //Tạo chữ ký bằng SHA256withRSA
        PrivateKey privateKey = loadPrivateKey(privateKeyPath);;
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(jsonData.getBytes(StandardCharsets.UTF_8));
        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    @SneakyThrows
    private PrivateKey loadPrivateKey(String privateKeyPath) {
        Resource resource = resourceLoader.getResource(privateKeyPath);
        String privateKeyPEM = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return keyFactory.generatePrivate(keySpec);
    }
}
