import ToggleButton from "../components/toggleButton";
import {fireEvent, render} from "@testing-library/react";
import {decodeColors} from "../util/decodeColors";
import {color} from "chart.js/helpers";


describe("Toggle Button Tests", () => {
    const selectedStyle = {
        backgroundColor: decodeColors[color],
        opacity: "100%"

    }

    const unselectedStyle = {
        backgroundColor: decodeColors[color],
        opacity: "40%"
    }

    let toggleButton
    let toggle

    beforeEach(() => {
        toggle = 0
        const setToggle = ((value) => {
            toggle = value
        })

        toggleButton = render(
            <ToggleButton choices={["First", "Second"]}
                          selected={toggle}
                          setSelected={setToggle}
                          color={"purple"}
                          buttonWidth={300}
                          textSize={14}/>
        )
    })

    test("Rendering", () => {
        let selectedButton = toggleButton.getByText("First")
        let unselectedButton = toggleButton.getByText("Second")
        expect(selectedButton).toBeInTheDocument()
        expect(unselectedButton).toBeInTheDocument()
        expect(selectedButton.style === selectedStyle)
        expect(unselectedButton.style === unselectedStyle)
    })

    test("Toggle", () => {
        let firstButton = toggleButton.getByText("First")
        let secondButton = toggleButton.getByText("Second")

        fireEvent.click(secondButton)

        expect(secondButton.style === selectedStyle)
        expect(firstButton.style === unselectedStyle)
    })

})
