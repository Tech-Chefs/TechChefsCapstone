import { Link } from 'react-router-dom';

function Navbar(){
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <ul className="navbar-nav">
                <li><Link className="nav-link" to="/">Home</Link></li>
                <li><Link className="nav-link" to="/fieldagent">Field Agents</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;

