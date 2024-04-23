import "../css/statistics-page.css"
import StatisticsFrame from "../components/StatisticsFrame";
import Actionbar from "../components/actionbar";
import StatisticItem from "../components/StatisticItem";

export default function StatisticsPage() {
    return (
        <div>
            <Actionbar>

            </Actionbar>
            <div className={"statistics-page page"}>
                <div className={"content"}>
                    <StatisticsFrame header={"Top Tracks"} subheader={"01.04.2024 - 22.04.2024"}>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228"}
                                       title={"Song Title"}
                                       subtitle={"Artist"}
                                       number={1}
                                       color={"green"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Song Title"}
                                       subtitle={"Artist"}
                                       number={2}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Song Title"}
                                       subtitle={"Artist"}
                                       number={3}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Song Title"}
                                       subtitle={"Artist"}
                                       number={4}
                                       color={"purple"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Song Title"}
                                       subtitle={"Artist"}
                                       number={5}
                                       color={"purple"}/>
                    </StatisticsFrame>
                    <StatisticsFrame header={"Top Artists"} subheader={"01.04.2024 - 22.04.2024"}>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228"}
                                       title={"Artist Name"}
                                       number={1}
                                       color={"green"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Artist Name"}
                                       number={2}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Artist Name"}
                                       number={3}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Artist Name"}
                                       number={4}
                                       color={"purple"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Artist Name"}
                                       number={5}
                                       color={"purple"}/>
                    </StatisticsFrame>
                </div>
            </div>
        </div>

    )
}


