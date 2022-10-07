import { Link } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from '../AuthContext';

function Navbar() {
    const auth = useContext(AuthContext);

    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className='container-fluid'>
                <a className='navbar-brand' href='/'>
                    <img src='https://cdn.pixabay.com/photo/2019/07/03/22/45/business-4315606_960_720.jpg' alt='Bootstrap' height="100"/>
                </a>
                <form className="d-flex col-6
                " role="search">
                    <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                        <button className="btn btn-outline-success bi bi-search" type="submit"></button>
                </form>
                <ul className="navbar-nav col-3">
                    <li className='nav-item'><Link className="nav-link" to="/">Home</Link></li>
                    <li className='nav-item'><Link className="nav-link" to="/recipe/add">Create</Link></li>
                    <li className='nav-item'><Link className="nav-link" to="/recipes">Recipes List</Link></li>
                    {auth.user ? 
                    (<li className='nav-item'><button type='button' className="btn btn-danger btn-lg" onClick={() => auth.logout()}>Logout</button></li>) :
                    (<li className='nav-item'><Link type="button" className="btn btn-primary btn-lg" to="/loginpage">Login</Link></li>)
                }
                </ul>
            </div>
        </nav>
    );
}

export default Navbar;