import { useState } from "react";
import { Player } from "../../types/types";
import "../styles/PlayerList.style.css";
import PlayerModal from "./PlayerModal";

type Props = {
    list: Player[];
    onDeleteClickHnd: (data: Player) => void;
    onEdit: (data: Player) => void;
};

const PlayerList = (props: Props) => {
    const { list, onDeleteClickHnd, onEdit } = props;
    const [showModal, setShowModal] = useState(false);
    const [dataToShow, setDataToShow] = useState(null as Player | null);

    const onCloseModal = () => setShowModal(false);

    const viewPlayer = (data: Player) => {
        setDataToShow(data);
        setShowModal(true);
    };

    return (
        <div>
            <article>
                <h3 className="list-header">Popis igrača</h3>
            </article>
            <table>
                <tr>
                    <th>Ime igrača</th>
                    <th></th>
                </tr>
                {list.map((player) => {
                    return (
                        <tr key={player.id}>
                            <td>{`${player.firstName} ${player.lastName}`}</td>
                            <td>
                                <div>
                                    <input
                                        type="button"
                                        value="Uredi"
                                        onClick={() => onEdit(player)}
                                    />
                                    <input
                                        type="button"
                                        value="Ukloni"
                                        onClick={() => onDeleteClickHnd(player)}
                                    />
                                    <input
                                        type="button"
                                        value="Pregled"
                                        onClick={() => viewPlayer(player)}
                                    />
                                </div>
                            </td>
                        </tr>
                    );
                })}
            </table>
            {showModal && dataToShow !== null && (
                <PlayerModal onClose={onCloseModal} data={dataToShow} />
            )}
        </div>
    );
};

export default PlayerList;