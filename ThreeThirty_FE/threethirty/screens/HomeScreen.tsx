import React, {useState} from 'react';
import {ScrollView, StyleSheet, TouchableOpacity, View} from 'react-native';
import HomePost from '../components/HomePost';
import Icon from 'react-native-vector-icons/MaterialIcons';
import PostWrite from '../components/PostWrite';
import {useNavigation} from '@react-navigation/native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  floatingButton: {
    position: 'absolute',
    bottom: 20,
    right: 20,
    backgroundColor: '#00b0f0',
    borderRadius: 30,
    width: 60,
    height: 60,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

const homePostData = [
  {
    id: 1,
    userName: '김함바',
    content: '#삼성전자 3년동안 물타기했는데 9층입니다...',
    commentCount: 12,
    likeCount: 23,
    disLikeCount: 1,
  },
  {
    id: 2,
    userName: '김함바',
    content: '#삼성전자 4년동안 물타기했는데 9층입니다...',
    commentCount: 112,
    likeCount: 203,
    disLikeCount: 1,
  },
  {
    id: 3,
    userName: '김함바',
    content: '#삼성전자 5년동안 물타기했는데 9층입니다...',
    commentCount: 112,
    likeCount: 233,
    disLikeCount: 1,
  },
  {
    id: 4,
    userName: '김함바',
    content: '#삼성전자 6년동안 물타기했는데 9층입니다...',
    commentCount: 102,
    likeCount: 333,
    disLikeCount: 12,
  },
  {
    id: 5,
    userName: '김함바',
    content: '#삼성전자 2년동안 물타기했는데 9층입니다...',
    commentCount: 102,
    likeCount: 33,
    disLikeCount: 12,
  },
];

const HomeScreen = () => {
  const [isWriteMode, setIsWriteMode] = useState(false);
  const navigation = useNavigation();

  const handleGoToDetail = () => {
    navigation.navigate('Detail');
  };

  const handleFloatingButtonPress = () => {
    setIsWriteMode(true);
  };

  return (
    <>
      {isWriteMode ? (
        <PostWrite setIsWriteMode={setIsWriteMode} />
      ) : (
        <View style={styles.container}>
          <ScrollView>
            {homePostData.map(data => (
              <HomePost
                key={data.id}
                data={data}
                handleGoToDetail={handleGoToDetail}
              />
            ))}
          </ScrollView>

          <TouchableOpacity
            style={styles.floatingButton}
            onPress={handleFloatingButtonPress}>
            <Icon name="create" size={30} color="white" />
          </TouchableOpacity>
        </View>
      )}
    </>
  );
};

export default HomeScreen;
