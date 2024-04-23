import "../css/statistic-item.css"

export default function StatisticItem({number, title, subtitle, image, color}){
    return(
        <div className={"item"}>
            <div className={"left"}>
                <p className={"number " + color}>{number}.</p>
                <div className={"column"}>
                    <p className={"title"}>{title}</p>
                    {subtitle != null && <p className={"subtitle"}>{subtitle}</p>}
                </div>
            </div>
            <img src={image}/>
        </div>
    )
}