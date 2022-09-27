import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className='container-fluid'>
                <a className='navbar-brand' href='/'>
                    <img src='https://cdn.pixabay.com/photo/2019/07/03/22/45/business-4315606_960_720.jpg' alt='Bootstrap' height="100"/>
                </a>
                <form className="d-flex col-6
                " role="search">
                    <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                        <button className="btn btn-outline-success" type="submit">Search</button>
                </form>
                <ul className="navbar-nav col-3">
                    <li className='nav-item'><Link className="nav-link" to="/">Home</Link></li>
                    <li className='nav-item'><Link className="nav-link" to="/techchefs/add">Create</Link></li>
                    <li className='nav-item'><Link className="nav-link" to="/techchefs">Recipes List</Link></li>
                </ul>
            </div>
        </nav>
    );
}

export default Navbar;