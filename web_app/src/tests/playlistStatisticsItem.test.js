import PlaylistStatisticsItem from "../components/playlistStatisticsItem";
import {render} from "@testing-library/react";

describe("Playlist Statistics Item Tests", () => {

    describe("Test calculating minutes from milliseconds", () => {

        test("Convert milliseconds to minutes", () => {
            let expectedMinutes = 31
            let duration = 1860000
            let playlistStatsItem = render(
                <PlaylistStatisticsItem name={"name"}
                                        duration={duration}
                                        songs={10}
                                        top_artist={"Top Artist"}
                                        top_genre={"Top Genre"}
                                        top_artist_count={3}
                                        top_genre_count={5}/>
            )

            let minutesElement = playlistStatsItem.getByTestId("playlist-minutes")
            expect(minutesElement).toBeInTheDocument()
            expect(minutesElement.textContent).toEqual(expectedMinutes.toString())
        })

        test("Round minutes up", () => {
            let expectedMinutes = 31
            let duration = 1840000
            let playlistStatsItem = render(
                <PlaylistStatisticsItem name={"name"}
                                        duration={duration}
                                        songs={10}
                                        top_artist={"Top Artist"}
                                        top_genre={"Top Genre"}
                                        top_artist_count={3}
                                        top_genre_count={5}/>
            )

            let minutesElement = playlistStatsItem.getByTestId("playlist-minutes")
            expect(minutesElement).toBeInTheDocument()
            expect(minutesElement.textContent).toEqual(expectedMinutes.toString())
        })

        test("Round 0.5 minutes up", () => {
            let expectedMinutes = 31
            let duration = 1830000
            let playlistStatsItem = render(
                <PlaylistStatisticsItem name={"name"}
                                        duration={duration}
                                        songs={10}
                                        top_artist={"Top Artist"}
                                        top_genre={"Top Genre"}
                                        top_artist_count={3}
                                        top_genre_count={5}/>
            )

            let minutesElement = playlistStatsItem.getByTestId("playlist-minutes")
            expect(minutesElement).toBeInTheDocument()
            expect(minutesElement.textContent).toEqual(expectedMinutes.toString())
        })

        test("Round minutes down", () => {
            let expectedMinutes = 30
            let duration = 1820000
            let playlistStatsItem = render(
                <PlaylistStatisticsItem name={"name"}
                                        duration={duration}
                                        songs={10}
                                        top_artist={"Top Artist"}
                                        top_genre={"Top Genre"}
                                        top_artist_count={3}
                                        top_genre_count={5}/>
            )

            let minutesElement = playlistStatsItem.getByTestId("playlist-minutes")
            expect(minutesElement).toBeInTheDocument()
            expect(minutesElement.textContent).toEqual(expectedMinutes.toString())
        })


    })
})