import AsyncStorage from '@react-native-async-storage/async-storage';
import React, {useEffect, useState} from 'react';
import AuthNavigation from './navigations/AuthNavigation';
import TabNavigation from './navigations/TabNavigation';
import {RecoilRoot} from 'recoil';

function App(): JSX.Element {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const updateUserInfo = async () => {
    try {
      const value = await AsyncStorage.getItem('userData');
      if (value !== null) {
        setIsLoggedIn(true);
      } else {
        setIsLoggedIn(false);
      }
    } catch (error) {
      // Error retrieving data
    }
  };

  useEffect(() => {
    updateUserInfo();
  }, [isLoggedIn]);

  return isLoggedIn ? (
    <RecoilRoot>
      <TabNavigation updateUserInfo={updateUserInfo} />
    </RecoilRoot>
  ) : (
    <AuthNavigation updateUserInfo={updateUserInfo} />
  );
}

export default App;
