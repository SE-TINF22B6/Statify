import {Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import PropTypes from "prop-types";
import Button from "../components/button";
import "../css/generate-statistics-dialog.css"
import {useEffect, useState} from "react";
import ToggleButton from "../components/toggleButton";

export default function GenerateStatisticsDialog({open, setOpen}) {

    const [toggle, setToggle] = useState(0)

    const [content, setContent] = useState(<></>)

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

                )
                break
        }
    }, [toggle]);

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