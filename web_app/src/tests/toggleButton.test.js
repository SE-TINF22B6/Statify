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


    test("toggles button selection to second button", () => {
        let toggle = 0
        const setToggle = ((value) => {
            toggle = value
        })

        let toggleButton = render(
            <ToggleButton choices={["First", "Second"]}
                          selected={toggle}
                          setSelected={setToggle}
                          color={"purple"}
                          buttonWidth={300}
                          textSize={14}/>
        )

        let firstButton = toggleButton.getByText("First")
        let secondButton = toggleButton.getByText("Second")

        fireEvent.click(secondButton)

        expect(secondButton.style === selectedStyle)
        expect(firstButton.style === unselectedStyle)
    })

    test("toggles button selection to first button", () => {
        let toggle = 1
        const setToggle = ((value) => {
            toggle = value
        })

        let toggleButton = render(
            <ToggleButton choices={["First", "Second"]}
                          selected={toggle}
                          setSelected={setToggle}
                          color={"purple"}
                          buttonWidth={300}
                          textSize={14}/>
        )

        let firstButton = toggleButton.getByText("First")
        let secondButton = toggleButton.getByText("Second")

        fireEvent.click(firstButton)

        expect(firstButton.style === selectedStyle)
        expect(secondButton.style === unselectedStyle)
    })

    it('renders with default props', () => {
        let button = render(<ToggleButton />);
        const firstButton = button.getByText('1');
        const secondButton = button.getByText('2');

        expect(firstButton).toBeInTheDocument();
        expect(secondButton).toBeInTheDocument();
        expect(firstButton).toHaveStyle('opacity: 100%');
        expect(secondButton).toHaveStyle('opacity: 40%');
    });

    it('renders with custom props', () => {
        let button = render(<ToggleButton color="purple" selected={1} choices={['One', 'Two']} />);
        const firstButton = button.getByText('One');
        const secondButton = button.getByText('Two');

        expect(firstButton).toBeInTheDocument();
        expect(secondButton).toBeInTheDocument();
        expect(firstButton).toHaveStyle('opacity: 40%');
        expect(secondButton).toHaveStyle('opacity: 100%');
    });

    it("error if choices prop has wrong size", () => {

        const spy = jest.spyOn(console, 'error').mockImplementation(() => {});
        let expectedErrorMessage = "Invalid array length 3 (expected 2) for prop choices supplied to ToggleButton. Validation failed."

        render(<ToggleButton choices={[1, 2, 3]}/>)

        const errorCalls = spy.mock.calls;
        const found = errorCalls.some(call => call.join(" ").includes(expectedErrorMessage));
        expect(found).toBe(true);

    });

})
