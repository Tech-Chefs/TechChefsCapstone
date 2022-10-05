function Home() {
    return (
        <>
            <div className="homebody">
                <section className="home">
                    <h1>Welcome to TechChefs!</h1>

                    <form role="search">
                        <div className="input-group mb-3">
                            <input type="text" className="form-control" placeholder="Search" aria-label="Search" aria-describedby="button-addon2" />
                            <button className="btn btn-success bi bi-search" type="submit" id="button-addon2"></button>
                        </div>
                    </form>
                </section>
            </div>
        </>
    )
}

export default Home;