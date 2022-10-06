import { useEffect, useState } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';

const RECIPE_INGREDIENT_DEFAULT = {
    ingredient: { id: 0, name: "" },
    measurement: {
        unit: { abbr: "" },
        quantity: 0
    },
    isOptional: false,
    preparation: ""
}

const RECIPE_DEFAULT =
{
    id: 0,
    userId: 4,
    name: "",
    description: "",
    directions: [""],
    ingredients: [RECIPE_INGREDIENT_DEFAULT]
}

// const INGREDIENT_TEST = [
//     {
//         id: 1,
//         userId: 0,
//         name: "tomato",
//         parentId: 0,
//         subIngredients: [

//         ],
//         containsDairy: false,
//         nutBased: false,
//         containsEgg: false,
//         containsGluten: false,
//         containsSoy: false,
//         animalBased: false,
//         isMeat: false,
//         isFish: false
//     },
//     {
//         id: 2,
//         userId: 0,
//         name: "Milk",
//         parentId: 0,
//         subIngredients: [

//         ],
//         containsDairy: true,
//         nutBased: false,
//         containsEgg: false,
//         containsGluten: false,
//         containsSoy: false,
//         animalBased: true,
//         isMeat: false,
//         isFish: false
//     },
//     {
//         id: 3,
//         userId: 0,
//         name: "Almond",
//         parentId: 0,
//         subIngredients: [

//         ],
//         containsDairy: false,
//         nutBased: true,
//         containsEgg: false,
//         containsGluten: false,
//         containsSoy: false,
//         animalBased: false,
//         isMeat: false,
//         isFish: false
//     }
// ]

function RecipeForm() {

    const [recipe, setRecipe] = useState(RECIPE_DEFAULT);
    const [errors, setErrors] = useState([]);
    const { id } = useParams();
    const [ingredient, setIngredient] = useState([]);
    const [unit, setUnit] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/techchefs/IngredientService")
            .then(response => response.json())
            .then(data => setIngredient(data))
            .catch(console.log)
        fetch("http://localhost:8080/api/techchefs/units")
            .then(response => response.json())
            .then(data => setUnit(data))
            .catch(console.log)
    }, [])

    useEffect(() => {
        if (id) {
            fetch(`http://localhost:8080/api/techchefs/RecipeService/${id}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json()
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setRecipe(data))
                .catch(console.log)
        }
    }, [id])

    const history = useHistory();

    const handleAddDirection = (event) => {
        const newRecipe = { ...recipe, directions: [...recipe.directions, ""] };
        setRecipe(newRecipe);
    }

    const handleDirectionChange = (event, index) => {
        const newDirections = [...recipe.directions];
        newDirections[index] = event.target.value;
        const newRecipe = { ...recipe, directions: newDirections };
        setRecipe(newRecipe);
    }

    const handleRecipeIngredientChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];
        newIngredients[index][event.target.name] = event.target.value;
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
        console.log(recipe)
    }

    const handleIngredientChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];

        for (let i of ingredient) {
            if (i.name === event.target.value) {
                newIngredients[index].ingredient = i;
            }
        }
        newIngredients[index].ingredient.name = event.target.value
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
        console.log(recipe)
    }

    const handleChange = (event) => {
        const newRecipe = { ...recipe }

        if (event.target.type === "checkbox") {
            newRecipe[event.target.name] = event.target.checked;
        } else {
            newRecipe[event.target.name] = event.target.value;
        }
        setRecipe(newRecipe);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!id) {
            addRecipe();
        } else {
            updateRecipe();
        }
    }

    const addRecipe = () => {
        console.log(recipe)
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(recipe)
        }

        fetch(`http://localhost:8080/api/techchefs/RecipeService`, init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json()
                }
                else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => {
                if (data.id) {
                    history.push("/recipes")
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    const updateRecipe = () => {
        recipe.id = id;

        const init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(recipe)
        }

        fetch(`http://localhost:8080/api/recipe/${id}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null
                }
                else if (response.status === 400) {
                    return response.json()
                }
                else {
                    return Promise.reject.apply(`Unexpected status code: ${response.status}`)
                }
            })
            .then(data => {
                if (!data) {
                    history.push("/recipes");
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    return (
        <>
            <div className='recipeFormBody'>
                <div className="container ">
                    <form onSubmit={handleSubmit}>
                        <div className="row">
                            <div className="col">
                                <label htmlFor="name"><h3>Recipe Name:</h3></label>
                                <input id="name" name="name" type="text" value={recipe.name}
                                    className="form-control shadow p-3 mb-5 bg-body rounded" onChange={handleChange} />
                            </div>
                        </div>
                        <div className='row'>
                            <div className="col">
                                <label htmlFor="exampleFormControlTextarea1" className="form-label"> <h3>Description:</h3></label>
                                <textarea className="form-control shadow p-3 mb-3 bg-body rounded" id="description" name="description" onChange={handleChange}></textarea>
                            </div>
                        </div>

                        <div className="row">
                            <div className="col">
                                <h3>Ingredients</h3>
                                {recipe.ingredients.map((recipeIngredient, index) => (
                                    <section className="row" id="ingre">
                                        <div className='col'>
                                            <label htmlFor="exampleDataList" className="form-label">Ingredient</label>
                                            <input className="form-control shadow p-3 mb-3 bg-body rounded" list="datalistOptions" id="exampleDataList" placeholder="Type to search..." key={recipeIngredient.ingredient.id} value={recipeIngredient.ingredient.name} name="ingredient" onChange={(event) => handleIngredientChange(event, index)} />
                                            <datalist id="datalistOptions">
                                                {
                                                    ingredient.map(i => (
                                                        <option key={i.id} value={i.name}></option>
                                                    ))}
                                            </datalist>
                                        </div>

                                        <div className='col'>
                                            <label>Measurement</label>
                                            <input type="number" className="form-control shadow p-3 mb-3 bg-body rounded" id="exampleFormControlInput1" placeholder="0" value={recipeIngredient.measurement.quantity} />
                                        </div>

                                        <div className='col'>
                                            <label htmlFor="exampleDataList" className="form-label" value={recipeIngredient.measurement.unit}>Unit</label>
                                            <select id="unitListOptions" className='form-control shadow p-3 mb-3 bg-body rounded'>
                                                {
                                                    unit.map(u => (
                                                        <option key={u.id} value={u.abbr}>{u.abbr}</option>
                                                    ))}
                                            </select>
                                        </div>

                                    </section>
                                ))}
                                <Link className="ingreButton btn btn-primary" to="/ingredient">Create New Ingredient</Link>
                            </div>
                        </div>
                        <div className="row" id='directionForm'>
                            <div className="col">
                                <section className='directionSec'>
                                    <h3>Directions</h3>
                                    <ol>
                                        {recipe.directions.map((direction, index) => (
                                            <li key={index}>
                                                <textarea className="form-control shadow p-3 mb-3 bg-body rounded" id="exampleFormControlTextarea1" value={direction} onChange={(event) => handleDirectionChange(event, index)}></textarea>
                                            </li>
                                        ))}
                                    </ol>
                                </section>
                                <button className="directionBtn btn btn-secondary" onClick={handleAddDirection} type='button'>Add Direction</button>
                            </div>

                        </div>
                        <div className="submitCancel mt-4">
                            <button className="btn btn-primary mr-2" type="submit">
                                <i className="bi bi-file-earmark-check"></i>
                                {id ? " Update Recipe" : " Add Recipe"}
                            </button>
                            <Link className='btn btn-danger' to="/recipes">
                                <i className='bi bi-stoplights'></i> Cancel
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}

export default RecipeForm;