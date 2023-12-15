# 2023-2-SCS4031-01-minseoduseo

## About
### 1. 프로젝트명
홈파밍족을 위한 AI 기반 작물관리 서비스

### 2. 프로젝트 목표
본 프로젝트에서는 AI 기반의 작물 관리 서비스를 제공하여 초보자들도 식물을 효과적으로 관리할 수 있도록 하고자 합니다.

### 3. 요구분석
최근 고물가, 건강과 환경에 대한 관심 증가, MZ 세대의 자기계발과 취미에 대한 수요 증가 등으로 홈가드닝 시장이 급성장하고 있습니다. 그러나 식물을 처음 키워보는 사람의 경우 식물을 관리하고 키우는데 어려움을 겪고 있습니다. 이를 보조하기 위한 대부분의 식물관리 프로그램에서는 관상용 식물에 대해서만 식물일지, 식물등록 기능만 제공할 뿐, 인공지능을 활용한 서비스는 부족한 것으로 분석되었습니다.

## Guides
- License: MIT
- Execute:


## System Architecture
 <a href="https://ibb.co/YB5LXm0"><img src="https://i.ibb.co/F4QDX2H/arc1.png" alt="arc1" border="0"></a>

<a href="https://ibb.co/hfSCHfD"><img src="https://i.ibb.co/xHN3GHJ/arc2.png" alt="arc2" border="0"></a>

## 프론트엔드 개발 스택

| 역할                 | 종류                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Framework            | ![Next JS](https://camo.githubusercontent.com/3720f5674058f0893aa9671b0baf0b23343201b77014dc9a497c632a3cff15e8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f52454143542d3631444146422e7376673f267374796c653d666f722d7468652d6261646765266c6f676f3d5265616374266c6f676f436f6c6f723d7768697465) |
| Styling              | ![Styled Components](https://img.shields.io/badge/styled--components-DB7093?style=for-the-badge&logo=styled-components&logoColor=white) |
| Programming Language | ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)  |
| Package Manager      | ![Yarn](https://img.shields.io/badge/yarn-%232C8EBB.svg?style=for-the-badge&logo=yarn&logoColor=white) |
| Version Control      | ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)  |



## 로컬에서 실행하는 방법

### 웹에서 실행하기

웹 폴더로 이동

```
cd frontend
cd reactweb
```

관련 라이브러리 가져오기
```
yarn 
```

웹 실행

```
yarn start
```
---

## 백엔드 개발 스택
| 역할 | 종류 |
|---|---|
|Framework| <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/flask-000000?style=for-the-badge&logo=flask&logoColor=white">|
|Database| <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> |
|Programming Language| <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">|
|Package Manager| <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> |
|Version Control| <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> |
|Infra| <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> |


## AI 개발 스택 - 식물 잎 데이터를 기반으로 한 질병 진단 모델
### Repository Link
- [AI 개발](https://github.com/hongseoi/crops-plant-disease-classification)
- [Flask 기반 API 생성](https://github.com/hongseoi/pytorch-flask-api)

### Skills
| 역할 | 종류 |
|---|---|
| Language | Python |
| Framework | Pytorch |
| Version control | Git, GitHub | 

### Full Process

#### 1. 식물여부 이진분류 모델 생성
식물인지, 식물이 아닌지 판단하는 모델
- dataset: imageNet(mini)
- model: CNN
- output: binary class("plant", "non-plant")


#### 2. Image segemtation model 생성
1의 결과가 식물일 경우 맨앞의 잎만 남도록 배경을 제거하는 segmentation 수행
- dataset: 기존 식물 이미지 데이터
- model: U-Net
- output: segmented image


#### 3. fine-grained classification model
2의 결과물에 대해서 28개 라벨에 대해 진단하는 식물 질병 진단 모델
- dataset: segmentated 식물잎 이미지 데이터
- model: ResNet
- output: 28개 label


## Project Result
#### 👉 메인화면

- <b>로그인 이후 메인화면</b>
 : 질병진단, 식물도감, 식물일지, 챗봇, 내 식물 관리 버튼이 보여집니다.
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/home.png" width="300" height="500">  </p>

- <b>좌측 상단 메뉴 바 클릭시</b>
 : 좌측 상단 버튼을 누르면 앱에서 제공하는 기능들을 한 눈에 볼 수 있고 클릭 시 각 기능을 이용할 수 있습니다. 
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/menu.png" width="300" height="500">  </p>

#### 👉 각 기능들
- <b>식물 진단</b>
: 모바일에서 직접 사진을 찍어 해당 식물의 질병을 진단할 수 있습니다. 
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/disease1.png" width="290" height="635">  </p>
- <b>식물 도감</b>
: N가지 식물에 대해 이미지와 함께 이름, 특징 정보를 제공합니다
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/dictionary.png" width="290" height="635"> </p>
- <b>식물 일지</b>
: 달력 및 일기 기능을 통해 식물 관리 내역을 기록하고, 조회할 수 있습니다.
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/calendar.png" width="290" height="635">  </p>
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/diary.png" width="290" height="635">  </p>
- <b>챗봇</b>
: openai의 chatgpt를 기반으로 한 챗봇으로, 식물에 관해 질문하고 답변을 받을 수 있습니다.
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/chatbot1.png" width="290" height="635">  </p>
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/chatbot2.png" width="290" height="635">  </p>
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/chatbot3.png"width="290" height="635"> </p>
- <b>내 식물 관리</b>
: 작물종, 작물이름, 작물 태그 색상을 지정하여 사용자가 키우는 식물 정보를 등록할 수 있습니다. 
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/myplant.png" width="290" height="635">  </p>
<p align="center"><img src="https://raw.githubusercontent.com/CSID-DGU/2023-2-SCS4031-01-minseoduseo/main/doc/registry.png" width="290" height="635">  </p>




## Expected Outcomes
- **궁금한 작물에 대한 정보를 한 눈에 확인할 수 있습니다**
식물도감, 챗봇 등의 기능을 활용해 작물에 대한 정보를 앱 내에서 한번에 확인할 수 있습니다.


- **작물을 보다 전문적으로 키울 수 있습니다**
인공지능 기반 서비스를 이용하여 작물을 처음 키워보는 사람도 작물을 효과적으로 관리할 수 있습니다.

## References
- [AI - pytorch](https://github.com/avinassh/pytorch-flask-api)
  

## Team Members
| 이름 | 역할 |
|---|---|
| 강민지 | AI Developer |
| 문서연 | Frontend Developer |
| 이두용 | Backend Developer | 
| 홍서이 | AI Developer | 
