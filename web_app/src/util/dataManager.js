let playlistStatistics = []
let topTrackStatistics = []
let topArtistStatistics = []
let playlists = []
let profile

let userId = "thisIsAUserId"


export function getTopTracksStatistics(){
    if(topArtistStatistics.length === 0){
        return fetchTopTracksStatistics()
    }
    return new Promise(() => {
        return topTrackStatistics
    })
}

export function getTopArtistsStatistics(){
    if(topArtistStatistics.length === 0){
        return fetchTopArtistsStatistics()
    }
    return new Promise(() => {
        return topArtistStatistics
    })
}

export function getPlaylistStatistics(){
    if(playlistStatistics.length === 0){
        return fetchPlaylistStatistics()
    }
    return new Promise(() => {
        return playlistStatistics
    })
}

export function getPlaylists(){
    if(playlists.length === 0){
        return fetchPlaylists()
    }
    return new Promise(() => {
        return playlists
    })
}

export function getProfile() {
    if(profile == null){
        return fetchProfile()
    }
    return new Promise(() => {
        return profile
    })
}

function fetchPlaylistStatistics(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/statistics/playlists?userId=" + userId)
            .then(result => result.json())
            .then(res => {
                playlistStatistics = playlistStatistics.concat(res)
                resolve(playlistStatistics)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })

}

function fetchTopArtistsStatistics(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/statistics/artists?userId=" + userId)
            .then(result => result.json())
            .then(res => {
                topArtistStatistics = topArtistStatistics.concat(res)
                resolve(topArtistStatistics)
                console.log(res)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

function fetchTopTracksStatistics(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/statistics/tracks?userId=" + userId)
            .then(result => result.json())
            .then(res => {
                topTrackStatistics = topTrackStatistics.concat(res)
                resolve(topTrackStatistics)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

function fetchPlaylists(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/playlists?userId=" + userId)
            .then(result => result.json())
            .then(res => {
                playlists = res
                resolve(playlists)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

function fetchProfile(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/profile?userId=" + userId)
            .then((result) => {
                return result.json()
            })
            .then((user) => {
                profile = user
                resolve(user);
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

export function fetchTrack(trackId){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/track?trackId=abc")
            .then((result) => {
                return result.json();
            })
            .then(res => {
                resolve(res)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

export function generatePlaylistStatistics(playlistId){
    fetch("http://localhost:8081/generate/playlists?userId=" + userId + "&playlistId=" + playlistId)
        .then(result => result.json())
        .then(res => {
            playlistStatistics.push(res)
        })
        .catch(err => {
            console.log(err)
        })
}

export function generateTopArtistsStatistics(){
    fetch("http://localhost:8081/generate/artists?userId=" + userId)
        .then(result => result.json())
        .then(res => {
            topArtistStatistics.push(res)
        })
        .catch(err => {
            console.log(err)
        })
}

export function generateTopTracksStatistics(){
    fetch("http://localhost:8081/generate/tracks?userId=" + userId)
        .then(result => result.json())
        .then(res => {
            topTrackStatistics.push(res)
        })
        .catch(err => {
            console.log(err)
        })
}

export function setUserId(id){
    userId = id
}
