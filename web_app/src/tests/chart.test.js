import React from 'react';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Chart from "../components/chart"
import { Bar } from 'react-chartjs-2';

// Mocking the Bar component from react-chartjs-2
jest.mock('react-chartjs-2', () => ({
    Bar: jest.fn(() => <div data-testid="bar-chart" />),
}));

const mockData = [
    { label: 'Data1', value: 30 },
    { label: 'Data2', value: 20 },
];

describe('Chart Component', () => {
    test('renders without crashing', () => {
        const { getByTestId } = render(<Chart data={mockData} />);
        expect(getByTestId('chart-container')).toBeInTheDocument();
    });

    test('renders Bar component with correct data and options', () => {
        render(<Chart data={mockData} />);

        expect(Bar).toHaveBeenCalledWith(
            expect.objectContaining({
                data: expect.objectContaining({
                    labels: ['Data1', 'Data2'],
                    datasets: [
                        expect.objectContaining({
                            label: 'Value',
                            data: [30, 20],
                            backgroundColor: 'rgba(104,36,229)',
                        }),
                    ],
                }),
                options: expect.objectContaining({
                    responsive: false,
                    plugins: {
                        legend: { position: 'top', display: false },
                        title: { display: false },
                    },
                    scales: {
                        x: {
                            grid: { color: 'grey' },
                            ticks: { color: 'white', maxRotation: 0, autoSkip: false },
                        },
                        y: {
                            grid: { color: 'grey' },
                            ticks: { color: 'white', beginAtZero: true, precision: 1 },
                        },
                    },
                    layout: { padding: { top: 10, bottom: 10 } },
                }),
                width: 771,
                height: 248,
            }),
            {}
        );
    });
});
