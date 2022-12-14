package com.iljungitjung.domain.notification.service;


import com.iljungitjung.domain.notification.dto.*;
import com.iljungitjung.domain.notification.entity.Phone;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.notification.exception.phone.ExpireRandomNumException;
import com.iljungitjung.domain.notification.repository.PhoneRepository;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.TemporaryUser;
import com.iljungitjung.global.login.exception.ExpireTemporaryUserException;
import com.iljungitjung.global.login.repository.TemporaryUserRepository;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;
    private final String AUTH_PHONE = "인증 번호를 입력해 주세요\n[%s]";
    private final String PRESENTED_NUMBER = "이미 존재하는 전화번호 입니다.";
    private final UserRepository userRepository;
    private final TemporaryUserRepository temporaryUserRepository;
    private final PhoneRepository phoneRepository;
    private final NotificationCorrespondence notificationCorrespondence;

    @Override
    public String requestRandomNumber(String phone, HttpSession httpSession) {
        if (checkDuplicatePhone(phone)) {
            return PRESENTED_NUMBER;
        }
        String randomNum = makeRandomNumber();
        NotificationMessage message = new NotificationMessage(phone, makeAuthenticateContent(randomNum));
        sendMessage(NotificationRequestDto.createFromMessages(makeMessageList(message)));
        savePhoneRandomNumber(new PhoneConfirmRequestDto(phone, randomNum), httpSession);
        return randomNum;
    }
    private List<NotificationMessage> makeMessageList(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private void sendMessage(NotificationRequestDto requestDto) {
        HttpEntity<NotificationMessageRequestDto> body = makeBody(requestDto);
        checkStatusEqualsAccpeted(notificationCorrespondence.sendNcloud(body));
    }

    public NotificationResponseDto checkStatusEqualsAccpeted(NotificationResponseDto response){
        if(response.getStatusCode().equals(statusAccepted())) return response;
        throw new FailSendMessageException();
    }

    private String statusAccepted() {
        return Integer.toString(HttpStatus.ACCEPTED.value());
    }
    private HttpEntity<NotificationMessageRequestDto> makeBody(NotificationRequestDto requestDto) {
        HttpHeaders headers = notificationCorrespondence.makeHeaders();
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        return new HttpEntity<>(jsonBody, headers);
    }

    private String makeRandomNumber() {
        UUID uuid = UUID.randomUUID();
        return parseToShortUUID(uuid.toString());
    }

    private String parseToShortUUID(String uuid) {
        return uuid.substring(0, 6);
    }

    private String makeAuthenticateContent(String number) {
        return String.format(AUTH_PHONE, number);
    }

    private boolean checkDuplicatePhone(String phone) {
        return userRepository.existsUserByPhonenum(phone);
    }

    @Transactional
    private void savePhoneRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession session) {
        String id = session.getId();
        deleteExistPhone(id);
        Phone phone = Phone.builder()
                .phonenum(requestDto.getPhonenum())
                .id(id)
                .randomNumber(requestDto.getRandomNumber()).build();
        phoneRepository.save(phone);
    }

    private void deleteExistPhone(String id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
        }
    }

    @Override
    public void confirmRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession session) {
        Phone phone = phoneRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireRandomNumException();
        });
        phone.checkCorrect(requestDto);
        updatePhonenumTemporaryUser(requestDto, session);
    }

    @Transactional
    private void updatePhonenumTemporaryUser(PhoneConfirmRequestDto requestDto, HttpSession session) {
        TemporaryUser temporaryUser = temporaryUserRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireTemporaryUserException();
        });
        temporaryUser.setPhonenum(requestDto.getPhonenum());
        temporaryUserRepository.save(temporaryUser);
    }


}
