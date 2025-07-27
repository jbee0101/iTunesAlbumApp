package com.example.itunesalbumapp.presentation.ui.navigation

/**
 * Represents the different screens in the app.
 *
 * @param route The route for the screen.
 */
sealed class Screen(val route: String) {
    /**
     * Represents the album list screen.
     *
     * @constructor Creates an instance of [AlbumList].
     */
    object AlbumList : Screen("album_list")

    /**
     * Represents the album detail screen.
     *
     * @constructor Creates an instance of [AlbumDetail].
     */
    object AlbumDetail : Screen("album_detail/{album}") {

        /**
         * Creates the route for the album detail screen.
         *
         * @param album The album to be displayed.
         * @return The route for the album detail screen.
         */
        fun createRoute(album: String) = "album_detail/$album"
    }
}