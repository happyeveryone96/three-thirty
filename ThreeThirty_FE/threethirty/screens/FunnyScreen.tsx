import React, {useState} from 'react';
import {ScrollView, StyleSheet, TouchableOpacity, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import FunnyPost from '../components/FunnyPost';
import PostWrite from '../components/PostWrite';

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
  buttonText: {
    color: 'white',
    fontSize: 20,
  },
});

const funnyPostData = [
  {
    id: 1,
    rank: 1,
    content: '#삼성전자 4년동안 물타기 했는데 9층입니다...',
    commentCount: 112,
    likeCount: 1233,
    disLikeCount: 12,
  },
  {
    id: 2,
    rank: 2,
    content: '#삼성전자 3년동안 물타기 했는데 9층입니다...',
    commentCount: 11,
    likeCount: 123,
    disLikeCount: 14,
  },
  {
    id: 3,
    rank: 3,
    content: '#삼성전자 2년동안 물타기 했는데 9층입니다...',
    commentCount: 20,
    likeCount: 70,
    disLikeCount: 12,
  },
  {
    id: 4,
    rank: 4,
    content: '#삼성전자 1년동안 물타기 했는데 9층입니다...',
    commentCount: 10,
    likeCount: 20,
    disLikeCount: 12,
  },
];

const FunnyScreen = () => {
  const [isWriteMode, setIsWriteMode] = useState(false);

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
            {funnyPostData.map(data => (
              <FunnyPost key={data.id} data={data} />
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

export default FunnyScreen;
