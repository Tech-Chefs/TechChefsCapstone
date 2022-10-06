import { useEffect, useState } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';


const RECIPE_DEFAULT =
{
    id: 0,
    name: "",
    description: "",
    directions: ["",],
    recipeIngredients: []
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

const UNIT_TEST = [
    {
        id: 1,
        name: "Teaspoon",
        abbr: "Tsp"
    },
    {
        id: 2,
        name: "Tablespoon",
        abbr: "Tbsp"
    },
    {
        id: 3,
        name: "Whole",
        abbr: "whole"
    }
]

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

    const handleDirectionChange = (event,index) => {
        const newDirections = [...recipe.directions];
        newDirections[index] = event.target.value;
        const newRecipe = {...recipe, directions: newDirections};
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
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(recipe)
        }

        fetch(`http://localhost:8080/api/recipe`, init)
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
            <div className="container-fluid">
                <form>
                    <div className="row">
                        <div className="col-3">
                            <h2>Recipe Name</h2>
                            <label htmlFor="name">Recipe:</label>
                            <input id="name" name="name" type="text" value={recipe.name}
                                className="form-control" onChange={handleChange} />
                        </div>
                        <div className="col-6 mb-3">
                            <h2>Description</h2>
                            <label htmlFor="exampleFormControlTextarea1" className="form-label">Example textarea</label>
                            <textarea className="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-3"></div> {/*for placement design */}

                        <div className="col-6">
                            <h2>Ingredients</h2>
                            <section className="row" id="ingre">
                                        <div className='col'>
                                            <label htmlFor="exampleDataList" className="form-label">Ingredient</label>
                                            <input className="form-control" list="datalistOptions" id="exampleDataList" placeholder="Type to search..." />
                                            <datalist id="datalistOptions">
                                                {
                                                    ingredient.map(i => (
                                                        <option key={i.id} value={i.name} />
                                                    ))}
                                            </datalist>
                                        </div>

                                        <div className='col'>
                                            <label>Measurement</label>
                                            <input type="number" className="form-control" id="exampleFormControlInput1" placeholder="0" />
                                        </div>

                                        <div className='col'>
                                            <label htmlFor="exampleDataList" className="form-label">Unit</label>
                                            <select id="unitListOptions" className='form-control'>
                                                {
                                                    unit.map(u => (
                                                        <option key={u.id} value={u.abbr}>{u.abbr}</option>
                                                    ))}
                                            </select>
                                        </div>
                            </section>
                            <Link className="ingreButton btn btn-secondary" to="/ingredient">Add Ingredient</Link>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-3"></div>
                        <div className="col-6">
                            <h2>Directions</h2>
                            <ol>
                                {recipe.directions.map((direction,index) => (
                                    <li key={index}>
                                        <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" value={direction} onChange={(event) => handleDirectionChange(event,index)}></textarea>
                                    </li>
                                ))}
                            </ol>
                        </div>
                        <button className="ingreButton btn btn-secondary" onClick={handleAddDirection} type='button'>Add Direction</button>
                    </div>
                    <div className="mt-4">
                        <button className="btn btn-success mr-2" type="submit">
                            <i className="bi bi-file-earmark-check"></i>
                            {id ? " Update Recipe" : " Add Recipe"}
                        </button>
                        <Link className='btn btn-warning' to="/recipes">
                            <i className='bi bi-stoplights'></i> Cancel
                        </Link>
                    </div>
                </form>

            </div>
        </>
    )
}

export default RecipeForm;