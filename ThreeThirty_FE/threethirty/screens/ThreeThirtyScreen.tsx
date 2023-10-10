import React, {useState} from 'react';
import {ScrollView, StyleSheet, TouchableOpacity, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import PostWrite from '../components/PostWrite';
import ThreeThirtyPost from '../components/ThreeThirtyPost';

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  floatingButton: {
    position: 'absolute',
    bottom: 20,
    right: 20,
    backgroundColor: '#00b0f0',
    borderRadius: 30,
    width: 60,
    height: 60,
    justifyContent: 'center',
    alignItems: 'center',
  },
  buttonText: {
    color: 'white',
    fontSize: 20,
  },
});

const ThreeThirtyPostData = [
  {id: 1, content: '너무했어, 삼전', time: '3:31pm'},
  {id: 2, content: '너무했어, 삼전!', time: '3:32pm'},
  {id: 3, content: '너무했어, 삼전!!', time: '3:33pm'},
  {id: 4, content: '너무했어, 삼전!!!', time: '3:34pm'},
  {id: 5, content: '너무했어, 삼전!!!!', time: '3:35pm'},
  {id: 6, content: '너무했어, 삼전!!!!!', time: '3:36pm'},
  {id: 7, content: '너무했어, 삼전!!!!', time: '3:35pm'},
  {id: 8, content: '너무했어, 삼전!!!!!', time: '3:36pm'},
  {id: 9, content: '너무했어, 삼전!!!!', time: '3:37pm'},
  {id: 10, content: '너무했어, 삼전!!!!!', time: '3:38pm'},
  {id: 11, content: '너무했어, 삼전!!!!', time: '3:40pm'},
  {id: 12, content: '너무했어, 삼전!!!!!!!!', time: '3:36pm'},
];

const ThreeThirtyScreen = () => {
  const [isWriteMode, setIsWriteMode] = useState(false);

  const handleFloatingButtonPress = () => {
    setIsWriteMode(true);
  };
  return (
    <>
      {isWriteMode ? (
        <PostWrite setIsWriteMode={setIsWriteMode} />
      ) : (
        <View style={styles.container}>
          <ScrollView>
            {ThreeThirtyPostData.map(data => (
              <ThreeThirtyPost key={data.id} data={data} />
            ))}
          </ScrollView>

          <TouchableOpacity
            style={styles.floatingButton}
            onPress={handleFloatingButtonPress}>
            <Icon name="create" size={30} color="white" />
          </TouchableOpacity>
        </View>
      )}
    </>
  );
};

export default ThreeThirtyScreen;
