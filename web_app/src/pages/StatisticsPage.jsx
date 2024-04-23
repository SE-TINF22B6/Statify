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
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b273b7a9a6a2bf311630d3fc6956"}
                                       title={"Faith"}
                                       subtitle={"George Michael"}
                                       number={1}
                                       color={"green"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b27377a50cb765eef114e8ce488c"}
                                       title={"Lemon Tree"}
                                       subtitle={"Fools Garden"}
                                       number={2}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b2734121faee8df82c526cbab2be"}
                                       title={"Thriller"}
                                       subtitle={"Michael Jackson"}
                                       number={3}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b2733b7492bb678d5d51683444ae"}
                                       title={"Footloose"}
                                       subtitle={"Kenny Loggins"}
                                       number={4}
                                       color={"purple"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab67616d0000b2731fc9fd5d701ee05cb39b7b19"}
                                       title={"Like a Prayer"}
                                       subtitle={"Madonna"}
                                       number={5}
                                       color={"purple"}/>
                    </StatisticsFrame>
                    <StatisticsFrame header={"Top Artists"} subheader={"01.04.2024 - 22.04.2024"}>
                        <StatisticItem image={"https://i.scdn.co/image/ab6761610000e5eb214f3cf1cbe7139c1e26ffbb"}
                                       title={"The Weeknd"}
                                       number={1}
                                       color={"green"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab6761610000e5ebe03a98785f3658f0b6461ec4"}
                                       title={"Olivia Rodrigo"}
                                       number={2}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab6761610000e5ebad85a585103dfc2f3439119a"}
                                       title={"Hozier"}
                                       number={3}
                                       color={"orange"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab6761610000e5eb40b5c07ab77b6b1a9075fdc0"}
                                       title={"Ariana Grande"}
                                       number={4}
                                       color={"purple"}/>
                        <StatisticItem image={"https://i.scdn.co/image/ab6761610000e5ebe672b5f553298dcdccb0e676"}
                                       title={"Taylor Swift"}
                                       number={5}
                                       color={"purple"}/>
                    </StatisticsFrame>
                </div>
            </div>
        </div>

    )
}


