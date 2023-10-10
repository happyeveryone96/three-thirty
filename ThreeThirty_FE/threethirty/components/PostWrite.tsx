import React, {useState} from 'react';
import {
  Alert,
  Button,
  Dimensions,
  StyleSheet,
  TextInput,
  View,
} from 'react-native';

const screenWidth = Dimensions.get('window').width;
const screenHeight = Dimensions.get('window').height;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 20,
    alignItems: 'center',
    backgroundColor: 'white',
  },
  input: {
    width: screenWidth * 0.8,
    height: screenHeight * 0.6,
    borderWidth: 1,
    borderColor: 'black',
    backgroundColor: 'lightgray',
    padding: 10,
    borderRadius: 10,
  },
});

const PostWrite = ({setIsWriteMode}: any) => {
  const [text, setText] = useState('');
  const hashtags = text.match(/#[\wㄱ-ㅎㅏ-ㅣ가-힣]+/g);

  return (
    <View style={styles.container}>
      <TextInput
        multiline={true}
        style={styles.input}
        onChangeText={setText}
        value={text}
        placeholder="글을 작성해주세요"
        placeholderTextColor={'black'}
      />
      <Button
        title="작성하기"
        onPress={() => {
          Alert.alert(
            `content:${text}` +
              '\n' +
              `hashtags:${hashtags ? hashtags.join(', ') : '없음'}`,
          );
        }}
      />
      <Button
        title="취소하기"
        onPress={() => {
          setIsWriteMode(false);
        }}
      />
    </View>
  );
};

export default PostWrite;
