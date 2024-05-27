import React from "react";
import '../css/duration-tempo.css';

export default function DurationTempoLoudness({ duration, tempo, loudness }) {
    return (
        <div className="container">
            <div className="item">
                <div className="label">Duration</div>
                <div className="value">{duration}</div>
            </div>
            <div className="item">
                <div className="label">Tempo</div>
                <div className="value">{tempo}</div>
            </div>
            <div className="item">
                <div className="label">Loudness</div>
                <div className="value">{loudness}</div>
            </div>
        </div>
    )
}