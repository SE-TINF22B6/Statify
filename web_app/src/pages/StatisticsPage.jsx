import "../css/pages/statistics-page.css"
import StatisticsFrame from "../components/statisticsFrame";
import Actionbar from "../components/actionbar";
import StatisticItem from "../components/statisticItem";
import PlaylistStatisticsItem from "../components/playlistStatisticsItem";
import profile from "../images/profile-icon.png";
import {useNavigate} from "react-router-dom";
import Button from "../components/button";
import {useContext, useEffect, useState} from "react";
import GenerateStatisticsDialog from "./GenerateStatisticsDialog";
import {ApiClientContext} from "../App";

export default function StatisticsPage() {

    const navigate = useNavigate();

    const apiClient = useContext(ApiClientContext)

    const [open, setOpen] = useState(false)

    const [playlistStats, setPlaylistStats] = useState(apiClient.playlistStatistics);
    const [artistsStats, setArtistsStats] = useState(apiClient.topArtistStatistics);
    const [tracksStats, setTracksStats] = useState(apiClient.topTrackStatistics);

    const [playlistIndex, setPlaylistIndex] = useState(0)
    const [artistsIndex, setArtistsIndex] = useState(0)
    const [tracksIndex, setTracksIndex] = useState(0)

    useEffect(() => {
        apiClient.getTopTracksStatistics()
            .then(res => {
                setTracksStats(res)
            })
            .catch(err => {
                console.log(err)
            })
        apiClient.getTopArtistsStatistics()
            .then(res => {
                setArtistsStats(res)
            })
            .catch(err => {
                console.log(err)
            })
        apiClient.getPlaylistStatistics()
            .then(res => {
                setPlaylistStats(res)
            })
            .catch(err => {
                console.log(err)
            })
    }, [apiClient]);

    return (
        <div>
            <Actionbar className={"statistics"}>
                <img className="icon" src={profile} alt="Profile Icon" onClick={() => navigate("/profile")}/>
            </Actionbar>
            <div className={"statistics-page page"}>
                <div className={"content column"}>
                    <div className={"row"}>
                        <StatisticsFrame header={"Top Tracks"} subheader={tracksStats[tracksIndex] != null ? new Date((tracksStats[tracksIndex]?.generateDate)).toString().substring(4, 15) : ""} scrollable={tracksStats.length > 1} onScroll={scrollTracks}>
                            <StatisticItem image={tracksStats[tracksIndex]?.firstTrack.imageUrl}
                                           title={tracksStats[tracksIndex]?.firstTrack.name}
                                           subtitle={tracksStats[tracksIndex]?.firstTrack.artists.join(", ")}
                                           number={1}
                                           color={"green"}
                                           className={"clickable"}
                                           onClick={() => navigateToTrack(tracksStats[tracksIndex]?.firstTrack.id)}/>
                            <StatisticItem image={tracksStats[tracksIndex]?.secondTrack.imageUrl}
                                           title={tracksStats[tracksIndex]?.secondTrack.name}
                                           subtitle={tracksStats[tracksIndex]?.secondTrack.artists.join(", ")}
                                           number={2}
                                           color={"orange"}
                                           className={"clickable"}
                                           onClick={() => navigateToTrack(tracksStats[tracksIndex]?.secondTrack.id)}/>
                            <StatisticItem image={tracksStats[tracksIndex]?.thirdTrack.imageUrl}
                                           title={tracksStats[tracksIndex]?.thirdTrack.name}
                                           subtitle={tracksStats[tracksIndex]?.thirdTrack.artists.join(", ")}
                                           number={3}
                                           color={"orange"}
                                           className={"clickable"}
                                           onClick={() => navigateToTrack(tracksStats[tracksIndex]?.thirdTrack.id)}/>
                            <StatisticItem image={tracksStats[tracksIndex]?.fourthTrack.imageUrl}
                                           title={tracksStats[tracksIndex]?.fourthTrack.name}
                                           subtitle={tracksStats[tracksIndex]?.fourthTrack.artists.join(", ")}
                                           number={4}
                                           color={"purple"}
                                           className={"clickable"}
                                           onClick={() => navigateToTrack(tracksStats[tracksIndex]?.fourthTrack.id)}/>
                            <StatisticItem image={tracksStats[tracksIndex]?.fifthTrack.imageUrl}
                                           title={tracksStats[tracksIndex]?.fifthTrack.name}
                                           subtitle={tracksStats[tracksIndex]?.fifthTrack.artists.join(", ")}
                                           number={5}
                                           color={"purple"}
                                           className={"clickable"}
                                           onClick={() => navigateToTrack(tracksStats[tracksIndex]?.fifthTrack.id)}/>
                        </StatisticsFrame>
                        <StatisticsFrame header={"Top Artists"} subheader={artistsStats[artistsIndex] != null ? new Date((artistsStats[artistsIndex]?.generateDate)).toString().substring(4, 15) : ""} scrollable={artistsStats.length > 1} onScroll={scrollArtists}>
                            <StatisticItem image={artistsStats[artistsIndex]?.firstArtist.imageUrl}
                                           title={artistsStats[artistsIndex]?.firstArtist.name}
                                           number={1}
                                           color={"green"}/>
                            <StatisticItem image={artistsStats[artistsIndex]?.secondArtist.imageUrl}
                                           title={artistsStats[artistsIndex]?.secondArtist.name}
                                           number={2}
                                           color={"orange"}/>
                            <StatisticItem image={artistsStats[artistsIndex]?.thirdArtist.imageUrl}
                                           title={artistsStats[artistsIndex]?.thirdArtist.name}
                                           number={3}
                                           color={"orange"}/>
                            <StatisticItem image={artistsStats[artistsIndex]?.fourthArtist.imageUrl}
                                           title={artistsStats[artistsIndex]?.fourthArtist.name}
                                           number={4}
                                           color={"purple"}/>
                            <StatisticItem image={artistsStats[artistsIndex]?.fifthArtist.imageUrl}
                                           title={artistsStats[artistsIndex]?.fifthArtist.name}
                                           number={5}
                                           color={"purple"}/>
                        </StatisticsFrame>
                    </div>
                    <div className={"row"}>
                        <StatisticsFrame header={"Playlist Statistics"} subheader={new Date((playlistStats[playlistIndex]?.generateDate)).toString().substring(4, 15)} scrollable={playlistStats.length > 1} onScroll={scrollPlaylist}>
                            <PlaylistStatisticsItem name={playlistStats[playlistIndex]?.name}
                                                    duration={playlistStats[playlistIndex]?.duration}
                                                    songs={playlistStats[playlistIndex]?.tracksNumber}
                                                    top_artist={playlistStats[playlistIndex]?.topArtist}
                                                    top_genre={playlistStats[playlistIndex]?.topGenre}
                                                    top_artist_count={playlistStats[playlistIndex]?.topArtistTracksNumber}
                                                    top_genre_count={playlistStats[playlistIndex]?.topGenreTracksNumber}/>
                        </StatisticsFrame>
                    </div>
                    <Button color={"orange"} scale={0.6} onClick={() => setOpen(true)}>Generate</Button>
                    <GenerateStatisticsDialog setOpen={setOpen} open={open}
                                                onGenerate={onGenerate}
                    />
                </div>
            </div>
        </div>
    )

    function navigateToTrack(trackId){
        if(trackId) {
            navigate("/track", {state: {trackId: trackId}})
        }
    }

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

    function onGenerate(type, parameters){
        switch (type){
            case "playlist":
                apiClient.generatePlaylistStatistics(parameters.playlistId)
                    .then(res => {
                        let arr = [...playlistStats, res]
                        setPlaylistIndex(arr.length - 1)
                        setPlaylistStats(arr)
                    })
                break
            case "tracks":
                apiClient.generateTopTracksStatistics(parameters.timeRange)
                    .then(res => {
                        let arr = [...tracksStats, res]
                        setTracksIndex(arr.length - 1)
                        setTracksStats(arr)
                    })
                break
            case "artists":
                apiClient.generateTopArtistsStatistics(parameters.timeRange)
                    .then(res => {
                        let arr = [...artistsStats, res]
                        setArtistsIndex(arr.length - 1)
                        setArtistsStats(arr)
                    })
                break
            default:
                break;
        }
    }


}


