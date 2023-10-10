import React, {useState} from 'react';
import {NavigationProp} from '@react-navigation/native';
import {
  Alert,
  Button,
  Dimensions,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native';
import {MediaType, launchImageLibrary} from 'react-native-image-picker';
import AuthAvatar from '../components/AuthAvatar';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  selectButton: {
    top: 30,
    marginBottom: 100,
  },
  input: {
    margin: 10,
    borderWidth: 1,
    borderRadius: 4,
    padding: 10,
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

type ImageSource = {uri: string | undefined} | undefined;
interface RegisterProfileInfoScreenProps {
  navigation: NavigationProp<any>;
}

const RegisterProfileInfoScreen = ({
  navigation,
}: RegisterProfileInfoScreenProps) => {
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
      <AuthAvatar source={selectedImage} />
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
        }}
        style={styles.complete}>
        <View>
          <Text>Complete</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export default RegisterProfileInfoScreen;
