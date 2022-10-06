import { useState, useEffect } from "react"
import { Link } from "react-router-dom"

function RecipeList() {
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/techchefs/RecipeService`)
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => setRecipes(data))
            .catch(console.log)
    }, [])

    const handleViewRecipe = (recipeId) => {
        console.log(`Viewing: ${recipeId}`)
    }

    console.log(recipes)

    return (
        <>
            <header>
                <h1 className="heroSection">Recipes</h1>
            </header>
            <div className="container-fluid">
                {recipes.map(recipe => (
                    <div className="card" key={recipe.id}>
                        {/* <img src="https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/2018_grilled-peanut-butter-and-jelly_20336_600x600.jpg?ext=.jpg" className="card-img-top" alt="..." /> */}
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