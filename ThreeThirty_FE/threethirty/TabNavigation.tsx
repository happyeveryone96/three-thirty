/* eslint-disable react/no-unstable-nested-components */
import React, {useState} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';

import {
  TouchableWithoutFeedback,
  StyleSheet,
  Text,
  View,
  TextInput,
  SafeAreaView,
  Dimensions,
} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const screenWidth = Dimensions.get('window').width;

const Tab = createBottomTabNavigator();

function Avatar({setUser}: any) {
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
}

function HomeScreen() {
  return <Text>Home</Text>;
}

function SearchScreen() {
  return <Text>Search</Text>;
}

function NotificationScreen() {
  return <Text>Notification</Text>;
}

function CommunityScreen() {
  return <Text>Community</Text>;
}

function ThreeThirtyScreen() {
  return <Text>ThreeThirty</Text>;
}

function FunnyScreen() {
  return <Text>Funny</Text>;
}

function FollowingScreen() {
  return <Text>Following</Text>;
}

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
  header: {
    flexDirection: 'row',
    justifyContent: 'center',
    bottom: 20,
  },
  box: {
    marginHorizontal: 10,
  },
  line: {
    width: 2,
    display: 'flex',
    alignItems: 'center',
    backgroundColor: 'gray',
  },
  text: {
    color: 'gray',
  },
  input: {
    flex: 1,
    height: 45,
    padding: 10,
    backgroundColor: 'lightgray',
    width: '100%',
  },
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
  icon: {
    fontSize: 24,
    color: 'gray',
  },
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

function Header({setPage, setUser}: any) {
  return (
    <View>
      <Avatar setUser={setUser} />
      <View style={styles.header}>
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setPage('main')}>
            <Text style={styles.text}>Main</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setPage('threeThirty')}>
            <Text style={styles.text}>3:30</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setPage('funny')}>
            <Text style={styles.text}>Funny</Text>
          </TouchableWithoutFeedback>
        </View>
        <View style={styles.line} />
        <View style={styles.box}>
          <TouchableWithoutFeedback onPress={() => setPage('following')}>
            <Text style={styles.text}>Following</Text>
          </TouchableWithoutFeedback>
        </View>
      </View>
    </View>
  );
}

function SearchHeader() {
  const [text, onChangeText] = React.useState('');
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.inputContainer}>
        <TextInput
          style={styles.input}
          onChangeText={onChangeText}
          value={text}
          placeholder="검색하기"
          onSubmitEditing={() => {
            onChangeText('');
          }}
          returnKeyType="search"
        />
        <Icon name="search" style={styles.icon} />
      </View>
    </SafeAreaView>
  );
}

function CommunityHeader() {
  const [text, onChangeText] = React.useState('');
  const [isSearch, setIsSearch] = React.useState(false);
  return (
    <>
      {isSearch ? (
        <SafeAreaView style={styles.container}>
          <View style={styles.inputContainer}>
            <TextInput
              style={styles.input}
              onChangeText={onChangeText}
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
}

function NotificationHeader() {
  return (
    <View style={styles.communityContainer}>
      <Text>Notification</Text>
      <Icon name="search" style={styles.communitySearchIcon} />
    </View>
  );
}

function TabNavigation({setUser}: any) {
  const [page, setPage] = useState('main');

  return (
    <NavigationContainer>
      <Tab.Navigator initialRouteName="Home">
        <Tab.Screen
          name="Home"
          component={
            (page === 'main' && HomeScreen) ||
            (page === 'threeThirty' && ThreeThirtyScreen) ||
            (page === 'funny' && FunnyScreen) ||
            (page === 'following' && FollowingScreen) ||
            HomeScreen
          }
          options={{
            title: '홈',
            tabBarIcon: ({color, size}) => (
              <Icon name="home" color={color} size={size} />
            ),
            headerTitle: () => {
              return <Header setPage={setPage} setUser={setUser} />;
            },
          }}
        />
        <Tab.Screen
          name="Search"
          component={SearchScreen}
          options={{
            title: '검색',
            tabBarIcon: ({color, size}) => (
              <Icon name="search" color={color} size={size} />
            ),
            headerTitle: () => <SearchHeader />,
          }}
        />
        <Tab.Screen
          name="Community"
          component={CommunityScreen}
          options={{
            title: '커뮤니티',
            tabBarIcon: ({color, size}) => (
              <Icon name="people" color={color} size={size} />
            ),
            headerTitle: () => <CommunityHeader />,
          }}
        />
        <Tab.Screen
          name="Notification"
          component={NotificationScreen}
          options={{
            title: '알림',
            tabBarIcon: ({color, size}) => (
              <Icon name="notifications" color={color} size={size} />
            ),
            headerTitle: () => <NotificationHeader />,
          }}
        />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default TabNavigation;
