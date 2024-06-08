import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import StatisticsFrame from '../components/statisticsFrame';

describe('StatisticsFrame Component', () => {
    const header = "Test Header";
    const subheader = "Test Subheader";
    const children = <div>Test Children</div>;
    const onScroll = jest.fn();

    it('renders with header, subheader, and children', () => {
        render(
            <StatisticsFrame
                header={header}
                subheader={subheader}
            >
                {children}
            </StatisticsFrame>
        );

        expect(screen.getByText(header)).toBeInTheDocument();
        expect(screen.getByText(subheader)).toBeInTheDocument();
        expect(screen.getByText("Test Children")).toBeInTheDocument();
        expect(screen.queryAllByAltText("arrow icon").length).toBe(0); // No arrow icons should be present
    });

    it('renders without subheader', () => {
        render(
            <StatisticsFrame
                header={header}
            >
                {children}
            </StatisticsFrame>
        );

        expect(screen.getByText(header)).toBeInTheDocument();
        expect(screen.queryByText(subheader)).not.toBeInTheDocument();
        expect(screen.getByText("Test Children")).toBeInTheDocument();
    });

    it('renders with scrollable arrows and handles onScroll correctly', () => {
        render(
            <StatisticsFrame
                header={header}
                subheader={subheader}
                scrollable
                onScroll={onScroll}
            >
                {children}
            </StatisticsFrame>
        );

        const arrows = screen.queryAllByAltText("arrow icon");
        expect(arrows.length).toBe(2); // Two arrow icons should be present

        fireEvent.click(arrows[0]); // Click the first arrow (scroll up)
        expect(onScroll).toHaveBeenCalledWith(-1);

        fireEvent.click(arrows[1]); // Click the second arrow (scroll down)
        expect(onScroll).toHaveBeenCalledWith(1);
    });

    it('applies default props correctly', () => {
        render(<StatisticsFrame>{children}</StatisticsFrame>);

        expect(screen.getByText("Statistics")).toBeInTheDocument(); // Default header
        expect(screen.getByText("Test Children")).toBeInTheDocument();
        expect(screen.queryAllByAltText("arrow icon").length).toBe(0); // No arrow icons should be present
    });
});
