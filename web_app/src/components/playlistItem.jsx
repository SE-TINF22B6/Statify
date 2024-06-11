import "../css/components/playlist-item.css"
import PropTypes, {bool} from "prop-types";

export default function PlaylistItem({title, image, selected, onClick}){
    return(
        <div className={selected ? "item row selected" : "item row"} onClick={onClick}>
            {image != null && <img src={image} alt=""/>}
            <p className={"title"}>{title}</p>
        </div>
    )
}

PlaylistItem.propTypes={
    title: PropTypes.string.isRequired,
    image: PropTypes.string,
    selected: bool,
}

PlaylistItem.defaultProps={
    selected: false
}

