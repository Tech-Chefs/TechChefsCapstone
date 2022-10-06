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

    const handleAddIngredient = (event) => {
        const newRecipe = { ...recipe, ingredients: [...recipe.ingredients, RECIPE_INGREDIENT_DEFAULT] };
        setRecipe(newRecipe);
        console.log(recipe)
    }

    const handleDirectionChange = (event, index) => {
        const newDirections = [...recipe.directions];
        newDirections[index] = event.target.value;
        const newRecipe = { ...recipe, directions: newDirections };
        setRecipe(newRecipe);
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

    const handleQuantityChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];
        newIngredients[index].measurement.quantity = event.target.value;
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
    }

    const handleUnitChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];

        for (let u of unit) {
            if (u.abbr === event.target.value) {
                newIngredients[index].measurement.unit = u;
            }
        }
        newIngredients[index].measurement.unit.abbr = event.target.value
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
    }

    const handleOptionalChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];
        newIngredients[index].isOptional = event.target.checked;
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
    }

    const handlePreparationChange = (event, index) => {
        const newIngredients = [...recipe.ingredients];
        newIngredients[index].preparation = event.target.value;
        const newRecipe = { ...recipe, ingredients: newIngredients };
        setRecipe(newRecipe);
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
                                <label htmlFor="exampleFormControlTextarea1" className="form-label"><h3>Description:</h3></label>
                                <textarea className="form-control shadow p-3 mb-3 bg-body rounded" id="description" name="description" onChange={handleChange}></textarea>
                            </div>
                        </div>

                        <div className="row">
                            <div className="col">
                                <h3>Ingredients</h3>
                                <section className="row" id="ingre">
                                    <div className="col">
                                        {recipe.ingredients.map((recipeIngredient, index) => (
                                            <section className="row" id="ingre">
                                                <div className='col-6'>
                                                    <label htmlFor="exampleDataList" className="form-label">Ingredient:</label>
                                                    <input className="form-control shadow p-3 mb-3 bg-body rounded" list="datalistOptions" id="exampleDataList" placeholder="Type to search..." key={recipeIngredient.ingredient.id} value={recipeIngredient.ingredient.name} name="ingredient" onChange={(event) => handleIngredientChange(event, index)} />
                                                    <datalist id="datalistOptions">
                                                        {
                                                            ingredient.map(i => (
                                                                <option key={i.id} value={i.name}></option>
                                                            ))}
                                                    </datalist>
                                                    <Link to="/ingredient">Create New Ingredient</Link>
                                                </div>
                                                <div className='col'>
                                                    <label>Measurement</label>
                                                    <input type="number" step="0.0625" min="0.0" className="form-control shadow p-3 mb-3 bg-body rounded" id="exampleFormControlInput1" placeholder="0" value={recipeIngredient.measurement.quantity} name="measurement.quantity" onChange={(event) => handleQuantityChange(event, index)} />
                                                </div>

                                                <div className='col'>
                                                    <label htmlFor="exampleDataList" className="form-label">Unit</label>
                                                    <select id="unitListOptions" className='form-control shadow mb-3 bg-body rounded' value={recipeIngredient.measurement.unit.abbr} onChange={(event) => handleUnitChange(event, index)}>
                                                        {
                                                            unit.map(u => (
                                                                <option key={u.id} value={u.abbr}>{u.abbr}</option>
                                                            ))}
                                                    </select>

                                                    <div className='col'>
                                                        <label htmlFor="preparation">Preparation (optional):</label>
                                                        <input id="preparation" name="preparation" type="text" value={recipeIngredient.preparation}
                                                            className="form-control shadow p-3 mb-2 bg-body rounded" onChange={(event) => handlePreparationChange(event, index)} />
                                                    </div>

                                                    <div className='col'>
                                                        <div className="form-group">
                                                            <input id="isOptional" name="isOptional" type="checkbox" className="mr-2" checked={recipeIngredient.isOptional} onChange={(event) => handleOptionalChange(event, index)} />
                                                            <label htmlFor="tracking">optional</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        ))}
                                        < button className='ingreButton btn btn-secondary' onClick={handleAddIngredient} type='button'>Add Ingredient</button>
                                    </div >
                                </section >
                            </div >
                        </div >
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
                            </div >
                            <div className="submitCancel mt-4">
                                <button className="btn btn-primary mr-2" type="submit">
                                    <i className="bi bi-file-earmark-check"></i>
                                    {id ? " Update Recipe" : " Add Recipe"}
                                </button>
                                <Link className='btn btn-danger' to="/recipes">
                                    <i className='bi bi-stoplights'></i> Cancel
                                </Link>
                            </div>
                        </div >
                    </form >
                </div >
            </div >
        </>
    )
}
export default RecipeForm;