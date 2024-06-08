import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Track from '../components/Track';

describe('Track Component', () => {
    const imageUrl = "http://example.com/image.jpg";
    const trackTitle = "Test Track Title";
    const artistName = "Test Artist Name";

    it('renders with track image, track title, and artist name', () => {
        render(
            <Track
                imageUrl={imageUrl}
                trackTitle={trackTitle}
                artistName={artistName}
            />
        );

        const imgElement = screen.getByRole('img', { name: /track/i });
        const titleElement = screen.getByText(trackTitle);
        const artistElement = screen.getByText(artistName);

        expect(imgElement).toBeInTheDocument();
        expect(imgElement).toHaveAttribute('src', imageUrl);
        expect(titleElement).toBeInTheDocument();
        expect(artistElement).toBeInTheDocument();
    });

    it('renders correctly without image', () => {
        render(
            <Track
                trackTitle={trackTitle}
                artistName={artistName}
            />
        );

        const imgElement = screen.queryByRole('img', { name: /track/i });
        const titleElement = screen.getByText(trackTitle);
        const artistElement = screen.getByText(artistName);

        expect(imgElement).toBeInTheDocument();
        expect(titleElement).toBeInTheDocument();
        expect(artistElement).toBeInTheDocument();
    });

});
