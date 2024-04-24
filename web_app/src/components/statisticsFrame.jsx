import "../css/statistics-frame.css"
import PropTypes from "prop-types";

export default function StatisticsFrame({children, header, subheader}){
    return(
        <div className={"frame column"}>
                <p className={"header"}>{header}</p>
                {subheader != null && <p className={"subheader"}>{subheader}</p>}
                <hr/>
            <div className={"frame-content column"}>
                {children}
            </div>
        </div>
    )
}

StatisticsFrame.propTypes={
    header: PropTypes.string.isRequired,
}

StatisticsFrame.defaultProps={
    header: "Statistics"
}