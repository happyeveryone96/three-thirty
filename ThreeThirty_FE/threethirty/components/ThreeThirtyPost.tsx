import React from 'react';
import {StyleSheet, Text, View} from 'react-native';

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    paddingVertical: 10,
    paddingHorizontal: 30,
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  content: {
    fontSize: 20,
    fontWeight: '800',
    flex: 1,
  },
  time: {
    color: 'gray',
  },
});

interface ThreeThirtyPostProps {
  data: {
    id: number;
    content: string;
    time: string;
  };
}

const ThreeThirtyPost = (props: ThreeThirtyPostProps) => {
  const {content, time} = props.data;
  return (
    <View style={styles.container}>
      <Text style={styles.content}>{content}</Text>
      <Text style={styles.time}>{time}</Text>
    </View>
  );
};

export default ThreeThirtyPost;
