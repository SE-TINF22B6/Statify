import "../css/statistics-page.css"
import StatisticsFrame from "../components/StatisticsFrame";
import Actionbar from "../components/actionbar";
export default function StatisticsPage(){
    return (
        <div>
            <Actionbar>

            </Actionbar>
            <div className={"statistics-page page"}>
                <div className={"content"}>
                    <StatisticsFrame header={"Top Artists"} subheader={"01.04.2024 - 22.04.2024"}>
                        <p>sbdcjbdc sjdnjdn dkjasnjsn ydsj sjdnjnsjda sdnanskd sdnasnd</p>
                    </StatisticsFrame>
                </div>
            </div>
        </div>

)
}


