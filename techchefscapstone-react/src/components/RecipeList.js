import { useState } from "react"
import { Link } from "react-router-dom"

const RECIPE_DATA = [
    {
        id: 1,
        name: "PB&J Sandwich",
        description: "Peanut butter and fruit preserves—jelly—spread on bread.",
        directions: [
            {
                direction: {
                    instruction: "Spread peanut butter onto 1 slice of bread. Spread 1 tablespoon jelly onto another slice of bread. Place on top of peanut butter, jelly-side down. Spread 1 teaspoon butter onto outside of each slice of bread.",
                    durationMinutes: 5
                }
            }
        ],
        recipeIngredients: [
            {
                ingredient: {
                    id: 3,
                    userId: 1,
                    name: "Peanut Butter",
                    parentId: null,
                    containsDairy: false,
                    containsGluten: false,
                    containsEgg: false,
                    containsSoy: false,
                    containsNut: true,
                    animalBased: false,
                    isMeat: false,
                    isFish: false,
                    subIngredients: null
                },
                measurement: {
                    unit: {
                        id: 3,
                        name: "tablespoon",
                        abbreviation: "tbsp"
                    }
                },
                quantity: 1.5,
                isOptional: false,
                preparation: "By hand"
            },
            {
                ingredient: {
                    id: 4,
                    userId: 1,
                    name: "Grape Jelly",
                    parentId: 5,
                    containsDairy: false,
                    containsGluten: false,
                    containsEgg: false,
                    containsSoy: false,
                    containsNut: false,
                    animalBased: false,
                    isMeat: false,
                    isFish: false,
                    subIngredients: null
                },
                measurement: {
                    unit: {
                        id: 3,
                        name: "tablespoon",
                        abbreviation: "tbsp"
                    }
                },
                quantity: 1.5,
                isOptional: false,
                preparation: "By hand"
            },
            {
                ingredient: {
                    id: 5,
                    userId: 1,
                    name: "Sliced Bread",
                    parentId: 9,
                    containsDairy: true,
                    containsGluten: true,
                    containsEgg: true,
                    containsSoy: true,
                    containsNut: false,
                    animalBased: false,
                    isMeat: false,
                    isFish: false,
                    subIngredients: [
                        {
                            id: 10,
                            userId: 8,
                            name: "White Bread",
                            parentId: 5,
                            containsDairy: true,
                            containsGluten: true,
                            containsEgg: true,
                            containsSoy: true,
                            containsNut: false,
                            animalBased: false,
                            isMeat: false,
                            isFish: false
                        }
                    ]
                },
                measurement: {
                    unit: {
                        id: 9,
                        name: "piece",
                        abbreviation: "pcs"
                    }
                },
                quantity: 2,
                isOptional: false,
                preparation: "By hand"
            }
        ]
    }
]

function RecipeList() {
    const [recipes, setRecipes] = useState(RECIPE_DATA);



    const handleViewRecipe = (recipeId) => {
        console.log(`Viewing: ${recipeId}`)
    }

    return (
        <>
            <section className="home">
                <h1 className="heroSection">Recipes</h1>
            </section>
            <div className="container-fluid">
                {recipes.map(recipe => (
                    <div className="card" key={recipe.id}>
                        <img src="https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/2018_grilled-peanut-butter-and-jelly_20336_600x600.jpg?ext=.jpg" className="card-img-top" alt="..." />
                        <div className="card-body">
                            <h5 className="card-title">{recipe.name}</h5>
                            <p className="card-text">{recipe.description}</p>
                            <Link to={`/recipe/${recipe.id}`} className="btn btn-primary" onClick={() => handleViewRecipe(recipe.id)}>
                                <i className="bi bi-binoculars"> View Recipe </i>
                            </Link>
                        </div>
                    </div>
                ))}
            </div>
        </>
    )
}

export default RecipeList;