package com.escalondev.movies_app_kmp.data.repository

import com.escalondev.movies_app_kmp.domain.model.Movie

// TODO: Delete this mocked repository once real data gets fetched from the server.
object MockedMoviesRepository {

    /**
     * get a list of mocked watchlist for now to display items on the UI.
     */
    fun getWatchlist(): List<Movie> {
        return listOf(
            Movie(
                id = 0,
                title = "Inside Out 2",
                imageUrl = "/t7bhjraXuN4hd3yZVBVVhP3BdX0.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 1,
                title = "Kindom of the Planet of the Apes",
                imageUrl = "https://media.themoviedb.org/t/p/original/gKkl37BQuKTanygYQG1pyYgLVgf.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 2,
                title = "Civil War",
                imageUrl = "https://media.themoviedb.org/t/p/original/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 3,
                title = "Godzila*Kong",
                imageUrl = "https://media.themoviedb.org/t/p/original/z1p34vh7dEOnLDmyCrlUVLuoDzd.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 4,
                title = "TAROT",
                imageUrl = "https://media.themoviedb.org/t/p/original/gAEUXC37vl1SnM7PXsHTF23I2vq.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                5,
                "Atlas",
                "https://media.themoviedb.org/t/p/original/bcM2Tl5HlsvPBnL8DKP9Ie6vU4r.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 6,
                title = "The Fall Guy",
                imageUrl = "https://media.themoviedb.org/t/p/original/tSz1qsmSJon0rqjHBxXZmrotuse.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 7,
                title = "Bad Boys: Ride or Die",
                imageUrl = "https://media.themoviedb.org/t/p/original/nP6RliHjxsz4irTKsxe8FRhKZYl.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 8,
                title = "The First Omen",
                imageUrl = "https://media.themoviedb.org/t/p/original/uGyiewQnDHPuiHN9V4k2t9QBPnh.jpg",
                releaseDate = "2024-10-09",
            ),
            Movie(
                id = 9,
                title = "Godzilla Minus One",
                imageUrl = "https://media.themoviedb.org/t/p/original/hkxxMIGaiCTmrEArK7J56JTKUlB.jpg",
                releaseDate = "2024-10-09",
            ),
        )
    }
}
