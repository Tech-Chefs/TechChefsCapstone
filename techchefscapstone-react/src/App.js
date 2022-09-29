import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './components/Home';
import LoginPage from './components/LoginPage';
import Navbar from './components/Navbar';
import NotFound from './components/NotFound';
import GroceryList from './components/GroceryList';
import RecipeList from './components/RecipeList';
import RecipeForm from './components/RecipeForm';
import ProfilePage from './components/ProfilePage';
import Recipe from './components/Recipe';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>

          <Route path="/" exact>
            <Home />
          </Route>

          <Route path={["/techchefs/add", "/techchefs/edit/:id"]}>
            <RecipeForm />
          </Route>

          <Route path="/techchefs" exact>
          </Route>

          <Route path="/loginpage" exact>
            <LoginPage />
          </Route>

          <Route path="profile" exact>
            <ProfilePage />
          </Route>

          <Route path="/grocerylist" exact>
            <GroceryList />
          </Route>

          <Route path="/recipelist" exact>
            <RecipeList />
          </Route>

          <Route path="/recipe" exact>
            <Recipe />
          </Route>

          <Route>
            <NotFound />
          </Route>

        </Switch>
      </Router>
    </>
  );
}

export default App;
