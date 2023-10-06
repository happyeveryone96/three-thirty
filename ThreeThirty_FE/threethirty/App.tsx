import React, {useState} from 'react';
import AuthNavigation from './navigations/AuthNavigation';
import TabNavigation from './navigations/TabNavigation';

function App(): JSX.Element {
  const [user, setUser] = useState(undefined);

  const isLoggedIn = user === 'guest';

  return isLoggedIn ? (
    <TabNavigation setUser={setUser} />
  ) : (
    <AuthNavigation setUser={setUser} />
  );
}

export default App;
