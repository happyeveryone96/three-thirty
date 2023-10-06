import React, {Dispatch, SetStateAction} from 'react';
import {
  View,
  TouchableWithoutFeedback,
  StyleSheet,
  Dimensions,
} from 'react-native';
import {Icon} from 'react-native-vector-icons/Icon';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  avatarContainer: {
    right: 30,
    alignItems: 'flex-end',
    width: screenWidth,
    top: 10,
  },
  avatarBox: {
    width: 40,
    height: 40,
    backgroundColor: 'lightgray',
    borderRadius: 40,
    alignItems: 'center',
    justifyContent: 'center',
  },
});

interface TabAvatarProps {
  setUser: Dispatch<SetStateAction<any>>;
}

const TabAvatar = ({setUser}: TabAvatarProps) => {
  const logOut = () => {
    setUser(undefined);
  };
  return (
    <View style={styles.avatarContainer}>
      <View style={styles.avatarBox}>
        <TouchableWithoutFeedback onPress={logOut}>
          <Icon name="person" size={30} color="gray" />
        </TouchableWithoutFeedback>
      </View>
    </View>
  );
};

export default TabAvatar;
