import { useState } from "react";
import {Player, Team} from "../../types/types";
import "../styles/PlayerForm.style.css";

type Props = {
    currTeam: Team;
    onBackBtnClickHnd: () => void;
    onSubmitClickHnd: (data: Player) => void;
};

const AddPlayer = (props: Props) => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [birthCity, setBirthCity] = useState("");
    const [nationality, setNationality] = useState("");
    const [team, setTeam] = useState();
    const [position, setPosition] = useState("");

    const { currTeam, onBackBtnClickHnd, onSubmitClickHnd } = props;

    const onFirstNameChangeHnd = (e: any) => {
        setFirstName(e.target.value);
    };

    const onLastNameChangeHnd = (e: any) => {
        setLastName(e.target.value);
    };

    const onBirthDateChangeHnd = (e: any) => {
        setBirthDate(e.target.value);
    };

    const onBirthCityChangeHnd = (e: any) => {
        setBirthCity(e.target.value);
    };

    const onNationalityChangeHnd = (e: any) => {
        setNationality(e.target.value);
    };

    const onPositionChangeHnd = (e: any) => {
        setPosition(e.target.value);
    };

    const onSubmitBtnClickHnd = (e: any) => {
        e.preventDefault();
        const data: Player = {
            id: 1,
            firstName: firstName,
            lastName: lastName,
            birthDate: birthDate,
            birthCity: birthCity,
            nationality: nationality,
            team: currTeam,
            position: position,
        };
        onSubmitClickHnd(data);
        onBackBtnClickHnd();
    };

    return (
        <div className="form-container">
            <div>
                <h3>Add Player Form</h3>
            </div>
            <form onSubmit={onSubmitBtnClickHnd}>
                <div>
                    <label>First Name : </label>
                    <input
                        type="text"
                        value={firstName}
                        onChange={onFirstNameChangeHnd}
                    />
                </div>
                <div>
                    <label>Last Name : </label>
                    <input type="text" value={lastName} onChange={onLastNameChangeHnd} />
                </div>
                <div>
                    <label>Birth Date : </label>
                    <input type="text" value={birthDate} onChange={onBirthDateChangeHnd} />
                </div>
                <div>
                    <label>Birth City : </label>
                    <input type="text" value={birthCity} onChange={onBirthCityChangeHnd} />
                </div>
                <div>
                    <label>Nationality : </label>
                    <input type="text" value={nationality} onChange={onNationalityChangeHnd} />
                </div>
                <div>
                    <label>Position : </label>
                    <input type="text" value={position} onChange={onPositionChangeHnd} />
                </div>
                <div>
                    <input type="button" value="Back" onClick={onBackBtnClickHnd} />
                    <input type="submit" value="Add Player" />
                </div>
            </form>
        </div>
    );
};

export default AddPlayer;