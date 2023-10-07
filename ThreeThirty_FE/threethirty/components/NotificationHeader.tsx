import React from 'react';
import {View, Text, StyleSheet, Dimensions} from 'react-native';
import {Icon} from 'react-native-vector-icons/Icon';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  communityContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    width: screenWidth,
    paddingHorizontal: 10,
  },
  communitySearchIcon: {
    left: 100,
    fontSize: 24,
    color: 'gray',
  },
});

const NotificationHeader = () => {
  return (
    <View style={styles.communityContainer}>
      <Text>Notification</Text>
      <Icon name="search" style={styles.communitySearchIcon} />
    </View>
  );
};

export default NotificationHeader;
