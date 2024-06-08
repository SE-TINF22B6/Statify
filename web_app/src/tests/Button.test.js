import {fireEvent, render, screen} from '@testing-library/react';
import Button from "../components/button";


describe("Button Tests", () => {

  it('renders with default props', () => {
    render(<Button />);
    const button = screen.getByRole('button');

    expect(button).toBeInTheDocument();
    expect(button).toHaveStyle('width: 340px');
    expect(button).toHaveStyle('height: 80px');
    expect(button).toHaveStyle('font-size: 32px');
  });

  it('renders with custom props', () => {
    render(
        <Button color="purple" scale={0.5} widthOffset={30} className="custom-button">
          Custom Text
        </Button>
    );
    const button = screen.getByRole('button');

    expect(button).toBeInTheDocument();
    expect(button).toHaveStyle('width: 200px'); // (340 * 0.5) + 30
    expect(button).toHaveStyle('height: 40px'); // (80 * 0.5)
    expect(button).toHaveStyle('font-size: 16px'); // (32 * 0.5)
    expect(button).toHaveClass('custom-button');
  });

  test("triggers onClick handler", () => {
    const onClick = jest.fn()
    let button = render(
        <Button color="orange"
                scale={0.5}
                widthOffset={30}
                className={"button"}
                onClick={onClick}>
          Text
        </Button>)

    fireEvent.click(button.getByRole("button"))

    expect(onClick).toBeCalledTimes(1);

  })

})
