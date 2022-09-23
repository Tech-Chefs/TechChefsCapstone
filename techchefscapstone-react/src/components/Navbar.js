import { Link } from 'react-router-dom';

function Navbar() {
    return (
<<<<<<< HEAD
        <nav className="navbar navbar-expand-lg bg-light">
            <div className='container-fluid'>
                <a className='navbar-brand' href='#'>
                    <img src='...' alt='Bootstrap' />
                    Tech Chefs
                </a>
                <form class="d-flex col-6
                " role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                        <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <ul className="navbar-nav">
                    <li><Link className="nav-link" to="/">Home</Link></li>
                    <li><Link className="nav-link" to="/techchefs/add">Create</Link></li>
                    <li><Link className="nav-link" to="/techchefs">Recipes</Link></li>
                </ul>
            </div>
=======

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul className="navbar-nav">
                <li><Link className="nav-link" to="/">Home</Link></li>
                <li><Link className="nav-link" to="/grocerylist">Grocery Lists</Link></li>
                <li><Link className="nav-link" to="/recipelist">Recipe List</Link></li>
                <input type="text" placeholder="Search.."></input>
                <li><Link className="nav-link" to="/loginpage">Login</Link></li>
                
            </ul>
>>>>>>> a71b43623f1c0e997c24b3333dcf3800d0310508
        </nav>
    );
}

export default Navbar;