/* eslint-disable react/no-unstable-nested-components */
import React, {useState} from 'react';
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableOpacity,
  Dimensions,
  Button,
  Alert,
  Image,
} from 'react-native';
// import {GoogleSigninButton} from 'react-native-google-signin';
import {DefaultTheme, NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import Icon from 'react-native-vector-icons/MaterialIcons';
import {MediaType, launchImageLibrary} from 'react-native-image-picker';

const Stack = createStackNavigator();
const screenWidth = Dimensions.get('window').width;
const screenHeight = Dimensions.get('window').height;
const styles = StyleSheet.create({
  slogan: {
    paddingTop: 100,
    paddingLeft: 30,
    fontSize: 40,
    fontWeight: '800',
    width: screenWidth * 0.9,
    color: '#1a9988',
  },
  socialButton: {
    // width: screenWidth * 0.92,
    // height: 60,
    width: screenWidth * 0.9,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
  },
  kakaoButton: {
    width: screenWidth * 0.9,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
    margin: 10,
  },
  bottomContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  registerContainer: {
    height: screenHeight,
    width: screenWidth,
  },
  buttonBox: {
    width: screenWidth * 0.9,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
  },
  input: {
    margin: 10,
    borderWidth: 1,
    borderRadius: 4,
    padding: 10,
  },
  desc: {
    margin: 10,
    padding: 10,
  },
  nextButton: {
    height: 40,
    margin: 10,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
    top: 100,
  },
  avatarContainer: {
    alignItems: 'center',
    width: screenWidth,
    top: 30,
  },
  avatarBox: {
    width: 100,
    height: 100,
    backgroundColor: 'lightgray',
    borderRadius: 100,
    alignItems: 'center',
    justifyContent: 'center',
  },
  select: {
    width: screenWidth,
    borderWidth: 1,
    borderColor: 'black',
  },
  selectedImage: {
    width: 100,
    height: 100,
    borderRadius: 100,
  },
  selectButton: {
    top: 30,
    marginBottom: 100,
  },
  question: {
    marginTop: 50,
    // alignItems: 'flex-start',
    paddingHorizontal: 20,
    paddingVertical: 10,
    width: screenWidth,
  },
  loginContainer: {
    width: screenWidth,
  },
  logoBox: {
    alignItems: 'center',
  },
  logo: {
    width: 200,
    height: 200,
    padding: 10,
    marginTop: 50,
    marginBottom: 50,
  },
  loginButton: {
    width: screenWidth * 0.95,
    margin: 10,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
  },
  complete: {
    width: screenWidth * 0.95,
    margin: 10,
    height: 40,
    top: 157,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
  },
});

const RegisterInfoScreen = ({navigation}: any) => {
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  return (
    <View>
      <Text style={styles.desc}>Create Your Account</Text>
      <TextInput
        style={styles.input}
        onChangeText={setEmail}
        value={email}
        placeholder="Email Address"
      />
      <TextInput
        style={styles.input}
        onChangeText={setName}
        value={name}
        placeholder="Name"
      />
      <TextInput
        style={styles.input}
        onChangeText={setPassword}
        value={password}
        placeholder="Password"
        secureTextEntry={true}
      />
      <TextInput
        style={styles.input}
        onChangeText={setPasswordConfirm}
        value={passwordConfirm}
        placeholder="Password Confirm"
        secureTextEntry={true}
      />
      <TextInput
        style={styles.input}
        onChangeText={setPhoneNumber}
        value={phoneNumber}
        placeholder="PhoneNumber"
      />
      <TouchableOpacity onPress={() => navigation.navigate('UserProfileInfo')}>
        <View style={styles.nextButton}>
          <Text>Next</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

function Avatar({source}: any) {
  return (
    <View style={styles.avatarContainer}>
      <View style={styles.avatarBox}>
        {source !== undefined ? (
          <Image source={source} style={styles.selectedImage} />
        ) : (
          <Icon name="person" size={80} color="black" />
        )}
      </View>
    </View>
  );
}

const LoginScreen = ({setUser}: any) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const storeData = () => {
    setUser('guest');
    Alert.alert('로그인 완료');
  };

  return (
    <View style={styles.loginContainer}>
      <View style={styles.logoBox}>
        <Image
          style={styles.logo}
          resizeMode="contain"
          source={require('./assets/three-thirty-logo.png')}
        />
      </View>
      <TextInput
        style={styles.input}
        onChangeText={setEmail}
        value={email}
        placeholder="Email Address or phone number"
      />
      <TextInput
        style={styles.input}
        onChangeText={setPassword}
        value={password}
        placeholder="Password"
        secureTextEntry={true}
      />
      <TouchableOpacity onPress={() => storeData()}>
        <View style={styles.loginButton}>
          <Text>Login</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

type ImageSource = {uri: string | undefined} | undefined;

const RegisterProfileInfo = ({navigation}: any) => {
  const [nickname, setNickname] = useState('');

  const [selectedImage, setSelectedImage] = useState<ImageSource>(undefined);
  const openImagePicker = () => {
    const options = {
      title: '이미지 선택',
      cancelButtonTitle: '취소',
      takePhotoButtonTitle: '카메라로 촬영',
      chooseFromLibraryButtonTitle: '갤러리에서 선택',
      mediaType: 'photo' as MediaType,
    };

    launchImageLibrary(options, response => {
      if (response.didCancel) {
        console.log('사용자가 선택을 취소했습니다.');
      } else if (response.errorCode) {
        console.error('이미지 선택 중 오류 발생:', response.errorCode);
      } else {
        if (response.assets) {
          const source = {uri: response.assets[0].uri};
          setSelectedImage(source);
        }
      }
    });
  };

  return (
    <View>
      <Avatar source={selectedImage} />
      <View style={styles.selectButton}>
        <Button title="파일 선택" onPress={openImagePicker} />
      </View>
      <TextInput
        style={styles.input}
        onChangeText={setNickname}
        value={nickname}
        placeholder="NickName"
      />
      <TouchableOpacity
        onPress={() => {
          Alert.alert('회원가입 완료');
          navigation.navigate('Login');
        }}>
        <View style={styles.complete}>
          <Text>Complete</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

const RegisterMainScreen = ({navigation}: any) => {
  return (
    <View style={styles.registerContainer}>
      <Text style={styles.slogan}>개미들이 성공하는 그날까지!!</Text>
      <View style={styles.bottomContainer}>
        <TouchableOpacity onPress={() => Alert.alert('구글 로그인')}>
          <View style={styles.socialButton}>
            <Text>Continue with Google</Text>
            {/* <Button
              title="Continue with Google"
              onPress={() => Alert.alert('구글 로그인')}
            /> */}
            {/* <GoogleSigninButton
            style={styles.googleButton}
            size={GoogleSigninButton.Size.Wide}
            color={GoogleSigninButton.Color.Dark}
            onPress={() => Alert.alert('구글 로그인')}
          /> */}
          </View>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => Alert.alert('카카오 로그인')}>
          <View style={styles.kakaoButton}>
            <Text>Continue with Kakao Talk</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => navigation.navigate('UserInfo')}>
          <View style={styles.buttonBox}>
            <Text>Create Account</Text>
          </View>
        </TouchableOpacity>
        <Text style={styles.question}>이미 계정이 있으신가요?</Text>
        <TouchableOpacity onPress={() => navigation.navigate('Login')}>
          <View style={styles.buttonBox}>
            <Text>Login</Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const RegisterScreen = ({setUser}: any) => {
  return (
    <NavigationContainer
      theme={{
        ...DefaultTheme,
        colors: {
          ...DefaultTheme.colors,
          background: 'white',
        },
      }}>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={RegisterMainScreen}
          options={{headerShown: false}}
        />
        <Stack.Screen
          name="UserInfo"
          component={RegisterInfoScreen}
          options={({navigation}) => ({
            headerTitle: '세시반',
            headerLeft: () => (
              <Button title="Cancel" onPress={() => navigation.goBack()} />
            ),
          })}
        />
        <Stack.Screen
          name="UserProfileInfo"
          component={RegisterProfileInfo}
          options={({navigation}) => ({
            headerTitle: '',
            headerLeft: () => (
              <Button title="Cancel" onPress={() => navigation.goBack()} />
            ),
          })}
        />
        <Stack.Screen
          name="Login"
          component={() => <LoginScreen setUser={setUser} />}
          options={({navigation}) => ({
            headerLeft: () => (
              <Button title="Cancel" onPress={() => navigation.goBack()} />
            ),
          })}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default RegisterScreen;
