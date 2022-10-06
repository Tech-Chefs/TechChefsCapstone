import { useEffect, useState } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';


const INGREDIENTS_DEFAULT = {
    userId: 4,
    name: "",
    parentId: 0,
    subIngredients: [],
    containsDairy: false,
    nutBased: false,
    containsEgg: false,
    containsGluten: false,
    containsSoy: false,
    animalBased: false,
    isMeat: false,
    isFish: false,
    diet: "Vegan",
    gluten: "Gluten Free"
}


function Ingredient() {
    const [ingredient, setIngredient] = useState(INGREDIENTS_DEFAULT);
    const [errors, setErrors] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            fetch(`http://localhost:8080/api/recipe/add/ingredient/${id}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json()
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setIngredient(data))
                .catch(console.log)
        }
    }, [id])

    const history = useHistory();

    const handleChange = (event) => {
        const newIngredient = { ...ingredient }

        if (event.target.type === "checkbox") {
            newIngredient[event.target.name] = event.target.checked;
        } else {
            newIngredient[event.target.name] = event.target.value;
        }
        setIngredient(newIngredient);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!id) {
            addIngredient();
        } else {
            updateIngredient();
        }
    }

    const handleDiet = (event) => {

        switch (event.target.value) {
            case `Vegan`:
                ingredient.animalBased = false
                ingredient.isMeat = false
                ingredient.isFish = false
                ingredient.containsEgg = false
                ingredient.containsDairy = false
                break
            case `Vegetarian`:
                ingredient.animalBased = true
                ingredient.isMeat = false
                ingredient.isFish = false
                break
            case `Meat Based`:
                ingredient.animalBased = true
                ingredient.isMeat = true
                ingredient.isFish = false
                break
            case `Seafood`:
                ingredient.animalBased = true
                ingredient.isMeat = true
                ingredient.isFish = true
                break
        }
        const newIngredient = { ...ingredient }
        newIngredient.diet = event.target.value;
        setIngredient(newIngredient);
    }
    const handleGluten = (event) => {
        switch (event.target.value) {
            case `Gluten Free`:
                ingredient.containsGluten = false
                ingredient.containsSoy = false
                break
            case `Contains Gluten`:
                ingredient.containsGluten = true
                ingredient.containsSoy = false
                break
            case `Contains Soy`:
                ingredient.containsGluten = true
                ingredient.containsSoy = true
                break
        }
        const newIngredient = { ...ingredient }
        newIngredient.gluten = event.target.value;
        setIngredient(newIngredient);
    }

    const addIngredient = () => {
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(ingredient)
        }

        fetch(`http://localhost:8080/api/techchefs/IngredientService`, init)
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
                    history.push("/recipe/add")
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    const updateIngredient = () => {
        ingredient.id = id;

        const init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(ingredient)
        }

        fetch(`http://localhost:8080/api/ingredient/${id}`, init)
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
                    history.push("/recipe/add");
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }


    return (
        <>
            <div className='ingredientFormBody'>
                <form onSubmit={handleSubmit}>
                    {errors.length > 0 && (
                        <div className="alert alert-danger">
                            <p>The following errors were found:</p>
                            <ul>
                                {errors.map(error => (
                                    <li key={error}>{error}</li>
                                ))}
                            </ul>
                        </div>
                    )}
                    <div className="container bg-light">
                        <div className="row">
                            <section className="col" id="ingreName">
                                <div className="form-group">
                                    <label htmlFor="name"><h3>Ingredient:</h3></label>
                                    <input id="name" name="name" type="text" value={ingredient.name}
                                        className="form-control shadow p-3 mb-3 bg-body rounded" onChange={handleChange} />
                                </div>
                            </section>
                        </div>
                        <div className="row">
                            <div className="col">
                                <h3>Diet Info</h3>
                                <section id="dietform">
                                    <div className="form-group shadow  mb-3 bg-body rounded">
                                        <select id="diet" name="diet" className="form-control" value={ingredient.diet}
                                            onChange={handleDiet} >
                                            <option>Vegan</option>
                                            <option>Vegetarian</option>
                                            <option>Meat Based</option>
                                            <option>Seafood</option>
                                        </select>
                                    </div>
                                    <div className="form-group" /*style={{ display: 'none' }}*/>
                                        <input id="containsEgg" name="containsEgg" type="checkbox" className="mr-2" checked={ingredient.containsEgg}
                                            onChange={handleChange} disabled={ingredient.diet === "Vegan"} />
                                        <label htmlFor="tracking">Contains Egg?</label>
                                    </div>
                                    <div className="form-group" /*style={{ display: 'none' }}*/>
                                        <input id="containsDairy" name="containsDairy" type="checkbox" className="mr-2" checked={ingredient.containsDairy}
                                            onChange={handleChange} disabled={ingredient.diet === "Vegan"} />
                                        <label htmlFor="tracking">Contains Dairy?</label>
                                    </div>
                                    <div className="form-group shadow mb-3 bg-body rounded">
                                        <select id="material" name="material" className="form-control" value={ingredient.gluten}
                                            onChange={handleGluten} >
                                            <option>Gluten Free</option>
                                            <option>Contains Gluten</option>
                                            <option>Contains Soy</option>
                                        </select>
                                    </div>
                                    <div className="form-group">
                                        <input id="nutBased" name="nutBased" type="checkbox" className="mr-2" checked={ingredient.nutBased}
                                            onChange={handleChange} />
                                        <label htmlFor="tracking">Nut Based?</label>
                                    </div>
                                </section>
                                <div className="submitCancel mt-4">
                                    <button className="btn btn-primary mr-2" type="submit">
                                        <i className="bi bi-file-earmark-check"></i>
                                        {id ? " Update Ingredient" : " Add Ingredient"}
                                    </button>
                                    <Link className='btn btn-danger' to="/recipe/add">
                                        <i className='bi bi-stoplights'></i> Cancel
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </>
    )
}

export default Ingredient;