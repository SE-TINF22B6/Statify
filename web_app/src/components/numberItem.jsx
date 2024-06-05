import "../css/number-item.css"
import PropTypes from "prop-types";
import { string, number } from 'prop-types';

export default function NumberItem({title, subtitle, number, unit, color}){
    return(
        <div className={"number-item column"}>
            {title != null && <p className={"title"}>{title}</p>}
            {subtitle != null && <p className={"subtitle"}>{subtitle}</p>}
            <p className={"number " + color}>{number}</p>
            <p className={"unit"}>{unit}</p>
        </div>
    )
}

NumberItem.propTypes={
    number: PropTypes.oneOfType([string, number]).isRequired,
    color: PropTypes.oneOf(["orange", "green", "purple"]),
    title: PropTypes.string,
    subtitle: PropTypes.string,
    unit: PropTypes.string.isRequired,
}

NumberItem.defaultProps={
    color: "orange",
    number: 0,
    unit: "songs"
}