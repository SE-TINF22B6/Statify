import "../css/track-page.css"
import Actionbar from "../components/actionbar";
import Track from "../components/track";
import DurationTempoLoudness from "../components/durationTempoLoudness";
import PopularityKeyMode from "../components/popularityKeyMode";
import Chart from "../components/chart";
import {useContext, useEffect, useState} from "react";
import {keyDict, modeDict} from "../util/decodeAudioFeatures";
import {ApiClientContext} from "../App";
import {useLocation} from "react-router-dom";

export default function TrackPage() {

    const apiClient = useContext(ApiClientContext)

    const location = useLocation();
    const {trackId} = location.state || {};


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
            <Actionbar className={"actionbar-track-page"}></Actionbar>
            <div className={"track-page page"}>
                <div className="track">
                    <Track imageUrl={track.imageUrl}
                           trackTitle={track.name}
                           artistName={track.artists}
                    ></Track>
                </div>
                <div className="divider"></div>
                <div className="audio-features">
                    <Chart data={track.audioFeatures}/>
                    <DurationTempoLoudness duration={track.duration}
                                           tempo={track.tempo}
                                           loudness={track.loudness}
                    ></DurationTempoLoudness>
                    <PopularityKeyMode popularity={track.popularity}
                                       trackKey={track.key}
                                       mode={track.mode}
                    ></PopularityKeyMode>
                </div>
            </div>
        </div>
    )
}

