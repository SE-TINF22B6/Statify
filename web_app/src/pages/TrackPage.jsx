import "../css/pages/track-page.css"
import Actionbar from "../components/actionbar";
import Track from "../components/track";
import Chart from "../components/chart";
import {useContext, useEffect, useState} from "react";
import {keyDict, modeDict} from "../util/decodeAudioFeatures";
import {ApiClientContext} from "../App";
import {useLocation, useNavigate} from "react-router-dom";
import TrackInfoRow from "../components/trackInfoRow";
import profile from "../images/profile-icon.png";

export default function TrackPage() {

    const apiClient = useContext(ApiClientContext)

    const location = useLocation();
    const {trackId} = location.state || {};

    const navigate = useNavigate()

    const [track, setTrack] = useState({
        name: "Track Name",
        duration: "00:00",
        popularity: 0,
        tempo: 0,
        key: "Key",
        mode: "Mode",
        artists: "Artists",
        imageUrl: "",
        audioFeatures: [
            {label: 'danceability', value: 0},
            {label: 'energy', value: 0},
            {label: 'speechiness', value: 0},
            {label: 'acousticness', value: 0},
            {label: 'instrumentalness', value: 0},
            {label: 'liveness', value: 0},
            {label: 'valence', value: 0},
        ]
    })

    useEffect(() => {
        apiClient.fetchTrack(trackId)
            .then((res) => {
                setTrack({
                    name: res.name,
                    duration: res.duration,
                    popularity: res.popularity,
                    tempo: res.audioFeatures.tempo,
                    key: keyDict[res.audioFeatures.key],
                    mode: modeDict[res.audioFeatures.mode],
                    artists: res.artists.join(", "),
                    imageUrl: res.imageUrl,
                    loudness: res.audioFeatures.loudness,
                    audioFeatures: [
                        {label: 'danceability', value: res.audioFeatures.danceability},
                        {label: 'energy', value: res.audioFeatures.energy},
                        {label: 'speechiness', value: res.audioFeatures.speechiness},
                        {label: 'acousticness', value: res.audioFeatures.acousticness},
                        {label: 'instrumentalness', value: res.audioFeatures.instrumentalness},
                        {label: 'liveness', value: res.audioFeatures.liveness},
                        {label: 'valence', value: res.audioFeatures.valence},
                    ]
                })
            })
            .catch(err => {
                console.log(err)
            })
    }, [apiClient, trackId]);


    return (
        <div>
            <Actionbar className={"actionbar-track-page"}>
                <img className="icon" src={profile} alt="Profile Icon" onClick={() => navigate("/profile")}/>
            </Actionbar>
            <div className={"track-page page row"}>
                <div className="track">
                    <Track imageUrl={track.imageUrl}
                           trackTitle={track.name}
                           artistName={track.artists}
                    ></Track>
                </div>
                <div className={"vr"}/>
                <div className="audio-features column">
                    <Chart data={track.audioFeatures}/>
                    <TrackInfoRow
                    data={[
                        {key: "Duration",
                        value: track.duration},
                        {key: "Tempo",
                        value: track.tempo},
                        {key: "Loudness",
                        value: track.loudness}
                    ]}
                    color={"orange"}
                    scale={1.4}/>
                    <TrackInfoRow
                        data={
                        [
                            {key: "Popularity",
                            value: track.popularity},
                            {key: "Key",
                            value: track.key},
                            {key: "Mode",
                            value: track.mode}
                        ]
                        }
                        scale={1.4}
                        color={"green"}
                    />
                </div>
            </div>
        </div>
    )
}

