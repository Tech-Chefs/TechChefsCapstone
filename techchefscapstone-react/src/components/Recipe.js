import { useState, useEffect } from "react"
import { useParams } from "react-router-dom";

function Recipe() {
    const [recipe, setRecipe] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/api/techchefs/RecipeService/${id}`)
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => {
                console.log(data)
                setRecipe(data)
            })
            .catch(console.log)
    }, [id])

    const renderRecipe = () => {
        console.log(recipe)
        let name = recipe.name;
        let description = recipe.description;
        console.log(recipe.directions)

        let directions = recipe.directions.map(dir => {
            return (
                <li>{dir}</li>
            )
        })

        let ingredients = recipe.ingredients.map(rec => {
            return (
                <>
                    <section className="container" id="ingredients" key={rec.ingredient.id}>
                        {rec.measurement.quantity} {rec.measurement.unit.abbr !== "whole" && rec.measurement.unit.abbr} {rec.ingredient.name}{rec.preparation !== "" && (", " + rec.preparation)} {rec.isOptional && "optional"}
                        <small>{rec.ingredient.containsDairy && " 🥛"} {rec.ingredient.containsGluten && " 🌾"} {rec.ingredient.containsEgg && " 🥚"} {rec.ingredient.containsSoy && " 🟢"}
                            {rec.ingredient.containsNut && " 🥜"} {rec.ingredient.animalBased ? (rec.ingredient.isMeat ? (rec.ingredient.isFish && "🐟") : "🥦") : "🌱"}
                        </small>
                    </section>
                </>
            )
        })

        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12">
                        <h1>{name}</h1>
                        <div className="row">
                            <div className="col">
                                <section id="recipeCarousel">Recipe Carousel</section>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <section id="items">
                                <h2 className="header">Ingredients</h2>
                                <small className="container" id="legend">
                                    Key: Contains Dairy 🥛, Contains Gluten 🌾, Contains Egg 🥚, Contains Soy 🟢, Contains Nut 🥜, Vegan 🌱, Vegetarian 🥦, Seafood 🐟
                                </small>
                                <br></br>
                                <h5>{ingredients}</h5>
                            </section>
                        </div>
                        <div className="col-9">
                            <section id="instructions">
                                <h2 className="header">Directions</h2>
                                <ol>{directions}</ol>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
    if (!recipe) {
        return null;
    }
    return (renderRecipe());
}
export default Recipe;