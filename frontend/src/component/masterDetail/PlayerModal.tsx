import { Player } from "../../types/types";
import "../styles/PlayerModal.style.css";

type Props = {
    onClose: () => void;
    data: Player;
};

const PlayerModal = (props: Props) => {
    const { onClose, data } = props;
    return (
        <div id="myModal" className="modal">
            <div className="modal-content">
        <span className="close" onClick={onClose}>
          &times;
        </span>
                <h3>Informacije o igraču</h3>
                <div>
                    <div>
                        <label>Ime : {data.firstName}</label>
                    </div>
                    <div>
                        <label>Prezime : {data.lastName}</label>
                    </div>
                    <div>
                        <label>Datum rođenja : {data.birthDate}</label>
                    </div>
                    <div>
                        <label>Mjesto rođenja : {data.birthCity}</label>
                    </div>
                    <div>
                        <label>Nacionalnost : {data.nationality}</label>
                    </div>
                    <div>
                        <label>Pozicija : {data.position}</label>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PlayerModal;