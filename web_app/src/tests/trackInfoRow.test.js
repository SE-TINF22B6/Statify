import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import TrackInfoRow from '../components/TrackInfoRow';

describe('TrackInfoRow Component', () => {
    const data = [
        { key: 'Key1', value: 10 },
        { key: 'Key2', value: 20 },
        { key: 'Key3', value: 30 }
    ];
    const color = 'green';

    it('renders with default props', () => {
        render(<TrackInfoRow data={data} />);

        expect(screen.getByText('Key1')).toBeInTheDocument();
        expect(screen.getByText('Key2')).toBeInTheDocument();
        expect(screen.getByText('Key3')).toBeInTheDocument();

        expect(screen.getByText('10')).toBeInTheDocument();
        expect(screen.getByText('20')).toBeInTheDocument();
        expect(screen.getByText('30')).toBeInTheDocument();

        expect(screen.getByText('10')).toHaveClass('orange'); // Default color
        expect(screen.getByText('20')).toHaveClass('orange'); // Default color
        expect(screen.getByText('30')).toHaveClass('orange'); // Default color
    });

    it('renders with custom props', () => {
        render(<TrackInfoRow data={data} color={color} />);

        expect(screen.getByText('Key1')).toBeInTheDocument();
        expect(screen.getByText('Key2')).toBeInTheDocument();
        expect(screen.getByText('Key3')).toBeInTheDocument();

        expect(screen.getByText('10')).toBeInTheDocument();
        expect(screen.getByText('20')).toBeInTheDocument();
        expect(screen.getByText('30')).toBeInTheDocument();

        expect(screen.getByText('10')).toHaveClass('green');
        expect(screen.getByText('20')).toHaveClass('green');
        expect(screen.getByText('30')).toHaveClass('green');
    });
});
