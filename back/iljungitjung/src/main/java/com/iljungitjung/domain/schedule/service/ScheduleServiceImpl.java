package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.dto.CategoryViewResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.dto.schedule.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    @Override
    public ScheduleViewResponseDto scheduleView(String nickname, String startDate, String endDate, HttpSession httpSession) {

        User userFrom = userService.findUserBySessionId(httpSession);
        User userTo = userRepository.findUserByNickname(nickname).orElseThrow(()->{
            throw new NoExistUserException();
        });

        ScheduleViewResponseDto responseDtos;
        boolean dateCheck = true;

        Date startDateFormat = new Date();
        Date endDateFormat = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        if(startDate==null || endDate == null) dateCheck=false;
        else{
            try{
                startDateFormat = formatter.parse(startDate+"0000");
                endDateFormat = formatter.parse(endDate+"2359");
            }catch (Exception e){
                throw new DateFormatErrorException();
            }
        }



        List<Schedule> scheduleList = scheduleRepository.findByUserTo_IdIs(userTo.getId());

        List<ScheduleViewDto> requestList = new ArrayList<>();
        List<ScheduleViewDto> acceptList = new ArrayList<>();
        List<ScheduleBlockDto> blockList = new ArrayList<>();
        List<ScheduleCancelDto> cancelList = new ArrayList<>();

        if(userFrom.getId()==userTo.getId()){
            for(Schedule schedule : scheduleList){
                if(dateCheck){
                    if(schedule.getStartDate().before(startDateFormat) || schedule.getEndDate().before(startDateFormat) || schedule.getStartDate().after(endDateFormat) || schedule.getEndDate().after(endDateFormat)) continue;
                }
                if (schedule.getType().equals(Type.ACCEPT)) {
                    acceptList.add(new ScheduleViewDto(schedule));
                } else if (schedule.getType().equals(Type.BLOCK)) {
                    blockList.add(new ScheduleBlockDto(schedule));
                }
            }
        }
        else{
            for(Schedule schedule : scheduleList){
                if(dateCheck){
                    if(schedule.getStartDate().before(startDateFormat) || schedule.getEndDate().before(startDateFormat) || schedule.getStartDate().after(endDateFormat) || schedule.getEndDate().after(endDateFormat)) continue;
                }
                if(schedule.getType().equals(Type.REQUEST)){
                    requestList.add(new ScheduleViewDto(schedule));
                }else if(schedule.getType().equals(Type.ACCEPT)){
                    acceptList.add(new ScheduleViewDto(schedule));
                }else if(schedule.getType().equals(Type.BLOCK)){
                    blockList.add(new ScheduleBlockDto(schedule));
                }else{
                    cancelList.add(new ScheduleCancelDto(schedule));
                }
            }
        }
        List<CategoryViewResponseDto> categoryList = new ArrayList<>();
        for(Category category :userTo.getCategoryList()){
            categoryList.add(new CategoryViewResponseDto(category));
        }
        responseDtos = new ScheduleViewResponseDto(categoryList, requestList, acceptList, blockList, cancelList);


        return responseDtos;
    }

    @Override
    public ScheduleViewDetailResponseDto scheduleViewDetail(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });
        ScheduleViewDetailResponseDto responseDto = new ScheduleViewDetailResponseDto(schedule);
        return responseDto;
    }

}
