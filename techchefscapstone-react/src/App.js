import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './components/Home';
import LoginPage from './components/LoginPage';
import Navbar from './components/Navbar';
import NotFound from './components/NotFound';

function App() {
  return (
    <>
      <Router>
        <Navbar/>
        <Switch>

          <Route path="/" exact>
            <Home/>
          </Route>    

          <Route path={["/techchefs/add","/techchefs/edit/:id"]}>
          </Route>

          <Route path="/techchefs" exact>
          </Route>

          <Route>
            <LoginPage/>
          </Route>     

          <Route>
            <NotFound/>
          </Route>     

        </Switch>       
      </Router>      
    </>
  );
}

export default App;
