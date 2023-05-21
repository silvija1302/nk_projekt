import { useEffect, useState } from "react";
import AddPlayer from "./forms/AddPlayer";
import EditPlayer from "./forms/EditPlayer";
import EditTeam from "./masterDetail/EditTeam";
import {
    Player,
    PageEnum,
    Team,
    getAllTeams,
    updateTeam,
    createPlayer,
    deletePlayer,
    updatePlayer
} from "../types/types";
import PlayerList from "./masterDetail/PlayerList";
import "./styles/Home.style.css";
import axios, {AxiosResponse} from "axios";

const Home = () => {
    const [playerList, setPlayerList] = useState([] as Player[]);
    const [teamList, setTeamList] = useState([] as Team[]);
    const [currentTeamIndex, setCurrentTeamIndex] = useState(0);
    const [shownPage, setShownPage] = useState(PageEnum.list);
    const [dataToEdit, setDataToEdit] = useState({} as Player);

    useEffect(() => {
        const listInString = window.localStorage.getItem("PlayerList");
        if (listInString) {
            _setPlayerList(JSON.parse(listInString));
        }
    }, []);

    useEffect(() => {
        fetchTeamData();
        fetchPlayersData();
    }, []);

    async function fetchPlayersData() {
        fetch('http://localhost:8080/api/player/all')
            .then((response) => response.json())
            .then((players: Player[]) => {
                const filteredPlayers = players.filter((player) => player.team.id === teamList[currentTeamIndex].id);
                const teamPlayersList: Player[] = [];

                filteredPlayers.forEach((player) => {
                    teamPlayersList.push(player)
                });
                setPlayerList(teamPlayersList);
            })
            .catch((error) => {
                console.error('Error fetching players:', error);
            });
    }

    async function fetchTeamData() {
        try {
            const response: Team[] = await axios.get('https://localhost:8080/api/team/all');
            setTeamList(response);
        } catch (error) {
            console.error('Error retrieving team data:', error);
        }
    }

    function handleNextTeam() {
        setCurrentTeamIndex((prevIndex) => (prevIndex + 1) % teamList.length);
    }

    function handlePreviousTeam() {
        setCurrentTeamIndex((prevIndex) => (prevIndex - 1 + teamList.length) % teamList.length);
    }

    const onAddPlayerClickHnd = () => {
        setShownPage(PageEnum.add);
    };

    const showListPage = () => {
        setShownPage(PageEnum.list);
    };

    const _setPlayerList = (list: Player[]) => {
        setPlayerList(list);
        window.localStorage.setItem("playerList", JSON.stringify(list));
    };

    const addPlayer = (data: Player) => {
        createPlayer(data)
        _setPlayerList([...playerList, data]);
    };

    const removePlayer = (data: Player) => {

        const indexToDelete = playerList.indexOf(data);
        const tempList = [...playerList];

        tempList.splice(indexToDelete, 1);
        deletePlayer(data.id)
        _setPlayerList(tempList);
    };

    const editPlayerData = (data: Player) => {
        setShownPage(PageEnum.edit);
        setDataToEdit(data);
        updatePlayer(data);
    };

    const updateData = (data: Player) => {
        const filteredData = playerList.filter((x) => x.id === data.id)[0];
        const indexOfRecord = playerList.indexOf(filteredData);
        const tempData = [...playerList];
        tempData[indexOfRecord] = data;
        _setPlayerList(tempData);
    };

    const saved = () => {
        updateTeam(teamList[currentTeamIndex])
        alert("Promjene pohranjene")
    };

    return (
        <>
            <article className="article-header">
                <header>
                    <h1>Nogometni Klub</h1>
                </header>
            </article>

            <div>
                <p>Team Name: {teamList[currentTeamIndex]?.name}</p>
                <EditTeam
                    data={teamList[currentTeamIndex]}
                    onUpdateClickHnd={saved}
                />
                <button onClick={handlePreviousTeam} disabled={teamList.length === 0}>Prethodni</button>
                <button onClick={handleNextTeam} disabled={teamList.length === 0}>Idući</button>
            </div>

            <section className="section-content">
                {shownPage === PageEnum.list && (
                    <>
                        <PlayerList
                            list={playerList}
                            onDeleteClickHnd={removePlayer}
                            onEdit={editPlayerData}
                        />
                        <br/>
                        <input
                            type="button"
                            value="Dodaj novog igrača"
                            onClick={onAddPlayerClickHnd}
                            className="add-player-btn"
                        />
                    </>
                )}

                {shownPage === PageEnum.add && (
                    <AddPlayer
                        currTeam = {teamList[currentTeamIndex]}
                        onBackBtnClickHnd={showListPage}
                        onSubmitClickHnd={addPlayer}
                    />
                )}

                {shownPage === PageEnum.edit && (
                    <EditPlayer
                        currTeam={teamList[currentTeamIndex]}
                        data={dataToEdit}
                        onBackBtnClickHnd={showListPage}
                        onUpdateClickHnd={updateData}
                    />
                )}
            </section>
        </>
    );
};

export default Home;