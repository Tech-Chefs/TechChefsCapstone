import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './components/Home';
import Navbar from './components/Navbar';
import LoginPage from './components/LoginPage';
import NotFound from './components/NotFound';
import RecipeList from './components/RecipeList';

function App() {
  return (
    <>
      <Router>
        <h1>Tech Chefs</h1>
        <Navbar/>
        <Switch>

          <Route path="/" exact>
            <Home/>
          </Route>    

          <Route path={["/techchefs/add","/techchefs/edit/:id"]}>
            <RecipeForm/>
          </Route>

          <Route path="/techchefs" exact>
            <RecipeList/>
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
