export default class ApiClient {


    playlistStatistics = []
    topTrackStatistics = []
    topArtistStatistics = []
    playlists = []
    profile

    userId


    getTopTracksStatistics() {
        if (this.topArtistStatistics.length === 0) {
            return this.fetchTopTracksStatistics()
        }
        return new Promise(() => {
            return this.topTrackStatistics
        })
    }

    getTopArtistsStatistics() {
        if (this.topArtistStatistics.length === 0) {
            return this.fetchTopArtistsStatistics()
        }
        return new Promise(() => {
            return this.topArtistStatistics
        })
    }

    getPlaylistStatistics() {
        if (this.playlistStatistics.length === 0) {
            return this.fetchPlaylistStatistics()
        }
        return new Promise(() => {
            return this.playlistStatistics
        })
    }

    getPlaylists() {
        if (this.playlists.length === 0) {
            return this.fetchPlaylists()
        }
        return new Promise(() => {
            return this.playlists
        })
    }

    getProfile() {
        if (this.profile == null) {
            return this.fetchProfile()
        }
        return new Promise(() => {
            return this.profile
        })
    }

    getUserId() {
        if (!this.userId) {
            this.userId = this.getCookie("userId")
        }
        return this.userId
    }

    isLoggedIn() {
        // return getUserId() !== null; // TODO: auskommentiert zu Testzwecken
        return false;
    }

    fetchPlaylistStatistics() {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/statistics/playlists?userId=" + this.getUserId())
                .then(result => result.json())
                .then(res => {
                    this.playlistStatistics = this.playlistStatistics.concat(res)
                    resolve(this.playlistStatistics)
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })

    }

    fetchTopArtistsStatistics() {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/statistics/artists?userId=" + this.getUserId())
                .then(result => result.json())
                .then(res => {
                    this.topArtistStatistics = this.topArtistStatistics.concat(res)
                    resolve(this.topArtistStatistics)
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }

    fetchTopTracksStatistics() {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/statistics/tracks?userId=" + this.getUserId())
                .then(result => result.json())
                .then(res => {
                    this.topTrackStatistics = this.topTrackStatistics.concat(res)
                    resolve(this.topTrackStatistics)
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }

    fetchPlaylists() {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/playlists?userId=" + this.getUserId())
                .then(result => {
                    return result.json()
                })
                .then(res => {
                    this.playlists = res
                    resolve(this.playlists)
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }

    fetchProfile() {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/profile?userId=" + this.getUserId())
                .then((result) => {
                    return result.json()
                })
                .then((user) => {
                    this.profile = user
                    resolve(user);
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }

    fetchTrack(trackId) {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/track?trackId=" + trackId + "&userID=" + this.getUserId())
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

    generatePlaylistStatistics(playlistId) {
        return new Promise((resolve, reject) =>{
            fetch("http://localhost:8081/generate/playlists?userId=" + this.getUserId() + "&playlistId=" + playlistId)
                .then(result => result.json())
                .then(res => {
                    if(!(res.status >= 300)){
                        this.playlistStatistics = [...this.playlistStatistics, res]
                        resolve(res)
                    }
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })

    }

    generateTopArtistsStatistics(timeRange) {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/generate/artists?userId=" + this.getUserId() + "&time_range=" + timeRange)
                .then(result => result.json())
                .then(res => {
                    if(!(res.status >= 300)) {
                        this.topArtistStatistics = [...this.topArtistStatistics, res]
                        resolve(res)
                    }
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })

    }

    generateTopTracksStatistics(timeRange) {
        return new Promise((resolve, reject) => {
            fetch("http://localhost:8081/generate/tracks?userId=" + this.getUserId() + "&time_range=" + timeRange)
                .then(result => result.json())
                .then(res => {
                    if(!(res.status >= 300)) {
                        this.topTrackStatistics = [...this.topTrackStatistics, res]
                        resolve(res)
                    }
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }

    requestDeleteProfile(navigate){
        fetch("http://localhost:8081/delete?userId=" + this.getUserId(), {
            method: 'DELETE',
        })
            .then(() => {
                this.playlistStatistics = null
                this.topArtistStatistics = null
                this.topTrackStatistics = null
                this.userId = null
                this.playlists = null
                this.profile = null
                this.deleteCookie("userId")
                navigate("/")
            })
            .catch(err => {
                console.log(err)
            })
    }

    requestDeleteData(){
        fetch("http://localhost:8081/statistics/delete?userId=" + this.getUserId(), {
            method: 'DELETE',
        })
            .then(() => {
                this.playlistStatistics = []
                this.topArtistStatistics = []
                this.topTrackStatistics = []
            })
            .catch(err => {
                console.log(err)
            })
    }

    setUserId(id) {
        this.userId = id
        this.setCookie("userId", id, 400)
    }


    setCookie(name, value, days) {
        const expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + days);

        document.cookie = `${name}=${value}; expires=${expirationDate.toUTCString()}; path=/`;
    }

    getCookie(name) {
        const cookies = document.cookie
            .split("; ")
            .find((row) => row.startsWith(`${name}=`));

        return cookies ? cookies.split("=")[1] : null;
    }

    deleteCookie(name){
        let value = this.getCookie(name)
        this.setCookie(name, value, 0)
    }

}