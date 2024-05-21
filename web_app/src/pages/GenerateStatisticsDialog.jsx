import {Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import PropTypes from "prop-types";
import Button from "../components/button";
import "../css/generate-statistics-dialog.css"
import {useEffect, useState} from "react";
import ToggleButton from "../components/toggleButton";
import PlaylistItem from "../components/playlistItem";

export default function GenerateStatisticsDialog({open, setOpen}) {

    const [toggle, setToggle] = useState(0)

    const [content, setContent] = useState(<></>)

    const [playlists, setPlaylists] = useState([])

    const [selectedPlaylist, setSelectedPlaylist] = useState(null)

    useEffect(() => {
        switch (toggle) {
            case 0:
                setContent(
                    <div className={"row radio-group"}>
                        <div className={"radio-group column"}>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="week" id="week" defaultChecked/>
                                <label htmlFor="week">1 Week</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="month" id="month"/>
                                <label htmlFor="month">1 Month</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="year" id="year"/>
                                <label htmlFor="year">1 Year</label>
                            </div>
                        </div>
                        <div className={"radio-group column"}>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="this-month" id="this-month"/>
                                <label htmlFor="this-month">This Month</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="this-year" id="this-year"/>
                                <label htmlFor="this-year">This Year</label>
                            </div>
                            <div className={"row"}>
                                <input type="radio" name="timespan" value="lifetime" id="lifetime"/>
                                <label htmlFor="lifetime">Lifetime</label>
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
        fetch("http://localhost:8081/playlists")
            .then((result) => {
                return result.json();
            })
            .then((res) => {
                setPlaylists(res);
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