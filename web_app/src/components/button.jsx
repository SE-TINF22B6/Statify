import React from "react";
import "../css/button.css";
import PropTypes from "prop-types";

export default function Button ({className, scale, color, children, widthOffset}) {
    let textSize = Math.round(32 * scale);
    let buttonWidth = Math.round(340 * scale) + (widthOffset ? widthOffset : 0);
    let buttonHeight = Math.round(80 * scale);
    let bgColor = decodeColors[color];
    bgColor = bgColor? bgColor : decodeColors["orange"];

    let buttonStyle = {
        fontSize:textSize + "px",
        width:buttonWidth + "px",
        height:buttonHeight + "px",
        backgroundColor: bgColor
    }
    return (
        <button className={`button ${className}`}
        style={buttonStyle}>
            <div className="text-wrapper">{children}</div>
        </button>
    );
};

Button.propTypes={
    scale: PropTypes.number,
    color: PropTypes.oneOf(["orange", "green", "purple", "grey"]),
    className: PropTypes.string,
    widthOffset: PropTypes.number
}

Button.defaultProps={
    className:"",
    scale:1
}

const decodeColors = {
    "orange": "var(--collection-1-orange-04)",
    "green": "var(--collection-1-spotify-04)",
    "purple": "var(--collection-1-purple-04)",
    "grey": "var(--collection-1-grey-04)"
}
