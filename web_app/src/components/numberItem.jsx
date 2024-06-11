import "../css/components/number-item.css"
import PropTypes from "prop-types";
import { string, number } from 'prop-types';

export default function NumberItem({title, subtitle, number, unit, color, dataTestId, scale }){
    let titleSize = Math.round(14 * scale);
    let subtitleSize = Math.round(12 * scale);
    let numberSize = Math.round(42 * scale);
    let unitSize = Math.round(10 * scale);


    return(
        <div className={"number-item column"}>
            {title != null && <p className={"title"} style={{fontSize: titleSize + "pt"}}>{title}</p>}
            {subtitle != null && <p className={"subtitle"} style={{fontSize: subtitleSize + "pt"}}>{subtitle}</p>}
            <p className={"number " + color} data-testid={dataTestId} style={{fontSize: numberSize + "pt"}}>{number}</p>
            {unit != null && <p className={"unit"} style={{fontSize: unitSize + "pt"}}>{unit}</p>}
        </div>
    )
}

NumberItem.propTypes={
    number: PropTypes.oneOfType([string, number]).isRequired,
    color: PropTypes.oneOf(["orange", "green", "purple"]),
    title: PropTypes.string,
    subtitle: PropTypes.string,
    unit: PropTypes.string,
    scale: PropTypes.number
}

NumberItem.defaultProps={
    color: "orange",
    number: 0,
    scale: 1
}