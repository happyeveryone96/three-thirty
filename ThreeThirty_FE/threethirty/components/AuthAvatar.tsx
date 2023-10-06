import React from 'react';
import {View, Image, StyleSheet, Dimensions} from 'react-native';
import {Icon, ImageSource} from 'react-native-vector-icons/Icon';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  avatarContainer: {
    alignItems: 'center',
    width: screenWidth,
    top: 30,
  },
  avatarBox: {
    width: 100,
    height: 100,
    backgroundColor: 'lightgray',
    borderRadius: 100,
    alignItems: 'center',
    justifyContent: 'center',
  },
  selectedImage: {
    width: 100,
    height: 100,
    borderRadius: 100,
  },
});

interface AuthAvatarProps {
  source: ImageSource;
}

function AuthAvatar({source}: AuthAvatarProps) {
  return (
    <View style={styles.avatarContainer}>
      <View style={styles.avatarBox}>
        {source !== undefined ? (
          <Image source={source} style={styles.selectedImage} />
        ) : (
          <Icon name="person" size={80} color="black" />
        )}
      </View>
    </View>
  );
}

export default AuthAvatar;
