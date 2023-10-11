import React, {useEffect, useState} from 'react';
import {
  Alert,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  View,
} from 'react-native';
import HomePost from '../components/HomePost';
import Icon from 'react-native-vector-icons/MaterialIcons';
import PostWrite from '../components/PostWrite';
import {useNavigation} from '@react-navigation/native';
import AsyncStorage from '@react-native-async-storage/async-storage';

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

interface PostType {
  post_id: number;
  nick_name: string;
  image_url: string;
  post_content: string;
  update_date: null | string;
  like_count: number;
  hate_count: number;
  comment_count: number;
  company_title: string;
  hashtag_content: string[];
  attach_file_url: string[];
  like_status: number;
  hate_status: number;
}

const HomeScreen = () => {
  const [data, setData] = useState<PostType[]>([]);
  const [isWriteMode, setIsWriteMode] = useState(false);
  const [isBtnClicked, setIsBtnClicked] = useState(false);
  const navigation = useNavigation();

  const handleGoToDetail = () => {
    navigation.navigate('Detail');
  };

  const getPost = async () => {
    try {
      const userData = await AsyncStorage.getItem('userData');
      const accessToken = JSON.parse(userData!)?.accessToken;

      const response = await fetch('http://localhost:8080/post', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
      });
      const postData = await response.json();
      setData(postData);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getPost();
  }, [isWriteMode, isBtnClicked]);

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
            {data?.map(post => (
              <HomePost
                key={post.post_id}
                data={post}
                handleGoToDetail={handleGoToDetail}
                setIsBtnClicked={setIsBtnClicked}
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
