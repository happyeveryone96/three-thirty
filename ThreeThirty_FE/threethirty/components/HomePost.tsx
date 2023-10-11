import AsyncStorage from '@react-native-async-storage/async-storage';
import React from 'react';
import {Alert} from 'react-native';
import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Dimensions,
} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 20,
    borderBottomWidth: 1,
  },
  contentBox: {
    flexDirection: 'row',
    width: screenWidth,
    marginLeft: 50,
  },
  avatarBox: {
    width: 40,
    height: 40,
    backgroundColor: 'lightgray',
    borderRadius: 40,
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 10,
  },
  userName: {
    textAlign: 'left',
  },
  bottomBox: {
    display: 'flex',
    flexDirection: 'row',
  },
  bottomSubBox: {
    width: 100,
    paddingTop: 30,
    alignItems: 'center',
    flexDirection: 'row',
  },
  num: {
    marginLeft: 4,
  },
});

interface HomePostProps {
  data: {
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
  };
  handleGoToDetail: () => void;
  setIsBtnClicked: any;
}

const HomePost = (props: HomePostProps) => {
  const {
    post_id,
    nick_name,
    // image_url,
    post_content,
    comment_count,
    like_count,
    hate_count,
    like_status,
    hate_status,
  } = props.data;

  const {handleGoToDetail, setIsBtnClicked} = props;

  const toggleLike = async () => {
    const userData = await AsyncStorage.getItem('userData');
    const accessToken = JSON.parse(userData!)?.accessToken;

    fetch(`http://localhost:8080/post/like/${post_id}`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        Authorization: `Bearer ${accessToken}`,
      },
    }).then(response => {
      const status = JSON.stringify(response?.status);
      setIsBtnClicked(true);
      setIsBtnClicked(false);
      // if (status === '401') {
      //   Alert.alert('토큰 만료');
      // }
    });
  };

  const toggleHate = async () => {
    const userData = await AsyncStorage.getItem('userData');
    const accessToken = JSON.parse(userData!)?.accessToken;

    fetch(`http://localhost:8080/post/hate/${post_id}`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        Authorization: `Bearer ${accessToken}`,
      },
    }).then(response => {
      const status = JSON.stringify(response?.status);
      setIsBtnClicked(true);
      setIsBtnClicked(false);
      // if (status === '401') {
      //   Alert.alert('토큰 만료');
      // }
    });
  };

  return (
    <TouchableOpacity onPress={handleGoToDetail}>
      <View style={styles.container}>
        <View style={styles.contentBox}>
          <View style={styles.avatarBox}>
            <Icon name="person" size={30} color="gray" />
          </View>
          <View>
            <Text style={styles.userName}>{nick_name}</Text>
            <Text>{post_content}</Text>
            <View style={styles.bottomBox}>
              <View style={styles.bottomSubBox}>
                <Icon name="comment" size={30} />
                <Text style={styles.num}>{comment_count}</Text>
              </View>
              <View style={styles.bottomSubBox}>
                <TouchableOpacity onPress={() => toggleLike()}>
                  <Icon
                    name="thumb-up"
                    size={30}
                    color={like_status ? 'red' : 'gray'}
                  />
                </TouchableOpacity>
                <Text style={styles.num}>{like_count}</Text>
              </View>
              <View style={styles.bottomSubBox}>
                <TouchableOpacity onPress={() => toggleHate()}>
                  <Icon
                    name="thumb-down"
                    size={30}
                    color={hate_status ? 'red' : 'gray'}
                  />
                </TouchableOpacity>
                <Text style={styles.num}>{hate_count}</Text>
              </View>
            </View>
          </View>
        </View>
      </View>
    </TouchableOpacity>
  );
};

export default HomePost;
