import "../css/statistics-page.css"
import StatisticsFrame from "../components/statisticsFrame";
import Actionbar from "../components/actionbar";
import StatisticItem from "../components/statisticItem";
import PlaylistStatisticsItem from "../components/playlistStatisticsItem";
import profile from "../images/profile-icon.png";
import {useNavigate} from "react-router-dom";
import Button from "../components/button";
import {useEffect, useState} from "react";
import GenerateStatisticsDialog from "./GenerateStatisticsDialog";
import {getPlaylistStatistics, getTopArtistsStatistics, getTopTracksStatistics} from "../util/dataManager";

export default function StatisticsPage() {

    const navigate = useNavigate();

    const [open, setOpen] = useState(false)

    const [playlistStats, setPlaylistStats] = useState([]);
    const [artistsStats, setArtistsStats] = useState([]);
    const [tracksStats, setTracksStats] = useState([]);

    const [playlistIndex, setPlaylistIndex] = useState(0)
    const [artistsIndex, setArtistsIndex] = useState(0)
    const [tracksIndex, setTracksIndex] = useState(0)

    useEffect(() => {
        getTopTracksStatistics()
            .then(res => {
                setTracksStats(res)
            })
            .catch(err => {
                console.log(err)
            })
        getTopArtistsStatistics()
            .then(res => {
                setArtistsStats(res)
            })
            .catch(err => {
                console.log(err)
            })
        getPlaylistStatistics()
            .then(res => {
                setPlaylistStats(res)
            })
            .catch(err => {
                console.log(err)
            })
    }, []);

    return (
        <div>
            <Actionbar className={"statistics"}>
                <img className="icon" src={profile} alt="Profile Icon" onClick={() => navigate("/profile")}/>
            </Actionbar>
            <div className={"statistics-page page"}>
                <div className={"content column"}>
                    <div className={"row"}>
                        <StatisticsFrame header={"Top Tracks"} subheader={tracksStats[tracksIndex] != null ? new Date((tracksStats[tracksIndex].generateDate)).toString().substring(4, 15) : ""} scrollable={tracksStats.length > 1} onScroll={scrollTracks}>
                            <StatisticItem image={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].firstTrack.imageUrl : ""}
                                           title={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].firstTrack.name : ""}
                                           subtitle={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].firstTrack.artists.join(", "): ""}
                                           number={1}
                                           color={"green"}/>
                            <StatisticItem image={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].secondTrack.imageUrl: ""}
                                           title={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].secondTrack.name : ""}
                                           subtitle={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].secondTrack.artists.join(", ") : ""}
                                           number={2}
                                           color={"orange"}/>
                            <StatisticItem image={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].thirdTrack.imageUrl : ""}
                                           title={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].thirdTrack.name : ""}
                                           subtitle={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].thirdTrack.artists.join(", ") : ""}
                                           number={3}
                                           color={"orange"}/>
                            <StatisticItem image={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fourthTrack.imageUrl : ""}
                                           title={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fourthTrack.name : ""}
                                           subtitle={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fourthTrack.artists.join(", ") : ""}
                                           number={4}
                                           color={"purple"}/>
                            <StatisticItem image={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fifthTrack.imageUrl : ""}
                                           title={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fifthTrack.name : ""}
                                           subtitle={tracksStats[tracksIndex] != null ? tracksStats[tracksIndex].fifthTrack.artists.join(", ") : ""}
                                           number={5}
                                           color={"purple"}/>
                        </StatisticsFrame>
                        <StatisticsFrame header={"Top Artists"} subheader={artistsStats[artistsIndex] != null ? new Date((artistsStats[artistsIndex].generateDate)).toString().substring(4, 15) : ""} scrollable={artistsStats.length > 1} onScroll={scrollArtists}>
                            <StatisticItem image={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].firstArtist.imageUrl : ""}
                                           title={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].firstArtist.name : ""}
                                           number={1}
                                           color={"green"}/>
                            <StatisticItem image={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].secondArtist.imageUrl : ""}
                                           title={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].secondArtist.name : ""}
                                           number={2}
                                           color={"orange"}/>
                            <StatisticItem image={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].thirdArtist.imageUrl : ""}
                                           title={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].thirdArtist.name : ""}
                                           number={3}
                                           color={"orange"}/>
                            <StatisticItem image={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].fourthArtist.imageUrl : ""}
                                           title={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].fourthArtist.name : ""}
                                           number={4}
                                           color={"purple"}/>
                            <StatisticItem image={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].fifthArtist.imageUrl : ""}
                                           title={artistsStats[artistsIndex] != null ? artistsStats[artistsIndex].fifthArtist.name : ""}
                                           number={5}
                                           color={"purple"}/>
                        </StatisticsFrame>
                    </div>
                    <div className={"row"}>
                        <StatisticsFrame header={"Playlist Statistics"} subheader={playlistStats[playlistIndex] != null ? new Date((playlistStats[playlistIndex].generateDate)).toString().substring(4, 15) : ""} scrollable={playlistStats.length > 1} onScroll={scrollPlaylist}>
                            <PlaylistStatisticsItem name={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].name : ""}
                                                    minutes={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].duration : 0}
                                                    songs={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].tracksNumber : 0}
                                                    top_artist={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].topArtist : ""}
                                                    top_genre={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].topGenre : ""}
                                                    top_artist_count={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].topArtistTracksNumber : 0}
                                                    top_genre_count={playlistStats[playlistIndex] != null ? playlistStats[playlistIndex].topGenreTracksNumber : 0}/>
                        </StatisticsFrame>
                    </div>
                    <Button color={"orange"} scale={0.6} onClick={() => setOpen(true)}>Generate</Button>
                    <GenerateStatisticsDialog setOpen={setOpen} open={open}
                                              addPlaylistStats={(stat) => console.log("test")}
                                              addTracksStats={(stat) => console.log("test")}
                                              addArtistStats={(stat) => console.log("test")}
                    />
                </div>
            </div>
        </div>
    )

    function scrollPlaylist(direction){
        if(direction > 0) {
            if (playlistIndex === playlistStats.length - 1) {
                setPlaylistIndex(0)
            }
            else{
                setPlaylistIndex(playlistIndex + 1)
            }
        }
        else if(direction < 0) {
            if (playlistIndex === 0) {
                setPlaylistIndex(playlistStats.length - 1)
            }
            else{
                setPlaylistIndex(playlistIndex - 1)
            }
        }
    }

    function scrollTracks(direction){
        if(direction > 0) {
            if (tracksIndex === tracksStats.length - 1) {
                setTracksIndex(0)
            }
            else{
                setTracksIndex(tracksIndex + 1)
            }
        }
        else if(direction < 0) {
            if (tracksIndex === 0) {
                setTracksIndex(tracksStats.length - 1)
            }
            else{
                setTracksIndex(tracksIndex - 1)
            }
        }
    }

    function scrollArtists(direction){
        if(direction > 0) {
            if (artistsIndex === artistsStats.length - 1) {
                setArtistsIndex(0)
            }
            else{
                setArtistsIndex(artistsIndex + 1)
            }
        }
        else if(direction < 0) {
            if (artistsIndex === 0) {
                setArtistsIndex(artistsStats.length - 1)
            }
            else{
                setArtistsIndex(artistsIndex - 1)
            }
        }
    }
}


