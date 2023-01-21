import './App.css';
import UsersContainer from "./components/UsersContainer";
import {useEffect, useState} from "react";

function App() {


    const [usersToDisplay, setUsersToDisplay] = useState([])
    const [professionQuery, setProfessionQuery] = useState("");
    const [idQuery, setIdQuery] = useState(null)

    useEffect(() => {
        if (professionQuery) {
            setIdQuery(null);
            fetchByProfession(professionQuery).then(users => setUsersToDisplay(users))
        }
    }, [professionQuery])

    useEffect(() => {
        if (idQuery) {
            setProfessionQuery("")
            fetchById(idQuery).then(users => setUsersToDisplay(users))
        }
    }, [idQuery])

    const handleIdChange = (e) => {
        e.preventDefault()
        if (e.target.value) {
            setIdQuery(e.target.value)
        } else {
            setIdQuery(null)
            setUsersToDisplay([])
        }
    }

    const handleProfessionChange = (e) => {
        e.preventDefault()
        if (e.target.value) {
            setProfessionQuery(e.target.value)
        } else {
            setProfessionQuery("")
            setUsersToDisplay([])
        }
    }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Hi!</h1>
          <input
              type="text"
              placeholder="Search by ID here"
              onChange={handleIdChange}
              value={idQuery} />
          <input
              type="text"
              placeholder="Search by profession here"
              onChange={handleProfessionChange}
              value={professionQuery} />
        <UsersContainer usersList={usersToDisplay}/>
      </header>
    </div>
  );
}

export const fetchByProfession = async (prof) => {
    const response = await fetch(`/api/users?profession=${prof}`);
    return await response.json()
}

export const fetchById = async (id) => {
    const response = await fetch(`/api/users/${id}`)
    return await response.json()
}

export default App