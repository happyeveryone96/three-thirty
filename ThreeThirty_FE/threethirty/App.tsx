import React, {useState} from 'react';
import RegisterScreen from './RegisterScreen';
import {useEffect} from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import TabNavigation from './TabNavigation';

function App(): JSX.Element {
  const [user, setUser] = useState(undefined);

  const getData = async (key: string) => {
    try {
      const value = await AsyncStorage.getItem(key);
      if (value !== null) {
        return JSON.parse(value);
      } else {
        return null;
      }
    } catch (e) {
      console.error('데이터 검색 실패', e);
    }
  };

  useEffect(() => {
    getData('user').then(savedUser => {
      if (savedUser !== null) {
        setUser(savedUser);
      } else {
        console.log('사용자 토큰을 찾을 수 없음');
      }
    });
  }, []);

  const isLoggedIn = user === 'guest';

  return isLoggedIn ? (
    <TabNavigation setUser={setUser} />
  ) : (
    <RegisterScreen setUser={setUser} />
  );
}

export default App;
