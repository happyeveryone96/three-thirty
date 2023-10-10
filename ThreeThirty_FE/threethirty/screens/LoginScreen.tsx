import React, {Dispatch, SetStateAction, useState} from 'react';
import {
  Dimensions,
  Image,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native';

const screenWidth = Dimensions.get('window').width;

const styles = StyleSheet.create({
  input: {
    margin: 10,
    borderWidth: 1,
    borderRadius: 4,
    padding: 10,
  },
  loginContainer: {
    width: screenWidth,
  },
  logoBox: {
    alignItems: 'center',
  },
  logo: {
    width: 200,
    height: 200,
    padding: 10,
    marginTop: 50,
    marginBottom: 50,
  },
  loginButton: {
    width: screenWidth * 0.95,
    margin: 10,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    borderWidth: 2,
    borderColor: '#1a9988',
  },
});

interface LoginScreenProps {
  setUser: Dispatch<SetStateAction<any>>;
}

const LoginScreen = ({setUser}: LoginScreenProps) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const storeData = () => {
    setUser('guest');
  };

  return (
    <View style={styles.loginContainer}>
      <View style={styles.logoBox}>
        <Image
          style={styles.logo}
          resizeMode="contain"
          source={require('../assets/three-thirty-logo.png')}
        />
      </View>
      <TextInput
        style={styles.input}
        onChangeText={setEmail}
        value={email}
        placeholder="Email Address or phone number"
      />
      <TextInput
        style={styles.input}
        onChangeText={setPassword}
        value={password}
        placeholder="Password"
        secureTextEntry={true}
      />
      <TouchableOpacity onPress={() => storeData()}>
        <View style={styles.loginButton}>
          <Text>Login</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export default LoginScreen;
