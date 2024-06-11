import React from "react";
import '../css/components/track.css';

export default function Track({ imageUrl, trackTitle, artistName }) {
    return (
        <div className="track">
            <img className="track-image" src={imageUrl} alt="track" />
            <div className="track-info">
                <h3 className="track-title">{trackTitle}</h3>
                <h2 className="artist-name">{artistName}</h2>
            </div>
        </div>
    )
}
