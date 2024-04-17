# three-thirty
Share your thoughts about stocks and get picked as the best comment

## 데모 영상

![GIFMaker_me](https://github.com/happyeveryone96/three-thirty/assets/66675699/6422635a-ee21-4bde-828e-3e659c7564d6)

<br><br>

## 백엔드 환경설정 메뉴얼

개발 환경 : IntelliJ

### 프로젝트 설정 정보
Project : Gradle-groovy<br>
Language : Java<br>
Spring Boot : 2.7.16<br>
Packaging : Jar<br>
Java : 11<br>
Dependencies : MyBatis Framework, Lombok, Spring Security, Spring Boot Dev Tools, MySQL Driver, OAuth2 Client, Validation<br><br>

<!-- JWT관련 설정, DB 설정 추후 추가 -->
### 프로젝트 폴더 구조
src/main/java/<br>
- config : 애플리케이션의 설정 파일이나 설정 관련 클래스<br>
- domain :  핵심 도메인 객체들이 위치<br>
- dto :  데이터 전달을 위한 클래스들이 위치<br>
- exception : 예외 처리 관련 클래스<br>
- mapper : 데이터베이스와 관련된 매퍼 인터페이스<br>
- security : 보안 관련 클래스<br>
- service : 비즈니스 로직을 처리하는 서비스 클래스<br><br>

src/main/resoucs/<br>
- mapper :  SQL 매핑 파일<br>
- application.yml : 스프링 부트 애플리케이션의 설정을 정의하는 YAML 형식의 설정 파일<br>
- setting.yml : 스프링 부트 애플리케이션의 설정을 정의하는 YAML 형식의 설정 파일<br><br>

build.gradle : Gradle 빌드 도구 설정 파일로, 프로젝트의 의존성 및 빌드 설정이 정의<br><br>


### 실행
방법1<br> 
: src/main/java -> ThreeThirtyApplication.java 우클릭 > Run<br>

방법2<br>
: Run > Edit Configuratiohs > Add new... > Application > Name 설정, Build and run : java11, threeThirty.main, ThreeThirtyApplication > OK<br>
Run Configurations이 생성한 Application으로 설정되어 있는지 확인 > Run(어느 파일에서나 Run 가능)

<br><br>

# React Native 개발 환경 설정

## React Native CLI 설치

```
npm install -g react-native-cli
또는
yarn global add react-native-cli
```

## CocoaPods 설치 (iOS 종속성 관리)

```
sudo gem install cocoapods
```

## iOS 개발 환경 설정

- Xcode 설치
- Xcode 명령줄 도구 설치

```
xcode-select --install
```

- iOS 시뮬레이터 설치
  - Xcode 열기 > Preferences > Components 탭에서 원하는 iOS 시뮬레이터 버전 설치

## CocoaPods 종속성 설치

```bash
cd ThreeThirty_FE
cd threethirty
cd ios
pod install
```

## 폴더 이동 및 앱 실행

### For Android (Android 개발 환경 설정 관련 에러 해결중. 수정 필요)

```bash
cd ..

# using npm
npm run android

# OR using Yarn
yarn android
```

### For iOS

```bash
cd ..

# using npm
npm run ios

# OR using Yarn
yarn ios
```
