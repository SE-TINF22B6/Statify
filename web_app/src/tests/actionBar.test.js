import Actionbar from "../components/actionbar";
import {fireEvent, render} from "@testing-library/react";
import {BrowserRouter} from "react-router-dom";

test("Rendering Children", () => {
    let actionbar = render(
        <Actionbar>
            <button>Test</button>
        </Actionbar>
    , {wrapper: BrowserRouter})

    let button = actionbar.getByText("Test")
    expect(button).toBeInTheDocument()

})

test("Home navigation", () => {

    let actionBar = render(
        <BrowserRouter>
            <Actionbar>
            </Actionbar>
        </BrowserRouter>
    )

    let navigationElement = actionBar.getByTestId("actionbar-navigation-div")

    fireEvent.click(navigationElement)

    expect(window.location.pathname).toBe('/');
})