function Home() {
    return (
        <>
            <body>
                <div className="homebody">
                    <section className="home">
                        <h1>Welcome to TechChefs!</h1>
                    </section>

                    <form className="d-flex col-7" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success bi bi-search" type="submit"></button>
                    </form>
                </div>
            </body>
        </>
    )
}

export default Home;