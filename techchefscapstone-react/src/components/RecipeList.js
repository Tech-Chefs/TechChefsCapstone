import { useState, useEffect, useContext } from "react"
import { Link } from "react-router-dom"
import AuthContext from '../AuthContext';

function RecipeList() {
    const [recipes, setRecipes] = useState([]);
    const auth = useContext(AuthContext);

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

    const handleDeleteRecipe = (recipeId) => {
        const recipe = recipes.find(recipe => recipe.id === recipeId);

        if (window.confirm(`Delete Recipe # ${recipe.id} ?`)) {
            const init = {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${auth.user.token}`
                }
            }

            fetch(`http://localhost:8080/api/techchefs/RecipeService/${recipeId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newRecipes = recipes.filter(recipe => recipe.id !== recipeId)
                        setRecipes(newRecipes);
                    }
                    else {
                        return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                    }
                })
                .catch(console.log);
        }
    }

    return (
        <>
            <header>
                <h1 className="heroSection">Recipes</h1>
            </header>
            <div className="container-fluid" id="cardContainer">
                {recipes.map(recipe => (
                    <div className="card" key={recipe.id}>
                        <div className="card-body">
                            <h5 className="card-title">{recipe.name}</h5>
                            <p className="card-text">{recipe.description}</p>
                            <Link to={`/recipe/${recipe.id}`} className="btn btn-primary">
                                <i className="bi bi-binoculars"> View Recipe </i>
                            </Link>
                            <div className="float-right mr-2">
                                {auth.user &&
                                    (<Link className="btn btn-secondary btn mr-2" to={`/recipe/edit/${recipe.id}`}>
                                    <i className="bi bi-pencil-square"></i> Edit
                                </Link>)}
                                {auth.user && auth.user.hasRole("ROLE_ADMIN") && (<button className="btn btn-danger btn" onClick={() => handleDeleteRecipe(recipe.id)}>
                                    <i className="bi bi-trash"></i> Delete
                                </button>)}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </>
    )
}

export default RecipeList;