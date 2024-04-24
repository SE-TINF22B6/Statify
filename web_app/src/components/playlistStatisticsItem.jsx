import "../css/playlist-statistics-item.css"

import NumberItem from "./numberItem";
import PropTypes from "prop-types";

export default function PlaylistStatisticsItem({name, songs, minutes, top_artist, top_genre, top_artist_count, top_genre_count}){
    return (
        <div className={"column playlist-statistics"}>
            <p className={"playlist-name"}>{name}</p>
            <div className={"content column"}>
                <div className={"row"}>
                    <NumberItem number={songs} unit={"Songs"} color={"green"}/>
                    <NumberItem number={minutes} unit={"Minutes"} color={"green"}/>
                </div>
                <div className={"row"}>
                    <NumberItem title={"Top Genre"} subtitle={top_genre} number={top_genre_count} unit={"Songs"} color={"orange"}/>
                    <NumberItem title={"Top Artist"} subtitle={top_artist} number={top_artist_count} unit={"Songs"} color={"orange"}/>
                </div>
            </div>
        </div>
    )
}

PlaylistStatisticsItem.propTypes={
    name: PropTypes.string.isRequired,
    top_artist: PropTypes.string.isRequired,
    top_genre: PropTypes.string.isRequired,
    songs: PropTypes.number.isRequired,
    minutes: PropTypes.number.isRequired,
    top_artist_count: PropTypes.number.isRequired,
    top_genre_count: PropTypes.number.isRequired
}

PlaylistStatisticsItem.defaultProps={
    name: "Playlist",
    top_artist: "Top Artist",
    top_genre: "Top Genre",
    songs: 0,
    minutes: 0,
    top_artist_count: 0,
    top_genre_count: 0
}