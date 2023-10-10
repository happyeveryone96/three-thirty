import React from 'react';
import {StyleSheet, Text, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    paddingHorizontal: 30,
    paddingVertical: 30,
    borderBottomWidth: 1,
  },
  contentBox: {
    flexDirection: 'row',
  },
  rank: {
    fontSize: 30,
    marginRight: 10,
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

interface FunnyPostProps {
  data: {
    id: number;
    rank: number;
    content: string;
    commentCount: number;
    likeCount: number;
    disLikeCount: number;
  };
}

const FunnyPost = (props: FunnyPostProps) => {
  const {rank, content, commentCount, likeCount, disLikeCount} = props.data;
  return (
    <View style={styles.container}>
      <View style={styles.contentBox}>
        <Text style={styles.rank}>{rank}</Text>
        <View>
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
  );
};

export default FunnyPost;
