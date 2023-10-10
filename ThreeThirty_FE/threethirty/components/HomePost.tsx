import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 20,
    borderBottomWidth: 1,
  },
  contentBox: {
    flexDirection: 'row',
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
    flex: 1,
    alignItems: 'center',
    flexDirection: 'row',
  },
  num: {
    marginLeft: 4,
  },
});

interface HomePostProps {
  data: {
    id: number;
    userName: string;
    content: string;
    commentCount: number;
    likeCount: number;
    disLikeCount: number;
  };
  handleGoToDetail: () => void;
}

const HomePost = (props: HomePostProps) => {
  const {userName, content, commentCount, likeCount, disLikeCount} = props.data;
  const {handleGoToDetail} = props;
  return (
    <TouchableOpacity onPress={handleGoToDetail}>
      <View style={styles.container}>
        <View style={styles.contentBox}>
          <View style={styles.avatarBox}>
            <Icon name="person" size={30} color="gray" />
          </View>
          <View>
            <Text style={styles.userName}>{userName}</Text>
            <Text>{content}</Text>
            <View style={styles.bottomBox}>
              <View style={styles.bottomSubBox}>
                <Icon name="comment" size={30} />
                <Text style={styles.num}>{commentCount}</Text>
              </View>
              <View style={styles.bottomSubBox}>
                <Icon name="thumb-up" size={30} color="gray" />
                <Text style={styles.num}>{likeCount}</Text>
              </View>
              <View style={styles.bottomSubBox}>
                <Icon name="thumb-down" size={30} color="gray" />
                <Text style={styles.num}>{disLikeCount}</Text>
              </View>
            </View>
          </View>
        </View>
      </View>
    </TouchableOpacity>
  );
};

export default HomePost;
