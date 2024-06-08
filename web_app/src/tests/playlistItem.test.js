import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import PlaylistItem from '../components/PlaylistItem';

describe('PlaylistItem Component', () => {
    const title = "Test Title";
    const image = "http://example.com/test.jpg";
    const onClick = jest.fn();

    it('renders without crashing with title and image', () => {
    render(<PlaylistItem title={title} image={image} />);

    expect(screen.getByText(title)).toBeInTheDocument();
    expect(screen.getByRole('img')).toHaveAttribute('src', image);
});

it('renders without crashing, with title only', () => {
    render(<PlaylistItem title={title} />);

    expect(screen.getByText(title)).toBeInTheDocument();
    expect(screen.queryByRole('img')).not.toBeInTheDocument();
});

it('applies selected class when selected prop is true', () => {
    render(<PlaylistItem title={title} image={image} selected />);

    expect(screen.getByText(title).closest('div')).toHaveClass('selected');
});

it('does not apply selected class when selected prop is false', () => {
    render(<PlaylistItem title={title} image={image} />);

    expect(screen.getByText(title).closest('div')).not.toHaveClass('selected');
});

it('calls onClick handler when clicked', () => {
    render(<PlaylistItem title={title} image={image} onClick={onClick} />);

    fireEvent.click(screen.getByText(title));
    expect(onClick).toHaveBeenCalledTimes(1);
});
});
