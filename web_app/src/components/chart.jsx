import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import '../css/chart.css';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

export default function Chart({ data }) {
    const chartData = {
        labels: data.map((item) => item.label),
        datasets: [
            {
                label: 'Value',
                data: data.map((item) => item.value),
                backgroundColor: 'rgba(104,36,229)',
            },
        ],
    };

    const options = {
        responsive: false,
        plugins: {
            legend: {
                position: 'top',
                display: false,
            },
            title: {
                display: false,
            },
        },
        scales: {
            x: {
                grid: {
                    color: 'white',
                },
                ticks: {
                    color: 'white',
                    maxRotation: 0,
                    autoSkip: false,
                },
            },
            y: {
                grid: {
                    color: 'white',
                },
                ticks: {
                    color: 'white',
                    beginAtZero: true,
                    precision: 0,
                },
            },
        },
        layout: {
            padding: {
                top: 10,
                bottom: 10,
            },
        },
    };

    return (
        <div className="chart-container">
            <Bar data={chartData} options={options} width={771} height={248} />
        </div>
    );
};