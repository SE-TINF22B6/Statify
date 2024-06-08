import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import StatisticItem from '../components/StatisticItem';

describe('StatisticItem Component', () => {
    const number = 1;
    const title = "Test Title";
    const subtitle = "Test Subtitle";
    const image = "http://example.com/test.jpg";
    const color = "green";
    const onClick = jest.fn();
    const className = "custom-class";

    it('renders with title, number, subtitle, and image', () => {
        render(
            <StatisticItem
                number={number}
                title={title}
                subtitle={subtitle}
                image={image}
                color={color}
                className={className}
                onClick={onClick}
            />
        );

        expect(screen.getByText(`${number}.`)).toHaveClass(color);
        expect(screen.getByText(title)).toBeInTheDocument();
        expect(screen.getByText(subtitle)).toBeInTheDocument();
        expect(screen.getByRole('img')).toBeInTheDocument();
        expect(screen.getByRole('img')).toHaveAttribute('src', image);
    });

    it('renders without subtitle and image', () => {
        render(
            <StatisticItem
                number={number}
                title={title}
                color={color}
                className={className}
                onClick={onClick}
            />
        );

        expect(screen.getByText(`${number}.`)).toHaveClass(color);
        expect(screen.getByText(title)).toBeInTheDocument();
        expect(screen.queryByText(subtitle)).not.toBeInTheDocument();
        expect(screen.queryByRole('img')).not.toBeInTheDocument();
    });

    it('applies default color class if color is not provided', () => {
        render(
            <StatisticItem
                number={number}
                title={title}
                subtitle={subtitle}
                className={className}
                onClick={onClick}
            />
        );

        expect(screen.getByText(`${number}.`)).toHaveClass('orange');
    });

    it('calls onClick handler when clicked', () => {
        render(
            <StatisticItem
                number={number}
                title={title}
                subtitle={subtitle}
                image={image}
                color={color}
                className={className}
                onClick={onClick}
            />
        );

        fireEvent.click(screen.getByText(title).parentElement.parentElement.parentElement);
        expect(onClick).toHaveBeenCalledTimes(1);
    });

    it('applies custom className', () => {
        render(
            <StatisticItem
                number={number}
                title={title}
                subtitle={subtitle}
                image={image}
                color={color}
                className={className}
                onClick={onClick}
            />
        );

        expect(screen.getByText(title).parentElement.parentElement.parentElement).toHaveClass('custom-class');
    });
});
