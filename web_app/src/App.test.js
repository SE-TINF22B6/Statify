import { render, screen } from '@testing-library/react';
import Button from "./components/button";

test("button test", () => {
  render(
      <Button color="orange"
              scale={0.5}
              widthOffset={30}
              className={"button"}>Text</Button>
  )

  const button = screen.getByRole("button");
  expect(button).toBeInTheDocument();
  let style = window.getComputedStyle(button)
  expect(style.getPropertyValue("width")).toBe("200px");
  expect(style.getPropertyValue("font-size")).toBe("16px");
  expect(button).toHaveClass("button");

  const text = screen.getByText("Text");
  expect(text).toBeInTheDocument();
  expect(button).toContainElement(text);
})
