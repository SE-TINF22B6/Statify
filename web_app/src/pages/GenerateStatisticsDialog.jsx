import {Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import PropTypes from "prop-types";
import Button from "../components/button";
import "../css/generate-statistics-dialog.css"
import {useEffect, useState} from "react";
import ToggleButton from "../components/toggleButton";
import PlaylistItem from "../components/playlistItem";
import {getPlaylists} from "../util/dataManager";

export default function GenerateStatisticsDialog({open, setOpen}) {

    const [toggle, setToggle] = useState(0)

    const [content, setContent] = useState(<></>)

    const [playlists, setPlaylists] = useState([])

    const [selectedPlaylist, setSelectedPlaylist] = useState(null)

    useEffect(() => {
        switch (toggle) {
            case 0:
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
                                <input type="radio" name="type" value="tracks" id="tracks" defaultChecked/>
                                <label htmlFor="tracks">Top Tracks</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="type" value="artists" id="artists"/>
                                <label htmlFor="artists">Top Artists</label>
                            </div>
                        </div>
                </div>

                )
                break
            case 1:
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
        setSelectedPlaylist(null)
        setToggle(0)
    }, [open]);

    function selectPlaylist(index){
        if(selectedPlaylist === index){
            setSelectedPlaylist(null)
        }
        else{
            setSelectedPlaylist(index)
        }
    }

    function onToggle(index){
        if(index === 0){
            setSelectedPlaylist(null)
        }
    }


    return (
        <Dialog open={open} onClose={() => setOpen(false)}>
            <DialogTitle>Generate Statistics</DialogTitle>
            <DialogContent>
                <div className={"column"}>
                    <ToggleButton choices={["Top Tracks/Artists", "Playlists"]} selected={toggle} setSelected={setToggle}
                                  color={"purple"} buttonWidth={300} textSize={14} onToggle={onToggle}/>
                    {content}
                </div>
            </DialogContent>
            <DialogActions className={"row"}>
                <Button className={"button"} color={"green"} scale={0.45} widthOffset={-20}>Generate</Button>
                <Button className={"button"} color={"purple"} scale={0.45} widthOffset={-20} onClick={() => setOpen(false)}>Cancel</Button>
            </DialogActions>
        </Dialog>
    )
}

GenerateStatisticsDialog.propTypes = {
    open: PropTypes.bool.isRequired,
    setOpen: PropTypes.func.isRequired
}