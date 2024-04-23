import "../css/playlist-statistics-item.css"

import NumberItem from "./numberItem";

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