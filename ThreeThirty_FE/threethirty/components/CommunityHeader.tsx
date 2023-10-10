import React from 'react';
import {
  SafeAreaView,
  View,
  TextInput,
  Text,
  StyleSheet,
  Dimensions,
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
  communityContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    width: screenWidth,
    paddingHorizontal: 10,
  },
  icon: {
    fontSize: 24,
    color: 'gray',
  },
  communitySearchIcon: {
    left: 100,
    fontSize: 24,
    color: 'gray',
  },
});

const CommunityHeader = () => {
  const [text, setText] = React.useState('');
  const [isSearch, setIsSearch] = React.useState(false);

  return (
    <>
      {isSearch ? (
        <SafeAreaView style={styles.container}>
          <View style={styles.inputContainer}>
            <TextInput
              style={styles.input}
              onChangeText={setText}
              value={text}
              placeholder="검색하기"
            />
            <Icon name="search" style={styles.icon} />
          </View>
        </SafeAreaView>
      ) : (
        <View style={styles.communityContainer}>
          <Text>커뮤니티</Text>
          <Icon
            name="search"
            style={styles.communitySearchIcon}
            onPress={() => setIsSearch(true)}
          />
        </View>
      )}
    </>
  );
};

export default CommunityHeader;
