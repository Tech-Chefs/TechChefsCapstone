import { Link } from 'react-router-dom';

function Navbar(){
    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul className="navbar-nav">
                <li><Link className="nav-link" to="/">Home</Link></li>
                <li><Link className="nav-link" to="/grocerylist">Grocery Lists</Link></li>
                <li><Link className="nav-link" to="/recipelist">Recipe List</Link></li>
                <input type="text" placeholder="Search.."></input>
                <li><Link className="nav-link" to="/loginpage">Login</Link></li>
                
            </ul>
        </nav>
    );
}

export default Navbar;