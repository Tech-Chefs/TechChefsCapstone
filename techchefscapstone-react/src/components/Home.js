function Home() {
    return (
        <>
            <body>
                <div className="homebody align-middle">
                    <section class="home">
                        <h1>Welcome to TechChefs!</h1>
                    </section>

                    <form class="d-flex col-6" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </body>
        </>
    )
}

export default Home;