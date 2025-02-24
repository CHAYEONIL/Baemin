package com.example.baemin.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PhoneVerificationService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long VERIFICATION_CODE_TTL = 3; // 3분 유효기간

    public void sendVerificationCode(String phoneNumber) {
        String verificationCode = generateRandomCode();
        saveVerificationCode(phoneNumber, verificationCode);
        sendSMS(phoneNumber, verificationCode); // SMS 발송 로직 구현 필요
    }

    public void verifyCode(String phoneNumber, String code) {
        String savedCode = redisTemplate.opsForValue().get(getRedisKey(phoneNumber));
        if (savedCode == null) {
            throw new VerificationException("인증번호가 만료되었습니다.");
        }
        if (!savedCode.equals(code)) {
            throw new VerificationException("잘못된 인증번호입니다.");
        }
        redisTemplate.delete(getRedisKey(phoneNumber)); // 검증 완료 후 삭제
    }

    private String generateRandomCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    private void saveVerificationCode(String phoneNumber, String code) {
        redisTemplate.opsForValue().set(
                getRedisKey(phoneNumber),
                code,
                VERIFICATION_CODE_TTL,
                TimeUnit.MINUTES
        );
    }

    private String getRedisKey(String phoneNumber) {
        return "PHONE:VERIFY:" + phoneNumber;
    }

    private void sendSMS(String phoneNumber, String code) {
        // SMS 발송 로직 구현
        // 예: 외부 SMS 서비스 연동
    }
}