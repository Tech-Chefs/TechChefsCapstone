function Recipe() {
    return (
        <>
            <div className="container-fluid">
                <h1>Recipe Name</h1>
                <div className="row">
                    <div className="col">
                        <section id="recipeCarousel">Recipe Carousel</section>
                    </div>
                </div>
                <div className="row">
                    <div className="col"><section id="items">Items/Ingredients</section></div>
                    <div className="col-9"><section id="instructions">Instructions</section></div>
                </div>
            </div>


        </>
    )
}

export default Recipe;