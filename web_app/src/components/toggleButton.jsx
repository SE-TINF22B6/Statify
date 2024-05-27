import "../css/toggle-button.css";
import {decodeColors} from "../util/decodeColors";
import PropTypes from "prop-types";

export default function ToggleButton({className, color, selected, setSelected, buttonWidth, textSize, choices, onToggle}){

    let style = {
        fontSize:textSize + "px",
        width:buttonWidth + "px"
    }

    let selectedStyle = {
        backgroundColor: decodeColors[color],
        opacity: "100%"

    }

    let unselectedStyle = {
        backgroundColor: decodeColors[color],
        opacity: "40%"
    }

    return(
        <div className="toggle-button row" style={style}>
            <button className={className}
                    style={selected === 0 ? selectedStyle : unselectedStyle}
                    onClick={() => {
                        onToggle(0)
                        setSelected(0)
                    }}
            >{choices[0]}</button>
            <button className={className}
                    style={selected === 1 ? selectedStyle : unselectedStyle}
                    onClick={() => {
                        onToggle(1)
                        setSelected(1)
                    }}
            >{choices[1]}</button>
        </div>
    )
}

const arrayOfLength = (expectedLength, props, propName, componentName) => {
    const arrayPropLength = props[propName].length

    if (arrayPropLength !== expectedLength) {
        return new Error(
            `Invalid array length ${arrayPropLength} (expected ${expectedLength}) for prop ${propName} supplied to ${componentName}. Validation failed.`
        )
    }
}

ToggleButton.propTypes={
    color: PropTypes.oneOf(["orange", "green", "purple"]),
    className: PropTypes.string,
    selected: PropTypes.oneOf([0, 1]),
    choices: arrayOfLength.bind(null, 2),
    onToggle: PropTypes.func
}

ToggleButton.defaultProps={
    className:"",
    selected:0,
    choices:["1", "2"],
    onToggle: () => {}
}

