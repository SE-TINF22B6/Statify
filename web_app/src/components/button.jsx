import React from "react";
import "../css/button.css";
import PropTypes from "prop-types";
import {decodeColors04} from "../util/decodeColors";

export default function Button ({className, scale, color, children, widthOffset, onClick}) {
    let textSize = Math.round(32 * scale);
    let buttonWidth = Math.round(340 * scale) + (widthOffset ? widthOffset : 0);
    let buttonHeight = Math.round(80 * scale);
    let bgColor = decodeColors04[color];
    bgColor = bgColor? bgColor : decodeColors04["orange"];

    let buttonStyle = {
        fontSize:textSize + "px",
        width:buttonWidth + "px",
        height:buttonHeight + "px",
        backgroundColor: bgColor
    }
    return (
        <button className={`button ${className}`}
        style={buttonStyle}
        onClick={() => onClick()}>
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


