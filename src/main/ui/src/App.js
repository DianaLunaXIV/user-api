import "./App.css";
import UsersContainer from "./components/UsersContainer";
import { useEffect, useState } from "react";

function App() {
  const [usersToDisplay, setUsersToDisplay] = useState([]);
  const [nameQuery, setNameQuery] = useState("");
  const [professionQuery, setProfessionQuery] = useState("");
  const [idQuery, setIdQuery] = useState(null);
  const [startDateQuery, setStartDateQuery] = useState("")
  const [endDateQuery, setEndDateQuery] = useState("")


  useEffect(() => {
    if (idQuery) {
      setProfessionQuery("");
      setNameQuery("");
      fetchById(idQuery).then((users) => setUsersToDisplay(users));
    }
  }, [idQuery]);

  useEffect(() => {
    if (professionQuery) {
      setIdQuery(null);
      setNameQuery("");
      fetchByProfession(professionQuery).then((users) =>
        setUsersToDisplay(users)
      );
    }
  }, [professionQuery]);

  useEffect(() => {
    if (nameQuery) {
      setIdQuery(null);
      setProfessionQuery("");
      fetchByName(nameQuery).then((users) => setUsersToDisplay(users));
    }
  }, [nameQuery]);

  const handleIdChange = (e) => {
    e.preventDefault();
    if (e.target.value) {
      setIdQuery(e.target.value);
    } else {
      setIdQuery(null);
      setUsersToDisplay([]);
    }
  };

  const handleProfessionChange = (e) => {
    e.preventDefault();
    if (e.target.value) {
      setProfessionQuery(e.target.value);
    } else {
      setProfessionQuery("");
      setUsersToDisplay([]);
    }
  };

  const handleNameChange = (e) => {
    e.preventDefault();
    if (e.target.value) {
      setNameQuery(e.target.value);
    } else {
      setNameQuery("");
      setUsersToDisplay([]);
    }
  };

  const handleStartDateChange = (e) => {
    e.preventDefault();
    if (e.target.value) {
        setStartDateQuery(e.target.value)
    } else {
        setStartDateQuery("")
    }
  }

  const handleEndDateChange = (e) => {
      e.preventDefault();
      if (e.target.value) {
          setEndDateQuery(e.target.value)
      } else {
          setEndDateQuery("")
      }
    }

  const handleIdFocusLoss = (e) => {
    e.preventDefault();
    e.target.value = "";
    setIdQuery(null);
  };

  const handleProfessionFocusLoss = (e) => {
    e.preventDefault();
    e.target.value = "";
    setProfessionQuery("");
  };

  const handleNameFocusLoss = (e) => {
    e.preventDefault();
    e.target.value = "";
    setNameQuery("");
  };


  const handleDateSubmit = (e) => {
    e.preventDefault()
    
    fetchByDateRange(startDateQuery, endDateQuery).then(users => setUsersToDisplay(users))

    document.getElementById("start_date").value = ""
    document.getElementById("end_date").value = ""

    setStartDateQuery("")
    setEndDateQuery("")

    
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Hi!</h1>
        <div>
            <input
                type="text"
                id="start_date"
                placeholder="Date (yyyy-mm-dd) from..."
                onChange={handleStartDateChange}
                value={startDateQuery}
            />
            <input
                type="text"
                id="end_date"
                placeholder="Date (yyyy-mm-dd) to..."
                onChange={handleEndDateChange}
                value={endDateQuery}
            />
            <button onClick={handleDateSubmit} type="button">Submit</button>
        </div>
        <div>
          <input
            type="text"
            placeholder="Search by first and last name here"
            id="full_name"
            onChange={handleNameChange}
            onBlur={handleNameFocusLoss}
            value={nameQuery}
          />
        </div>
        <input
          type="text"
          placeholder="Search by ID here"
          onChange={handleIdChange}
          onBlur={handleIdFocusLoss}
          value={idQuery}
        />
        <input
          type="text"
          placeholder="Search by profession here"
          onChange={handleProfessionChange}
          onBlur={handleProfessionFocusLoss}
          value={professionQuery}
        />
        <UsersContainer usersList={usersToDisplay} />
      </header>
    </div>
  );
}

export const fetchByProfession = async (prof) => {
  const response = await fetch(`/api/users?profession=${prof}`);
  return await response.json();
};

export const fetchById = async (id) => {
  const response = await fetch(`/api/users/${id}`);
  return await response.json();
};

export const fetchByName = async (names) => {
  const response = await fetch(
    `/api/users/full-name?firstName=${names.split(" ")[0]}&lastName=${
      names.split(" ")[1]
    }`
  );
  return await response.json();
};

export const fetchByDateRange = async (startDate, endDate) => {
  const response = await fetch(
    `/api/users/dateRange?startDate=${startDate}&endDate=${endDate}`
  )
  return await response.json()
}

export default App;
