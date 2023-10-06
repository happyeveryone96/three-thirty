import React, {Dispatch, SetStateAction} from 'react';
import {View, TouchableWithoutFeedback, Text, StyleSheet} from 'react-native';
import TabAvatar from './TabAvatar';

const styles = StyleSheet.create({
  header: {
    flexDirection: 'row',
    justifyContent: 'center',
    bottom: 20,
  },
  box: {
    marginHorizontal: 10,
  },
  text: {
    color: 'gray',
  },
  line: {
    width: 2,
    display: 'flex',
    alignItems: 'center',
    backgroundColor: 'gray',
  },
});

interface HeaderProps {
  setScreen: Dispatch<SetStateAction<any>>;
  setUser: Dispatch<SetStateAction<any>>;
}

const Header = ({setScreen, setUser}: HeaderProps) => {
  return (
    <View>
      <TabAvatar setUser={setUser} />
      <View style={styles.header}>
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setScreen('main')}>
            <Text style={styles.text}>Main</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setScreen('threeThirty')}>
            <Text style={styles.text}>3:30</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setScreen('funny')}>
            <Text style={styles.text}>Funny</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setScreen('following')}>
            <Text style={styles.text}>Following</Text>
          </TouchableWithoutFeedback>
        </View>
      </View>
    </View>
  );
};

export default Header;
