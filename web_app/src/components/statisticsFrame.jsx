import "../css/components/statistics-frame.css"
import arrow from "../images/arrow.png"
import PropTypes from "prop-types";

export default function StatisticsFrame({children, header, subheader, scrollable, onScroll}){
    return(
        <div className={"frame row"}>
            {scrollable && <img className={"clickable"} src={arrow} style={{rotate:"180deg"}} alt={"arrow icon"} onClick={() => onScroll(-1)}/>}
            <div className={"column"}>
                <p className={"header"}>{header}</p>
                {subheader != null && <p className={"subheader"}>{subheader}</p>}
                <hr/>
                <div className={"frame-content column"}>
                    {children}
                </div>
            </div>
            {scrollable && <img className={"clickable"} src={arrow} alt={"arrow icon"} onClick={() => onScroll(1)}/>}
        </div>
    )
}


StatisticsFrame.propTypes={
    header: PropTypes.string.isRequired,
    subheader: PropTypes.string,
    scrollable: PropTypes.bool,
    onScroll: PropTypes.func
}

StatisticsFrame.defaultProps={
    header: "Statistics",
    scrollable: false,
    onScroll: () => {}
}