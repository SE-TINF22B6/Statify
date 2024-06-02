let playlistStatistics = []
let topTrackStatistics = []
let topArtistStatistics = []
let playlists = []
let profile

let userId


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

function getUserId(){
    if(!userId){
        userId = getCookie("userId")
    }
    return userId
}

function fetchPlaylistStatistics(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/statistics/playlists?userId=" + getUserId())
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
        fetch("http://localhost:8081/statistics/artists?userId=" + getUserId())
            .then(result => result.json())
            .then(res => {
                topArtistStatistics = topArtistStatistics.concat(res)
                resolve(topArtistStatistics)
            })
            .catch(err => {
                console.log(err)
                reject(err)
            })
    })
}

function fetchTopTracksStatistics(){
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8081/statistics/tracks?userId=" + getUserId())
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
        fetch("http://localhost:8081/playlists?userId=" + getUserId())
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
        fetch("http://localhost:8081/profile?userId=" + getUserId())
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
        fetch("http://localhost:8081/track?trackId="  + trackId)
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
        fetch("http://localhost:8081/generate/playlists?userId=" + getUserId() + "&playlistId=" + playlistId)
            .then(result => result.json())
            .then(res => {
                playlistStatistics.push(res)
            })
            .catch(err => {
                console.log(err)
            })
}

export function generateTopArtistsStatistics(){
        fetch("http://localhost:8081/generate/artists?userId=" + getUserId())
            .then(result => result.json())
            .then(res => {
                topArtistStatistics.push(res)
            })
            .catch(err => {
                console.log(err)
            })
}

export function generateTopTracksStatistics(){
        fetch("http://localhost:8081/generate/tracks?userId=" + getUserId())
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
    setCookie("userId", id, 400)
}


function setCookie (name, value, days) {
    const expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + days);

    document.cookie = `${name}=${value}; expires=${expirationDate.toUTCString()}; path=/`;
}

function getCookie (name) {
    const cookies = document.cookie
        .split("; ")
        .find((row) => row.startsWith(`${name}=`));

    return cookies ? cookies.split("=")[1] : null;
}