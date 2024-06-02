import {Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import PropTypes from "prop-types";
import Button from "../components/button";
import "../css/generate-statistics-dialog.css"
import {useEffect, useState} from "react";
import ToggleButton from "../components/toggleButton";
import PlaylistItem from "../components/playlistItem";
import {
    generatePlaylistStatistics,
    generateTopArtistsStatistics,
    generateTopTracksStatistics,
    getPlaylists
} from "../util/apiClient";

export default function GenerateStatisticsDialog({open, setOpen}) {

    const TOGGLE_TRACKS_ARTISTS = 0
    const TOGGLE_PLAYLISTS = 1

    const [toggle, setToggle] = useState(TOGGLE_TRACKS_ARTISTS)

    const [content, setContent] = useState(<></>)

    const [playlists, setPlaylists] = useState([])

    const [selectedPlaylist, setSelectedPlaylist] = useState(1)

    const[selection, setSelection] = useState("tracks")

    useEffect(() => {
        switch (toggle) {
            case TOGGLE_TRACKS_ARTISTS:
                setContent(<div className={"column"}>
                    <div className={"radio-group column"}>
                        <div className={"row"}>
                            <input type="radio" name="timespan" value="long" id="long" defaultChecked/>
                            <label htmlFor="long">Long Term (1 Year)</label>
                        </div>
                        <div className={"row"}>
                            <input type="radio" name="timespan" value="medium" id="medium"/>
                            <label htmlFor="medium">Medium Term (6 Months)</label>
                        </div>
                        <div className={"row"}>
                            <input type="radio" name="timespan" value="short" id="short"/>
                            <label htmlFor="short">Short Term (4 Weeks)</label>
                        </div>
                    </div>
                    <hr/>
                        <div className={"radio-group column"}>
                            <div className={"row"}>
                                <input type="radio" name="type" value="tracks" id="tracks" defaultChecked onChange={(e) => handleChange(e, setSelection)}/>
                                <label htmlFor="tracks">Top Tracks</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="type" value="artists" id="artists" onChange={(e) => handleChange(e, setSelection)}/>
                                <label htmlFor="artists">Top Artists</label>
                            </div>
                        </div>
                </div>

                )
                break
            case TOGGLE_PLAYLISTS:
                setContent(
                    <div className={"column"}>
                        <div className={"scrollable playlist-container"}>
                            {
                                playlists.map((playlist, i) => (
                                    <PlaylistItem key={i} title={playlist.name} image={playlist.imageURL} selected={i === selectedPlaylist} onClick={() => selectPlaylist(i)}/>
                                ))
                            }
                        </div>
                    </div>
                )
                break
            default:
                setContent(<div></div>)
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [toggle, selectedPlaylist, playlists]);

    useEffect(() => {
        getPlaylists()
            .then(res => {
                setPlaylists(res)
            })
    }, []);

    useEffect(() => {
        setSelectedPlaylist(0)
        setToggle(TOGGLE_TRACKS_ARTISTS)
    }, [open]);

    function selectPlaylist(index){
        if(selectedPlaylist !== index){
            setSelectedPlaylist(index)
        }
    }
    function handleChange (e, setVariable) {
        setVariable(e.currentTarget.value);
    }

    function onGenerate(){
        if(toggle === TOGGLE_TRACKS_ARTISTS){
            if(selection === "artists"){
                generateTopArtistsStatistics()
            }
            else if(selection === "tracks"){
                generateTopTracksStatistics()
            }
        }
        else if(toggle === TOGGLE_PLAYLISTS){
            generatePlaylistStatistics(playlists[selectedPlaylist].id)
        }
        setOpen(false)
    }


    return (
        <Dialog open={open} onClose={() => setOpen(false)}>
            <DialogTitle>Generate Statistics</DialogTitle>
            <DialogContent>
                <div className={"column"}>
                    <ToggleButton choices={["Top Tracks/Artists", "Playlists"]} selected={toggle} setSelected={setToggle}
                                  color={"purple"} buttonWidth={300} textSize={14}/>
                    {content}
                </div>
            </DialogContent>
            <DialogActions className={"row"}>
                <Button className={"button"} color={"green"} scale={0.45} widthOffset={-20} onClick={onGenerate}>Generate</Button>
                <Button className={"button"} color={"purple"} scale={0.45} widthOffset={-20} onClick={() => setOpen(false)}>Cancel</Button>
            </DialogActions>
        </Dialog>
    )
}

GenerateStatisticsDialog.propTypes = {
    open: PropTypes.bool.isRequired,
    setOpen: PropTypes.func.isRequired,
}