import { Link } from 'react-router-dom';

function RecipeForm() {
    return (
        <>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-3">
                        <h2>Recipe Name</h2>
                        <section id="recipeName">grd</section>
                    </div>
                    <div className="col-6">
                        <h2>Description</h2>
                        <section id="desc">fwerf</section>
                    </div>
                </div>

                <div className="row">
                    <div className="col-3"></div>
                    <div className="col-6">
                        <h2>Ingredients</h2>
                        <section id="ingre">
                            <Link className="IngreButton btn btn-secondary" to="/ingredient">Add Ingredient</Link>
                        </section>
                    </div>
                </div>
                <div className="row">
                    <div className="col-3"></div>
                    <div className="col-6">
                        <h2>Directions</h2>
                        <section id="direc">dncnw</section>
                    </div>
                </div>
            </div>
        </>
    )
}

export default RecipeForm;