import React from "react";
import '../css/popularity-key-mode.css';

export default function PopularityKeyMode({ popularity, trackKey, mode }) {
    return (
        <div className="container">
            <div className="item">
                <div className="label">Popularity</div>
                <div className="lower-value">{popularity}</div>
            </div>
            <div className="item">
                <div className="label">Key</div>
                <div className="lower-value">{trackKey}</div>
            </div>
            <div className="item">
                <div className="label">Mode</div>
                <div className="lower-value">{mode}</div>
            </div>
        </div>
    )
}