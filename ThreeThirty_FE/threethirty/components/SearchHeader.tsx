import React from 'react';
import {
  Dimensions,
  SafeAreaView,
  StyleSheet,
  TextInput,
  View,
} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  container: {
    width: screenWidth * 0.9,
  },
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: 'lightgray',
    width: '100%',
    paddingHorizontal: 10,
  },
  input: {
    flex: 1,
    height: 45,
    padding: 10,
    backgroundColor: 'lightgray',
    width: '100%',
  },
  icon: {
    fontSize: 24,
    color: 'gray',
  },
});

const SearchHeader = () => {
  const [text, setText] = React.useState('');
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.inputContainer}>
        <TextInput
          style={styles.input}
          onChangeText={setText}
          value={text}
          placeholder="검색하기"
          onSubmitEditing={() => {
            setText('');
          }}
          returnKeyType="search"
        />
        <Icon name="search" style={styles.icon} />
      </View>
    </SafeAreaView>
  );
};

export default SearchHeader;
