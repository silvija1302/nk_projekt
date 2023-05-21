import {ChangeEvent, useEffect, useState} from "react";
import {League, Team} from "../../types/types";
import "../styles/TeamForm.style.css";
import axios from "axios";

type Props = {
    data: Team;
    onUpdateClickHnd: (data: Team) => void;
};

const EditTeam = (props: Props) => {
    const { data, onUpdateClickHnd} = props;

    const [name, setName] = useState(data.name);
    const [shortName, setShortName] = useState(data.shortName);
    const [coach, setCoach] = useState(data.coach);
    const [league, setLeague] = useState(data.league);

    const onNameChangeHnd = (e: any) => {
        setName(e.target.value);
    };

    const onShortNameChangeHnd = (e: any) => {
        setShortName(e.target.value);
    };

    const onCoachChangeHnd = (e: any) => {
        setCoach(e.target.value);
    };

    const onLeagueChangeHnd = (e: any) => {
        setLeague(e.target.value);
    };

    const onSubmitBtnClickHnd = (e: any) => {
        e.preventDefault();
        const updatedData: Team = {
            id: 1,
            name: name,
            shortName: shortName,
            coach: coach,
            league: league
        };
        onUpdateClickHnd(updatedData);
    };

    const [leagues, setLeagues] = useState([] as League[]);
    const [selectedOption, setSelectedOption] = useState('');

    useEffect(() => {
        fetchOptions();
    }, []);

    const fetchOptions = async () => {
        try {
            const response: League[] = await axios.get('http://localhost:8080/api/league/all');
            setLeagues(response);
        } catch (error) {
            console.error('Error fetching leagues:', error);
        }
    };

    const handleOptionChange = (event: ChangeEvent<HTMLSelectElement>) => {
        setSelectedOption(event.target.value);
    };

    return (
        <div className="form-container">
            <form onSubmit={onSubmitBtnClickHnd}>
                <div>
                    <label>Name : </label>
                    <input
                        type="text"
                        value={name}
                        onChange={onNameChangeHnd}
                    />
                </div>
                <div>
                    <label>Short Name : </label>
                    <input type="text" value={shortName} onChange={onShortNameChangeHnd} />
                </div>
                <div>
                    <label>Coach : </label>
                    <input type="text" value={coach} onChange={onCoachChangeHnd} />
                </div>
                <div>
                    <select value={selectedOption} onChange={handleOptionChange}>
                        <option value="">Liga</option>
                        {leagues.map((league) => (
                            <option key={league.id} value={league.name}>{league.name}</option>
                        ))}
                    </select>
                    <p>{selectedOption}</p>
                </div>
                <div>
                    <input type="submit" value="Spremi promjene" onChange={onSubmitBtnClickHnd} />
                </div>
            </form>
        </div>
    );
};

export default EditTeam;