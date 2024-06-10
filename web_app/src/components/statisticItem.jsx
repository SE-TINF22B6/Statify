import "../css/statistic-item.css"
import PropTypes from "prop-types";

export default function StatisticItem({number, title, subtitle, image, color, onClick, className}){
    return(
        <div className={"item row " + className} onClick={onClick}>
            <div className={"left row"}>
                <p className={"number " + color}>{number}.</p>
                <div className={"column"}>
                    <p className={"title"}>{title}</p>
                    {subtitle != null && <p className={"subtitle"}>{subtitle}</p>}
                </div>
            </div>
            {image != null && <img src={image} alt=""/>}
        </div>
    )
}

StatisticItem.propTypes={
    color: PropTypes.oneOf(["orange", "green", "purple"]),
    title: PropTypes.string.isRequired,
    subtitle: PropTypes.string,
    number: PropTypes.number.isRequired,
    image: PropTypes.string,
    onClick: PropTypes.func,
    className: PropTypes.string,
}

StatisticItem.defaultProps={
    color: "orange",
    title: "",
    number: 0,
    onClick: () => {},
    className: "",
    image: "",
}
