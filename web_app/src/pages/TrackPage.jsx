import "../css/track-page.css"
import Actionbar from "../components/actionbar";
import Track from "../components/track";
import DurationTempo from "../components/durationTempo";
import PopularityKeyMode from "../components/popularityKeyMode";
import Chart from "../components/chart";

export default function TrackPage() {
    const chartData = [
        { label: 'danceability', value: 5 },
        { label: 'energy', value: 1 },
        { label: 'loudness', value: 2 },
        { label: 'speechiness', value: 5 },
        { label: 'acousticness', value: 4 },
        { label: 'instrumentalness', value: 1 },
        { label: 'liveness', value: 1 },
        { label: 'valence', value: 5 },
    ];

    return (
        <div>
            <Actionbar className={"actionbar-track-page"}></Actionbar>
            <div className={"track-page page"}>
                <div className="track">
                    <Track imageUrl={"https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1"}
                        trackTitle={"Cut To The Feeling"}
                        artistName={"Carly Rae Jepsen"}
                    ></Track>
                </div>
                <div className="divider"></div>
                <div className="audio-features">
                    <Chart data={chartData} />
                    <DurationTempo duration={"3:46"}
                        tempo={"114.944"}
                    ></DurationTempo>
                    <PopularityKeyMode popularity={"12"}
                        trackKey={"D"}
                        mode={"Major"}
                    ></PopularityKeyMode>
                </div>
            </div>
        </div>
    )
}