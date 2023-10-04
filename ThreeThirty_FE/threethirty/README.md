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
