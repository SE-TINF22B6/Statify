import NumberItem from "./numberItem";
import "../css/track-row-info.css"
import PropTypes from "prop-types";

export default function TrackInfoRow({data, color, scale}){
    return (
        <div className={"track-row-info row"}>
            {
                data.map((data, i) =>
                    <NumberItem
                        title={data.key}
                        number={data.value}
                        color={color}
                        scale={scale}
                        key={i}
                    />
                )
            }
        </div>
    )
}

TrackInfoRow.propTypes = {
    data: PropTypes.arrayOf(PropTypes.shape(
        {
            key: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
            value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
        }
    )).isRequired,
    scale: PropTypes.number,
    color: PropTypes.oneOf(["orange", "green", "purple"]),
}

TrackInfoRow.defaultProps = {
    color: "orange",
    scale: 1
}


