import { useState, useEffect } from "react"
import { useParams } from "react-router-dom";

// const RECIPE_DATA = [
//     {
//         id: 1,
//         name: "PB&J Sandwich",
//         description: "Peanut butter and fruit preserves—jelly—spread on bread.",
//         directions: [
//             "Spread peanut butter onto 1 slice of bread.", "Spread 1 tablespoon jelly onto another slice of bread.",
//             "Place on top of peanut butter, jelly-side down.", "Spread 1 teaspoon butter onto outside of each slice of bread.",
//         ],
//         recipeIngredients: [
//             {
//                 ingredient: {
//                     id: 3,
//                     userId: 1,
//                     name: "Peanut Butter",
//                     parentId: null,
//                     containsDairy: false,
//                     containsGluten: false,
//                     containsEgg: false,
//                     containsSoy: false,
//                     containsNut: true,
//                     animalBased: false,
//                     isMeat: false,
//                     isFish: false,
//                     subIngredients: null
//                 },
//                 measurement: {
//                     unit: {
//                         id: 3,
//                         name: "tablespoon",
//                         abbreviation: "tbsp"
//                     },
//                     quantity: 1.5,
//                 },
//                 isOptional: false,
//                 preparation: "By hand"
//             },
//             {
//                 ingredient: {
//                     id: 4,
//                     userId: 1,
//                     name: "Grape Jelly",
//                     parentId: 5,
//                     containsDairy: false,
//                     containsGluten: false,
//                     containsEgg: false,
//                     containsSoy: false,
//                     containsNut: false,
//                     animalBased: false,
//                     isMeat: false,
//                     isFish: false,
//                     subIngredients: null
//                 },
//                 measurement: {
//                     unit: {
//                         id: 3,
//                         name: "tablespoon",
//                         abbreviation: "tbsp"
//                     },
//                     quantity: 1.5
//                 },

//                 isOptional: false,
//                 preparation: "By hand"
//             },
//             {
//                 ingredient: {
//                     id: 5,
//                     userId: 1,
//                     name: "Sliced Bread",
//                     parentId: 9,
//                     containsDairy: true,
//                     containsGluten: true,
//                     containsEgg: true,
//                     containsSoy: true,
//                     containsNut: false,
//                     animalBased: false,
//                     isMeat: false,
//                     isFish: false,
//                     subIngredients: [
//                         {
//                             id: 10,
//                             userId: 8,
//                             name: "White Bread",
//                             parentId: 5,
//                             containsDairy: true,
//                             containsGluten: true,
//                             containsEgg: true,
//                             containsSoy: true,
//                             containsNut: false,
//                             animalBased: false,
//                             isMeat: false,
//                             isFish: false
//                         }
//                     ]
//                 },
//                 measurement: {
//                     unit: {
//                         id: 9,
//                         name: "piece",
//                         abbreviation: "pcs"
//                     },
//                     quantity: 2
//                 },
//                 isOptional: false,
//                 preparation: "By hand"
//             }
//         ]
//     }
// ]

const RECIPE_DEFAULT = {
    id: 0,
    userId: 0,
    name: "",
    description: "",
    ingredients: [],
    directions: []
}

function Recipe() {
    const [recipe, setRecipe] = useState(RECIPE_DEFAULT);
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
                            {rec.measurement.quantity} {rec.measurement.unit.abbreviation} {rec.ingredient.name} (Optional: {rec.isOptional ? "Yes" : "No"})
                            <small>{rec.ingredient.containsDairy ? " 🥛" : ""} {rec.ingredient.containsGluten ? " 🌾" : ""} {rec.ingredient.containsEgg ? " 🥚" : ""} {rec.ingredient.containsSoy ? " 🟢" : ""}
                                {rec.ingredient.containsNut ? " 🥜" : ""} {rec.ingredient.animalBased ? " 🐄" : ""} {rec.ingredient.isMeat ? " 🥩" : ""} {rec.ingredient.isFish ? " 🐟" : ""}
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
                                        Key: Contains Dairy 🥛, Contains Gluten 🌾, Contains Egg 🥚, Contains Soy 🟢, Contains Nut 🥜, Animal Based 🐄, Is Meat 🥩, Is Fish 🐟
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
    return (renderRecipe());
}
export default Recipe;