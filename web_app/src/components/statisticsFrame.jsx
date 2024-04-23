import "../css/statistics-frame.css"

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