import "../css/number-item.css"

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