import React from 'react';
import {StyleSheet, Text, View} from 'react-native';

const styles = StyleSheet.create({
  container: {
    paddingVertical: 20,
    paddingHorizontal: 40,
  },
  content: {
    fontSize: 20,
    fontWeight: '700',
  },
});

interface FollowingPostProps {
  data: {
    id: number;
    content: string;
  };
}

const FollowingPost = (props: FollowingPostProps) => {
  const {content} = props.data;
  return (
    <View style={styles.container}>
      <Text style={styles.content}>{content}</Text>
    </View>
  );
};

export default FollowingPost;
