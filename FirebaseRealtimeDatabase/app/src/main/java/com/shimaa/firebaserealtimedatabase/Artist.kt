package com.shimaa.firebaserealtimedatabase

class Artist  {
    var artistId:String
    var artistName:String
    var artistGenre:String

    constructor(){
        artistId=""
        artistName=""
        artistGenre=""
    }
    constructor(artistId: String, artistName: String, artistGenre: String) {
        this.artistId = artistId
         this.artistName = artistName
        this.artistGenre = artistGenre
    }

     fun getId(): String {
        return artistId
    }
    fun getName(): String {
        return artistName
    }
    fun getGenre(): String {
        return artistGenre
    }
}