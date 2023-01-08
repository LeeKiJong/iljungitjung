## 목차
[1. 서비스 소개](#1-서비스-소개)  
[2. 주요 기능](#2-주요-기능)  
[3. 기술 스택](#3-기술-스택)  
[4. 시스템 구성도](#4-시스템-구성도)  
[5. 실행 방법](#5-실행-방법)  
[6. 팀원 소개](#6-팀원-소개)  

# 1. 서비스 소개
**일정있정**은 개인과 소상공인을 위한 웹 스케줄링 서비스 입니다.  
네이버, 카카오 미용실과 같은 예약 시스템은 등록된 업체만 이용 가능하며 예약이나 약속을 잡을 때 전화나 SNS를 이용한 커뮤니케이션에 의존적이라는 문제점에 착안하여 쉽고 편하게 이용할 수 있는 웹 서비스를 개발하였습니다.  
‘매우 가벼운’ 일정 예약 툴을 찾고 있는 프로젝트 팀이나 일정 조율, 관리가 중요한 직업이지만 이를 위한 복잡한 절차를 거치기 힘든 소상공인들이 사용할 수 있습니다.

📚 [노션 바로가기](https://fierce-alpaca-126.notion.site/3329ecd7f4c54f13979f2edce36bd810)
  

### 📆 프로젝트 기간
2022년 10월 11일 ~ 11월 21일 (약 6주)  
프론트 3명, 백엔드 3명

# 2. 주요 기능
### ✅ 일정 예약 기능
고객은 예약 시간, 요청 사항으로 일정을 예약할 수 있습니다.  
고객이 업체의 일정을 확인한 후 빈 시간대에 요청 사항을 입력하면 업체 측에서는 확인 후 수락 및 거절 시 고객에게 메시지를 전송할 수 있습니다.

### ✅ 일정 알림 기능
매일 오전 10시 오늘 일정 알림을 고객에게 전송합니다.  
고객이 예약을 신청하거나 취소하면 업체에게 알림 문자를 전송해주고, 업체가 예약을 승인하거나 취소하면 고객에게 알림 문자를 전송해줍니다.

### ✅ 일정 조회 기능
고객은 업체의 일정, 카테고리를 확인할 수 있으며 빈 시간대에 일정 예약을 요청할 수 있습니다.

### ✅ 일정 관리 기능
업체는 카테고리 등록, 수정, 삭제를 통해 카테고리를 관리할 수 있습니다.
업체는 일정 차단 기능을 통해 해당 시간대에 예약이 불가하도록 차단할 수 있습니다.

# 3. 기술 스택
## backend
### local
|intellij|jdk 11|spring boot|docker|
|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/52438368/195626893-c78d6450-04fc-4d25-b1f7-de3777ce55de.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195626943-e172849b-a81d-4584-9e94-a4c8275bae57.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195626992-13753380-523f-4f3e-8962-2da9c67f7ef7.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627001-da558b56-28a4-424b-8dea-199b0e740c5e.png" height="70px" />|
### server (ec2)
|docker|jenkins|nginx|mariaDB|Redis|
|:---:|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/52438368/195627001-da558b56-28a4-424b-8dea-199b0e740c5e.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627021-b948587f-f8ce-47ba-ba55-242a5d12fdd9.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627044-56c12e5f-443d-4d4a-93ee-da201fbb97b4.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627063-c5152bf1-8aa5-4c75-8a13-eff91601d75c.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/203672705-bb764461-f54d-4449-a583-d5f003b2f79c.png" height="70px" />

## frontend
|HTML|CSS|JavaScript|TypeScript
|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/52438368/195627115-bc672bbb-001c-4e15-b603-8fd01fac12c1.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627130-8209c379-e1c2-4cc9-89fe-14d364823d9c.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627148-c8f95876-db67-479e-972b-6caaa74d1265.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/203672593-d8bea7c3-1ed3-4a8c-8108-61463483a24a.png" height="70px" />|

|React|Redux|Sass|Mui|
|:---:|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/52438368/195627189-0cb77e7a-fb83-4ad2-ab3b-c5d559ed2cc6.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627207-614f859d-fcad-4683-a43e-fa5498b07085.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627227-55eff34c-f049-4b9b-9700-a326d10c2572.png" height="70px" />|<img src="https://user-images.githubusercontent.com/52438368/195627241-b4224add-7dc9-4601-9e6a-a56df61df90e.png" height="70px" />

# 4. 시스템 구성도

![image](https://user-images.githubusercontent.com/52438368/211197586-16085703-de69-4b83-8cc3-dae3ed1f5a7d.png)

# 5. 실행 방법
## backend
### CI/CD  
gitlab(back branch) push or merge -> jenkins webhook -> auto build  

### SSH
```
ssh -i K7D106T.pem ubuntu@k7d106.p.ssafy.io
```

### docker logs
![image](https://user-images.githubusercontent.com/52438368/211197392-8860b850-19ee-4bcf-99d8-c04c73efc29c.png)
```
docker logs --tail 300 iljungitjung_server_green
```

## frontend
1. 레포지토리를 clone 받는다.
```bash
git clone https://lab.ssafy.com/s07-final/S07P31D106.git
```
2. `front` 폴더에서 `package.json`에 정의된 모듈을 설치한다.
```shell
npm install
```
3. 프로그램을 실행시킨다.
```shell
npm start
```


# 6. 팀원 소개
|조인후|김다은|김주영|이기종|이상민|이재영|
|:---:|:---:|:---:|:---:|:---:|:---:|
|front(팀장)|back|front|back|back|front|
