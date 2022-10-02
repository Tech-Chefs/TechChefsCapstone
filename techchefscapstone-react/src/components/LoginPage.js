import { Link } from "react-router-dom";

function LoginPage() {
    return (
        <>
            <body className="loginBody">
                <form>
                    <input className="form-control-lg" type="text" placeholder="Username"></input>

                    <br></br>
                    <br></br>

                    <input className="form-control-lg" type="text" placeholder="Password"></input>

                    <br></br>
                    <br></br>

                    <div className="loginButton">
                        <Link className="btn btn-primary " type="submit" to="/profile">
                            <i class="bi bi-box-arrow-in-right"> Submit </i>
                        </Link>
                    </div>


                </form>

            </body>
        </>
    )
}
export default LoginPage;