import React from "react";
import '../css/duration-tempo.css';

export default function DurationTempo({ duration, tempo }) {
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
        </div>
    )
}