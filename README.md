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

## System Architecture


## 프론트엔드 개발 스택

| 역할                 | 종류                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Framework            | ![Next JS](https://camo.githubusercontent.com/3720f5674058f0893aa9671b0baf0b23343201b77014dc9a497c632a3cff15e8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f52454143542d3631444146422e7376673f267374796c653d666f722d7468652d6261646765266c6f676f3d5265616374266c6f676f436f6c6f723d7768697465)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| Styling              | ![Styled Components](https://img.shields.io/badge/styled--components-DB7093?style=for-the-badge&logo=styled-components&logoColor=white)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| State Management     | ![Recoil](https://img.shields.io/badge/Recoil-007af4.svg?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FscXVlXzEiIGRhdGEtbmFtZT0iQ2FscXVlIDEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmlld0JveD0iMCAwIDI1NS4yMSA2MjMuOTEiPjxkZWZzPjxzdHlsZT4uY2xzLTF7ZmlsbDp3aGl0ZX08L3N0eWxlPjwvZGVmcz48cGF0aCBjbGFzcz0iY2xzLTEiIGQ9Im03NC42MiAyNzcuNDYgMS4yNC0uMTMgMzQuNzgtMy4yOC01My40Ny01OC42NkE5Ni40NyA5Ni40NyAwIDAgMSAzMiAxNTAuM0gzYTEyNS4zIDEyNS4zIDAgMCAwIDMyLjggODQuNTdaTTE3Ny4xMyAzNDdsLTM2IDMuNCA1My4zMiA1OC41MUE5Ni40MSA5Ni40MSAwIDAgMSAyMTkuNjMgNDc0aDI4LjkyYTEyNS4yOCAxMjUuMjggMCAwIDAtMzIuNzYtODQuNTdaIi8+PHBhdGggY2xhc3M9ImNscy0xIiBkPSJNMjUzLjY5IDIzMS42OGMtNi4zMy0zMS4zLTMwLjg5LTU0LjA5LTYyLjU3LTU4LjA3bC02LjM1LS43OWE0OS42MSA0OS42MSAwIDAgMS00My4zNS00OS4xM3YtMjBhNTIuNzUgNTIuNzUgMCAxIDAtMjguOTEtLjM2djIwLjM4YTc4LjU2IDc4LjU2IDAgMCAwIDY4LjY1IDc3LjgybDYuMzYuOGMyMy4yNCAyLjkyIDM0Ljc4IDIwIDM3LjgzIDM1LjFzLS45MyAzNS4zMi0yMS4yMiA0N2E3My44MSA3My44MSAwIDAgMS0zMC4wNiA5LjYybC05NS42NiA5YTEwMi40NSAxMDIuNDUgMCAwIDAtNDEuOCAxMy4zOEM5IDMzMi40NS00LjgxIDM2MyAxLjUyIDM5NC4yOXMzMC44OSA1NC4wOCA2Mi41NyA1OC4wNmw2LjM1LjhhNDkuNiA0OS42IDAgMCAxIDQzLjM1IDQ5LjEydjE4YTUyLjc1IDUyLjc1IDAgMSAwIDI4LjkxLjI2di0xOC4yNmE3OC41NSA3OC41NSAwIDAgMC02OC42NS03Ny44MWwtNi4zNi0uOGMtMjMuMjQtMi45Mi0zNC43OC0yMC4wNS0zNy44My0zNS4xMXMuOTMtMzUuMzIgMjEuMjItNDdhNzMuNjggNzMuNjggMCAwIDEgMzAuMDYtOS42M2w5NS42Ni05YTEwMi40NSAxMDIuNDUgMCAwIDAgNDEuOC0xMy4zOGMyNy42NS0xNi4wMiA0MS40LTQ2LjU0IDM1LjA5LTc3Ljg2WiIvPjwvc3ZnPg==&logoColor=white) |
| Programming Language | ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Package Manager      | ![Yarn](https://img.shields.io/badge/yarn-%232C8EBB.svg?style=for-the-badge&logo=yarn&logoColor=white)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| Version Control      | ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

## 백엔드 개발 스택

## AI 개발 스택 - 식물 잎 데이터를 기반으로 한 질병 진단 모델
- [AI 개발](https://github.com/hongseoi/crops-plant-disease-classification)
- [Flask 기반 API 생성](https://github.com/hongseoi/pytorch-flask-api)

## Skills
| 역할 | 종류 |
|---|---|
| Language | Python |
| Framework | Pytorch |
| Version control | Git, GitHub | 

## Full Process

### 1. 식물여부 이진분류 모델 생성
식물인지, 식물이 아닌지 판단하는 모델
- dataset: imageNet(mini)
- model: CNN
- output: binary class("plant", "non-plant")


### 2. Image segemtation model 생성
1의 결과가 식물일 경우 맨앞의 잎만 남도록 배경을 제거하는 segmentation 수행
- dataset: 기존 식물 이미지 데이터
- model: U-Net
- output: segmented image


### 3. fine-grained classification model
2의 결과물에 대해서 28개 라벨에 대해 진단하는 식물 질병 진단 모델
- dataset: segmentated 식물잎 이미지 데이터
- model: ResNet
- output: 28개 label


## Project Result
- main 화면


## Expected Outcomes
-

## References
- AI


## Team Members
| 이름 | 역할 |
|---|---|
| 강민지 | AI Developer |
| 문서연 | Frontend Developer |
| 이두용 | Backend Developer | 
| 홍서이 | AI Developer | 
