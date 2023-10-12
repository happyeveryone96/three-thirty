import React, {useState} from 'react';
import {ScrollView, StyleSheet, TouchableOpacity, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import FollowingPost from '../components/FollowingPost';
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

const followingPostData = [
  {id: 1, content: '너무했어, 삼전'},
  {id: 2, content: '칠만전자 무너지냐?'},
  {id: 3, content: '삼전 너 정말 이럴거냐...'},
  {id: 4, content: 'LG야 이제 그만하자!'},
  {id: 5, content: 'LG야 느그집 안방 안정하시냐'},
  {id: 6, content: '네이버야 진짜 이제 네버 보고싶다'},
  {id: 7, content: '카카오야 진짜 아프리카 가고싶냐...'},
];

const FollowingScreen = () => {
  const [isWriteMode, setIsWriteMode] = useState(false);

  const handleFloatingButtonPress = () => {
    setIsWriteMode(true);
  };
  return (
    <>
      {isWriteMode ? (
        <PostWrite isWriteMode={isWriteMode} setIsWriteMode={setIsWriteMode} />
      ) : (
        <View style={styles.container}>
          <ScrollView>
            {followingPostData.map(data => (
              <FollowingPost key={data.id} data={data} />
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

export default FollowingScreen;
