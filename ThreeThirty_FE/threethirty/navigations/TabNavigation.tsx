/* eslint-disable react/no-unstable-nested-components */
import React, {useState} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import Icon from 'react-native-vector-icons/MaterialIcons';
import Header from '../components/Header';
import SearchHeader from '../components/SearchHeader';
import CommunityHeader from '../components/CommunityHeader';
import NotificationHeader from '../components/NotificationHeader';
import HomeScreen from '../screens/HomeScreen';
import SearchScreen from '../screens/SearchScreen';
import NotificationScreen from '../screens/NotificationScreen';
import CommunityScreen from '../screens/CommunityScreen';
import ThreeThirtyScreen from '../screens/ThreeThirtyScreen';
import FunnyScreen from '../screens/FunnyScreen';
import FollowingScreen from '../screens/FollowingScreen';
import {SetStateAction, Dispatch} from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import DetailScreen from '../screens/DetailScreen';

const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

interface TabNavigationProps {
  setUser: Dispatch<SetStateAction<any>>;
}

const NoTabNavigator = () => (
  <Stack.Navigator>
    <Stack.Screen name="Detail" component={DetailScreen} />
  </Stack.Navigator>
);

function TabNavigation({setUser}: TabNavigationProps) {
  const [screen, setScreen] = useState('main');

  return (
    <NavigationContainer>
      <Tab.Navigator initialRouteName="Home">
        <Tab.Screen
          name="Detail"
          component={NoTabNavigator}
          options={{
            tabBarButton: () => null,
            headerShown: false,
          }}
        />
        <Tab.Screen
          name="Home"
          component={
            (screen === 'main' && HomeScreen) ||
            (screen === 'threeThirty' && ThreeThirtyScreen) ||
            (screen === 'funny' && FunnyScreen) ||
            (screen === 'following' && FollowingScreen) ||
            HomeScreen
          }
          options={{
            title: '홈',
            tabBarIcon: ({color, size}) => (
              <Icon name="home" color={color} size={size} />
            ),
            headerTitle: () => {
              return <Header setScreen={setScreen} setUser={setUser} />;
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
