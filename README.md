# three-thirty
Share your thoughts about stocks and get picked as the best comment

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